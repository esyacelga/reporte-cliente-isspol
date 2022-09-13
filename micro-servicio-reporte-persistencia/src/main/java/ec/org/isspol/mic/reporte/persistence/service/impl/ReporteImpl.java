package ec.org.isspol.mic.reporte.persistence.service.impl;

import ec.org.isspol.log.IsspolLogger;
import ec.org.isspol.mic.reporte.persistence.entities.ext.DatoCabecera;
import ec.org.isspol.mic.reporte.persistence.entities.ext.DatoPie;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.ReporteGenerado;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.ReporteSubreporte;
import ec.org.isspol.mic.reporte.persistence.service.IReporte;
import ec.org.isspol.mic.reporte.shared.exception.ReportException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.ByteArrayInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ReporteImpl implements IReporte {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Reporte findReporte(String nameReport) throws ReportException {
        EntityManager em = entityManager;
        em.clear();
        Query query = em.createNamedQuery("Reporte.findByIdentificador").setParameter("identificador", nameReport);

        try {
            Reporte reporte = (Reporte) query.getSingleResult();
            em.getEntityManagerFactory().getCache().evict(Reporte.class, reporte.getIdReporte());
            return reporte;
        } catch (Exception ex) {
            IsspolLogger.getInstance().error("Error al recuperar ", ex);
            throw new ReportException("No se encontro el reporte con el identificador: " + nameReport, ex);
        } finally {
            em.close();
        }
    }

    @Override
    public Collection<ReporteSubreporte> buscarSubReportePorIdReporte(Integer idReportePadre) throws ReportException {
        EntityManager em = entityManager;
        em.clear();
        Collection<ReporteSubreporte> lstReporteSubreportes = new ArrayList<>();
        Query query = em.createNamedQuery("ReporteSubReporte.findByIdReportePadre").setParameter("idReportePadre", idReportePadre);

        try {
            lstReporteSubreportes = query.getResultList();

            em.getEntityManagerFactory().getCache().evictAll();
            return lstReporteSubreportes;
        } catch (Exception ex) {
            IsspolLogger.getInstance().error("Error al recuperar ", ex);
        } finally {
            em.close();
        }
        return lstReporteSubreportes;
    }

    @Override
    public void guardarReporteGenerado(ReporteGenerado reporteGenerado) throws ReportException {
        EntityManager em = entityManager;
        em.clear();
        try {
            em.getTransaction().begin();
            em.persist(reporteGenerado);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            IsspolLogger.getInstance().error("Error al guardarReporteGenerado ", ex);
            throw new ReportException("No se pudo guardar el Reporte generado.", ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizaReporteGenerado(ReporteGenerado reporteGenerado) throws ReportException {
        EntityManager em = entityManager;
        em.clear();
        try {
            em.getTransaction().begin();

            ReporteGenerado tmp = em.find(ReporteGenerado.class, reporteGenerado.getIdReporteGenerado());
            tmp.setModificaUsuario(reporteGenerado.getModificaUsuario());
            tmp.setModificaFecha(reporteGenerado.getModificaFecha());
            tmp.setModificaEquipo(reporteGenerado.getModificaEquipo());
            tmp.setVariables(reporteGenerado.getVariables());

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            IsspolLogger.getInstance().error("Error al actualizaReporteGenerado ", ex);
            throw new ReportException("No se pudo actualizar el Reporte generado.", ex);
        } finally {
            em.close();
        }
    }

    @Override
    public List<DatoCabecera> procReporteObtenerCabecera(Map<String, Object> parametros) throws ReportException {
        try {
            Map<String, Object> parametrosSP = new HashMap<>();

            parametrosSP.put("AS_IDENTIFICADOR", MapUtils.getString(parametros, "identificador_reporte", MapUtils.getString(parametros, "identificadorReporte")));
            parametrosSP.put("ID_REPORTE_GENERADO", MapUtils.getInteger(parametros, "id_reporte_generado", MapUtils.getInteger(parametros, "idReporteGenerado")));

            return obtenerReporteCabeceraSP(parametrosSP);

        } catch (Exception ex) {
            IsspolLogger.getInstance().error("Error al procReporteObtenerCabecera ", ex);
            throw new ReportException("No se pudo generar procReporteObtenerCabecera.", ex);
        }
    }

    @Override
    public Boolean validaExpedienteMarcaAgua(Map<String, Object> parametros) throws ReportException {
        Boolean incluyeImagen = Boolean.TRUE;
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = entityManager.unwrap(Connection.class);

            String numeroParametros = generarNumeroTemplateParametrosProceimiento(parametros);

            callableStatement = connection.prepareCall("{? = CALL documento.func_dsm_validar_expediente_marca_agua(" + numeroParametros + ")}");
            agregarParametroProcedimiento(callableStatement, parametros, Types.BOOLEAN);
            callableStatement.execute();

            incluyeImagen = callableStatement.getBoolean(1);
        } catch (Exception e) {
            IsspolLogger.getInstance().error("Ocurrio un error ejecutar el procedimiento", e);
            throw new ReportException(e);
        } finally {
            try {
                if (ObjectUtils.notEqual(callableStatement, null)) {
                    callableStatement.close();
                }
                if (ObjectUtils.notEqual(connection, null)) {
                    connection.close();
                }
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error", e);
            }
        }
        return incluyeImagen;
    }

    @Override
    public List<DatoPie> procReporteObtenerPie(Map<String, Object> parametros, String idUsuario) throws ReportException {
        List<DatoPie> lista = new ArrayList<>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = entityManager.unwrap(Connection.class);
            String numeroParametros = generarNumeroTemplateParametrosProceimiento(parametros);
            callableStatement = connection.prepareCall("{CALL reporte.proc_obtener_datos_pie(" + numeroParametros + ")}");
            agregarParametroProcedimiento(callableStatement, parametros, null);
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                lista.add(new DatoPie(resultSet.getString("DIR_EMPRESA"), resultSet.getString("TEL_EMPRESA"), resultSet.getString("COR_EMPRESA"),
                        ImageIO.read(new ByteArrayInputStream(resultSet.getBytes("LOGO_PIE"))), resultSet.getTimestamp("fecha"),
                        resultSet.getInt("numero"), StringUtils.defaultIfEmpty(resultSet.getString("id_usuario"), idUsuario)));
            }
        } catch (Exception e) {
            IsspolLogger.getInstance().error("Ocurrio un error ejecutar el procedimiento", e);
            throw new ReportException(e);
        } finally {
            try {
                if (ObjectUtils.notEqual(callableStatement, null)) {
                    callableStatement.close();
                }
                if (ObjectUtils.notEqual(connection, null)) {
                    connection.close();
                }
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error", e);
            }
        }
        return lista;
    }

    @Override
    public List<DatoCabecera> obtenerReporteCabeceraSP(Map<String, Object> parametros) throws ReportException {
        List<DatoCabecera> lista = new ArrayList<>();
        Connection connection = null;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            connection = entityManager.unwrap(Connection.class);
            String numeroParametros = generarNumeroTemplateParametrosProceimiento(parametros);
            callableStatement = connection.prepareCall("{CALL reporte.proc_obtener_datos_cabecera(" + numeroParametros + ")}");
            agregarParametroProcedimiento(callableStatement, parametros, null);
            callableStatement.execute();
            resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                lista.add(new DatoCabecera(resultSet.getString("codigo_iso"), resultSet.getString("numero_revision_iso"),
                        resultSet.getString("fecha_iso"), resultSet.getString("nombre_empresa"),
                        ImageIO.read(new ByteArrayInputStream(resultSet.getBytes("logo_empresa"))),
                        resultSet.getString("fecha"), resultSet.getString("descripcion"), resultSet.getBoolean("mostrar"),
                        resultSet.getString("area"), resultSet.getString("departamento"), resultSet.getString("nombre"),
                        resultSet.getBoolean("area_imprime"), resultSet.getBoolean("departamento_imprime"),
                        resultSet.getBoolean("nombre_imprime"), resultSet.getBoolean("fecha_imprime")));
            }
        } catch (Exception e) {
            IsspolLogger.getInstance().error("Ocurrio un error ejecutar el procedimiento", e);
            throw new ReportException(e);
        } finally {
            try {
                if (ObjectUtils.notEqual(callableStatement, null)) {
                    callableStatement.close();
                }
                if (ObjectUtils.notEqual(connection, null)) {
                    connection.close();
                }
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error", e);
            }
        }
        return lista;
    }

    @Override
    public List<List<String>> retornarDatosResultSetSp(String nombreSP, Map<String, Object> parametros) throws ReportException {
        CallableStatement callableStatement = null;

        String numeroParametros = generarNumeroTemplateParametrosProceimiento(parametros);
        ResultSet resultSet = null;
        try {
            Connection connection = entityManager.unwrap(Connection.class);

            callableStatement = connection.prepareCall("{CALL " + nombreSP + "(" + numeroParametros + ")}");
            agregarParametroProcedimiento(callableStatement, parametros, null);
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();
        } catch (Exception e) {
            IsspolLogger.getInstance().error("Ocurrio un error ejecutar el procedimiento", e);
            throw new ReportException(e);
        }
        return buildReusltData(resultSet);
    }

    private List<List<String>> buildReusltData(ResultSet resultSet) throws ReportException {
        ResultSetMetaData resultSetMetaData = null;
        final List<List<String>> rowList = new LinkedList<>();
        int columnCount = 0;

        try {
            resultSetMetaData = resultSet.getMetaData();
            final List<String> columnList = new LinkedList<>();
            for (int indice = 1; indice <= resultSetMetaData.getColumnCount(); ++indice) {
                columnList.add(resultSetMetaData.getColumnName(indice));

            }
            rowList.add(columnList);
        } catch (SQLException e) {
            IsspolLogger.getInstance().error("Ocurrio un error al convertir el result set a una coleccion", e);
            throw new ReportException(e);
        }

        try {
            columnCount = resultSetMetaData.getColumnCount();
        } catch (SQLException e) {
            IsspolLogger.getInstance().error("Ocurrio un error tomar el tamanio de las columnas de los metadatos", e);
            throw new ReportException(e);
        }

        try {
            while (resultSet.next()) {
                final List<String> columnList = new LinkedList<>();
                rowList.add(columnList);

                for (int column = 1; column <= columnCount; ++column) {
                    final Object value = resultSet.getObject(column);
                    columnList.add(String.valueOf(value));
                }
            }
        } catch (SQLException e) {
            IsspolLogger.getInstance().error("Ocurrio un error al generar la coleccion de el result set", e);
            throw new ReportException(e);
        }

        return rowList;
    }

    private void agregarParametroProcedimiento(CallableStatement callableStatement, Map<String, Object> parametros, Integer tipoSalida)
            throws ReportException {
        if (tipoSalida != null) {
            try {
                callableStatement.registerOutParameter(1, tipoSalida);
            } catch (Exception e) {
                IsspolLogger.getInstance().error("Error al agregar el parametro OUT al procedimiento", e);
                throw new ReportException(e);
            }
        }
        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            try {
                callableStatement.setObject(entry.getKey(), entry.getValue());
            } catch (SQLException e) {
                IsspolLogger.getInstance().error("Ocurrio un error al agregar el parametro al procedimiento", e);
                throw new ReportException(e);
            }
        }
    }

    private String generarNumeroTemplateParametrosProceimiento(Map<String, Object> parametros) {
        int tamanioParametro = parametros != null ? parametros.size() : 0;
        String numeroParametros = "";
        if (tamanioParametro != 0) {
            for (int indice = 0; indice < tamanioParametro; indice++) {
                numeroParametros += "?";
                if (indice + 1 < tamanioParametro) {
                    numeroParametros += ", ";
                }
            }
        }

        return numeroParametros;
    }
}

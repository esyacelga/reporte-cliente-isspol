package ec.org.isspol.mic.reporte.persistence.service.impl;

import ec.org.isspol.common.IsspolPersistException;
import ec.org.isspol.common.IsspolSearchException;
import ec.org.isspol.log.IsspolLogger;
import ec.org.isspol.mic.reporte.persistence.entities.expediente.Expediente;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.ReporteGenerado;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.ReporteSubreporte;
import ec.org.isspol.mic.reporte.persistence.service.ReporteRepositoryCustom;
import ec.org.isspol.mic.reporte.pojo.ext.DatoCabecera;
import ec.org.isspol.mic.reporte.pojo.ext.DatoPie;
import ec.org.isspol.mic.reporte.shared.UtilReporteIsspol;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.ByteArrayInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReporteRepositoryCustomImpl implements ReporteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Reporte findReporte(String nameReport) throws IsspolSearchException {
        entityManager.clear();
        Query query = entityManager.createNamedQuery("Reporte.findByIdentificador").setParameter("identificador", nameReport);
        try {
            Reporte reporte = (Reporte) query.getSingleResult();
            entityManager.getEntityManagerFactory().getCache().evict(Reporte.class, reporte.getIdReporte());
            return reporte;
        } catch (Exception ex) {
            IsspolLogger.getInstance().error("Error al recuperar ", ex);
            throw new IsspolSearchException("No se encontro el reporte con el identificador: " + nameReport, ex);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Boolean ejecutaFuncionValidarExpedienteAgua(Map<String, Object> parametros) throws IsspolSearchException {
        Boolean incluyeImagen = Boolean.TRUE;
        Connection connection = entityManager.unwrap(Connection.class);
        CallableStatement callableStatement = null;
        try {
            String numeroParametros = UtilReporteIsspol.getInstance().generarNumeroTemplateParametrosProceimiento(parametros);
            callableStatement = connection.prepareCall("{? = CALL documento.func_dsm_validar_expediente_marca_agua(" + numeroParametros + ")}");
            UtilReporteIsspol.getInstance().agregarParametroProcedimiento(callableStatement, parametros, Types.BOOLEAN);
            callableStatement.execute();
            incluyeImagen = callableStatement.getBoolean(1);
        } catch (Exception e) {
            IsspolLogger.getInstance().error("Ocurrio un error ejecutar el procedimiento", e);
            throw new IsspolSearchException(e);
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
    public List<DatoPie> ejecutaSpReporteObtenerPie(Map<String, Object> parametros, String idUsuario) throws IsspolSearchException {
        List<DatoPie> lista = new ArrayList<>();
        Connection connection = entityManager.unwrap(Connection.class);
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            String numeroParametros = UtilReporteIsspol.getInstance().generarNumeroTemplateParametrosProceimiento(parametros);

            callableStatement = connection.prepareCall("{CALL reporte.proc_obtener_datos_pie(" + numeroParametros + ")}");
            UtilReporteIsspol.getInstance().agregarParametroProcedimiento(callableStatement, parametros, null);
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();

            while (resultSet.next()) {
                lista.add(new DatoPie(resultSet.getString("DIR_EMPRESA"), resultSet.getString("TEL_EMPRESA"), resultSet.getString("COR_EMPRESA"),
                        ImageIO.read(new ByteArrayInputStream(resultSet.getBytes("LOGO_PIE"))), resultSet.getTimestamp("fecha"),
                        resultSet.getInt("numero"), StringUtils.defaultIfEmpty(resultSet.getString("id_usuario"), idUsuario)));
            }
        } catch (Exception e) {
            IsspolLogger.getInstance().error("Ocurrio un error ejecutar el procedimiento", e);
            throw new IsspolSearchException(e);
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
    public List<DatoCabecera> ejecutaSpObtenerCabecera(Map<String, Object> parametros) throws IsspolSearchException {
        List<DatoCabecera> lista = new ArrayList<>();
        Connection connection = entityManager.unwrap(Connection.class);
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            String numeroParametros = UtilReporteIsspol.getInstance().generarNumeroTemplateParametrosProceimiento(parametros);
            callableStatement = connection.prepareCall("{CALL reporte.proc_obtener_datos_cabecera(" + numeroParametros + ")}");
            UtilReporteIsspol.getInstance().agregarParametroProcedimiento(callableStatement, parametros, null);
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
            throw new IsspolSearchException(e);
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
    public void actualizaReporteGenerado(ReporteGenerado reporteGenerado) throws IsspolPersistException {
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
            throw new IsspolPersistException("No se pudo actualizar el Reporte generado.", ex);
        } finally {
            em.close();
        }
    }

    @Override
    public void guardarReporteGenerado(ReporteGenerado reporteGenerado) throws IsspolPersistException {
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
            throw new IsspolPersistException("No se pudo guardar el Reporte generado.", ex);
        } finally {
            em.close();
        }
    }

    @Override
    public Collection<ReporteSubreporte> buscarSubReportePorIdReporte(Integer idReportePadre) throws IsspolSearchException {
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
    public Boolean funDsmValidarExpedienteMarcaAgua(Map<String, Object> parametros) throws IsspolSearchException {
        try {
            Map<String, Object> parametrosSP = new LinkedHashMap<>();

            parametrosSP.put("AS_ID_USUARIO", MapUtils.getString(parametros, "idUsuario"));
            parametrosSP.put("AI_ID_GRUPO", MapUtils.getInteger(parametros, "idGrupo"));

            return ejecutaFuncionValidarExpedienteAgua(parametrosSP);

        } catch (Exception ex) {
            IsspolLogger.getInstance().error("Error al funDsmValidarExpedienteMarcaAgua ", ex);
            throw new IsspolSearchException("No se pudo generar funDsmValidarExpedienteMarcaAgua.", ex);
        }
    }


    public Expediente findExpediente(Integer identificador, String cedula) throws IsspolSearchException {
        EntityManager em = entityManager;
        em.clear();
        Query query = em.createNamedQuery("Expediente.findByIdentificador").setParameter("idExpediente", identificador).setParameter("cedula", cedula);

        try {
            Expediente expediente = (Expediente) query.getSingleResult();
            em.getEntityManagerFactory().getCache().evict(Expediente.class, expediente.getIdExpediente());
            return expediente;
        } catch (Exception ex) {
            IsspolLogger.getInstance().error("Error al recuperar ", ex);
            throw new IsspolSearchException("No se encontro el expediente con el identificador: " + identificador, ex);
        } finally {
            em.close();
        }
    }

    @Override
    public byte[] buscarExpediente(Map<String, Object> reporteParametro) throws IsspolSearchException {
        try {
            Integer idExpediente = (Integer) reporteParametro.get("idExpediente");
            String ciAfiliado = (String) reporteParametro.get("ciAfiliado");

            Boolean incluyeMarcaAgua = ejecutaFuncionValidarExpedienteAgua(reporteParametro);

            byte[] expedienteResult = findExpediente(idExpediente, ciAfiliado).getVAR_IMAGEN();

            if (incluyeMarcaAgua) {
                expedienteResult = UtilReporteIsspol.getInstance().waterMarkTiff(expedienteResult, (String) reporteParametro.get("idUsuario"));
            }

            return expedienteResult;
        } catch (IsspolSearchException e) {
            IsspolLogger.getInstance().error("Ocurrio un error al buscarExpediente", e);
            throw e;
        }
    }


}

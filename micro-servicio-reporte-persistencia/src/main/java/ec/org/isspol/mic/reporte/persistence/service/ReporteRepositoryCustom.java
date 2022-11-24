package ec.org.isspol.mic.reporte.persistence.service;

import ec.org.isspol.common.IsspolPersistException;
import ec.org.isspol.common.IsspolProcessException;
import ec.org.isspol.common.IsspolSearchException;
import ec.org.isspol.mic.reporte.persistence.entities.expediente.Expediente;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.ReporteGenerado;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.ReporteSubreporte;
import ec.org.isspol.mic.reporte.pojo.ext.DatoCabecera;
import ec.org.isspol.mic.reporte.pojo.ext.DatoPie;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ReporteRepositoryCustom {


    /**
     *
     * @param parametros
     * @return
     * @throws IsspolSearchException
     */
    public List<DatoCabecera> procReporteObtenerCabecera(Map<String, Object> parametros) throws IsspolSearchException;


    /**
     *
     * @param parametros
     * @return
     * @throws IsspolSearchException
     */
    public List<DatoPie> procReporteObtenerPie(Map<String, Object> parametros) throws IsspolSearchException ;


    /**
     *
     * @param reporte
     * @return
     */
    public Collection<ReporteSubreporte> buscarSubReportePorIdReporte(Reporte reporte);

    /**
     * @param nombreReporte
     * @return
     * @throws IsspolSearchException
     */
    public byte[] generaReporteRepository(String nombreReporte, Map<String, Object> parametros, boolean guardarEnBase, String mimeType, boolean dynamicReport, boolean esExcel) throws IsspolProcessException, IsspolSearchException;

    /**
     * @param nameReport
     * @return
     * @throws IsspolSearchException
     */
    public Reporte findReporte(String nameReport) throws IsspolSearchException;

    /**
     * @param parametros
     * @return
     * @throws IsspolSearchException
     */
    public Boolean ejecutaFuncionValidarExpedienteAgua(Map<String, Object> parametros) throws IsspolSearchException;

    /**
     * @param parametros
     * @param idUsuario
     * @return
     * @throws IsspolSearchException
     */
    public List<DatoPie> ejecutaSpReporteObtenerPie(Map<String, Object> parametros, String idUsuario) throws IsspolSearchException;

    /**
     * @param parametros
     * @return
     * @throws IsspolSearchException
     */
    public List<DatoCabecera> ejecutaSpObtenerCabecera(Map<String, Object> parametros) throws IsspolSearchException;

    /**
     * @param reporteGenerado
     * @throws IsspolSearchException
     */
    public void actualizaReporteGenerado(ReporteGenerado reporteGenerado) throws IsspolPersistException;


    /**
     * @param reporteGenerado
     * @throws IsspolPersistException
     */
    public void guardarReporteGenerado(ReporteGenerado reporteGenerado) throws IsspolPersistException;


    /**
     * @param idReportePadre
     * @return
     */
    public Collection<ReporteSubreporte> buscarSubReportePorIdReporte(Integer idReportePadre) throws IsspolSearchException;


    /**
     * @param parametros
     * @return
     * @throws IsspolSearchException
     */
    public Boolean funDsmValidarExpedienteMarcaAgua(Map<String, Object> parametros) throws IsspolSearchException;

    /**
     * @param identificador
     * @param cedula
     * @return
     * @throws IsspolSearchException
     */
    public Expediente findExpediente(Integer identificador, String cedula) throws IsspolSearchException;


    /**
     * @param reporteParametro
     * @return
     * @throws IsspolProcessException
     */
    public byte[] buscarExpediente(Map<String, Object> reporteParametro) throws IsspolSearchException;


}

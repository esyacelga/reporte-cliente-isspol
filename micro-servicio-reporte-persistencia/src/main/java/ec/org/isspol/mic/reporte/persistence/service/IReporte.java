package ec.org.isspol.mic.reporte.persistence.service;

import ec.org.isspol.mic.reporte.persistence.entities.ext.DatoCabecera;
import ec.org.isspol.mic.reporte.persistence.entities.ext.DatoPie;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.ReporteGenerado;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.ReporteSubreporte;
import ec.org.isspol.mic.reporte.shared.exception.ReportException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IReporte {

    public Reporte findReporte(String nameReport) throws ReportException;

    public Collection<ReporteSubreporte> buscarSubReportePorIdReporte(Integer idReportePadre) throws ReportException;

    public void guardarReporteGenerado(ReporteGenerado reporteGenerado) throws ReportException;

    public void actualizaReporteGenerado(ReporteGenerado reporteGenerado) throws ReportException;

    public List<DatoCabecera> procReporteObtenerCabecera(Map<String, Object> parametros) throws ReportException;


    public Boolean validaExpedienteMarcaAgua(Map<String, Object> parametros) throws ReportException;

    public List<DatoPie> procReporteObtenerPie(Map<String, Object> parametros, String idUsuario) throws ReportException;

    public List<DatoCabecera> obtenerReporteCabeceraSP(Map<String, Object> parametros) throws ReportException;

    public List<List<String>> retornarDatosResultSetSp(String nombreSP, Map<String, Object> parametros) throws ReportException;
}

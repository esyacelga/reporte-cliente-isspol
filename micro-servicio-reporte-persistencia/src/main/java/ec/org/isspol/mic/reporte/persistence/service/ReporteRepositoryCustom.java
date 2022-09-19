package ec.org.isspol.mic.reporte.persistence.service;

import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;

public interface ReporteRepositoryCustom {

    public Reporte findReporte(String nameReport) throws Exception;


}

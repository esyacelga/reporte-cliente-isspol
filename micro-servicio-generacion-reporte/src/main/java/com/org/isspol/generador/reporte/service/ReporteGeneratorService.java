package com.org.isspol.generador.reporte.service;

import ec.org.isspol.common.IsspolSearchException;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;

public interface ReporteGeneratorService {

    public Reporte obtieneReportePorId(int idReporte) throws IsspolSearchException;

}

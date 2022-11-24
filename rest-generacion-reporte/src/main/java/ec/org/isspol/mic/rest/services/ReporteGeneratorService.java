package ec.org.isspol.mic.rest.services;


import ec.org.isspol.common.IsspolSearchException;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;

public interface ReporteGeneratorService {

    public Reporte obtieneReportePorId(int idReporte) throws IsspolSearchException;

}

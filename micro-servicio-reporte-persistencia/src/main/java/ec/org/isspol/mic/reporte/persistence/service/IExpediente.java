package ec.org.isspol.mic.reporte.persistence.service;

import ec.org.isspol.mic.reporte.persistence.entities.expediente.Expediente;
import ec.org.isspol.mic.reporte.shared.exception.ReportException;

public interface IExpediente {

    public Expediente findExpediente(Integer identificador, String cedula) throws ReportException;
}

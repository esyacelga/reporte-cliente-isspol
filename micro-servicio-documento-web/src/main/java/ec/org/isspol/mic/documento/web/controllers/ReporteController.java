package ec.org.isspol.mic.documento.web.controllers;

import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.service.IReporte;
import ec.org.isspol.mic.reporte.shared.exception.ReportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReporteController {

    @Autowired
    private IReporte iReporte;


    @GetMapping("/buscarReportePorNombre/{nombreReporte}")
    public Reporte findReporte(@PathVariable String nombreReporte) throws ReportException {
        return iReporte.findReporte(nombreReporte);
    }


}

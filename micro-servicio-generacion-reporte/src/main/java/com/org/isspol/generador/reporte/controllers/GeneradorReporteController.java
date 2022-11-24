package com.org.isspol.generador.reporte.controllers;

import com.org.isspol.generador.reporte.service.ReporteGeneratorService;
import ec.org.isspol.common.IsspolSearchException;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneradorReporteController {

    @Autowired
    private ReporteGeneratorService reporteGeneratorService;

    @GetMapping("buscarReporte/id")
    public Reporte buscarReporte(@PathVariable int id) throws IsspolSearchException {
        return reporteGeneratorService.obtieneReportePorId(id);
    }
}

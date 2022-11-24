package com.org.isspol.generador.reporte.service.impl;

import com.org.isspol.generador.reporte.service.ReporteGeneratorService;
import ec.org.isspol.common.IsspolSearchException;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReporteGeneratorServiceImpl implements ReporteGeneratorService {

    @Autowired
    private RestTemplate clienteRest;


    @Override
    public Reporte obtieneReportePorId(int idReporte) throws IsspolSearchException {
        Reporte reporte = clienteRest.getForObject("http://localhost:8001/listar/25", Reporte.class);
        return reporte;
    }
}

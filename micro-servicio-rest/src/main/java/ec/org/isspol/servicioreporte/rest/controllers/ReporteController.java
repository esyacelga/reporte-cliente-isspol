package ec.org.isspol.servicioreporte.rest.controllers;

import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.service.IReporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReporteController {

    @Autowired
    private IReporte iReporte;

    @RequestMapping("/")
    public String home() {
        return "Hello World........";
    }

    @GetMapping("/reportes")
    public List<Reporte> getHotels() {
        List<Reporte> hotels = this.iReporte.findAll();
        return hotels;
    }
}
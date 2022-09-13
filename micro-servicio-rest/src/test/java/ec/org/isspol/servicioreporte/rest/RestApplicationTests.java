package ec.org.isspol.servicioreporte.rest;

import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.service.IReporte;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RestApplicationTests {


    @Autowired
    private IReporte iReporte;

    @Test
    void contextLoads() {
        List<Reporte> lista = iReporte.findAll();
        lista.size();
    }

}

package ec.org.isspol.servicioreporte.rest;

import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import ec.org.isspol.mic.reporte.persistence.service.ReporteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class RestApplicationTests {


    @Autowired
    private ReporteRepository iReporte;

/*    @Test
    void contextLoads() {
        List<Reporte> lista = iReporte.findAll();
        lista.size();
    }*/

    @Test
    void buscarReportePorId() {
        Optional<Reporte> reporte= iReporte.findById(25);
        System.out.println(reporte.get().getDescripcion());

    }

}

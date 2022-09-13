package ec.org.isspol.mic.reporte.persistence;

import ec.org.isspol.mic.reporte.persistence.service.impl.ReporteImpl;
import ec.org.isspol.mic.reporte.shared.exception.ReportException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReporteTest {

    @Autowired
    private ReporteImpl reporteImpl;

    @Test
    void obtenerReporte() throws ReportException {
        assertThat(reporteImpl.findReporte("reporte-autorizacion-debito"));

    }
}

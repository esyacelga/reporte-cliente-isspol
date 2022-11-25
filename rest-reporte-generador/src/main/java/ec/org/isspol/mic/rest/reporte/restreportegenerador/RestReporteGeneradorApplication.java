package ec.org.isspol.mic.rest.reporte.restreportegenerador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class RestReporteGeneradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestReporteGeneradorApplication.class, args);
    }

}

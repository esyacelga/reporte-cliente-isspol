package ec.org.isspol.mic.documento.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication (scanBasePackages={
        "ec.org.isspol.mic.reporte.persistence.service","javax.persistence"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})

public class ClienteDocumentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClienteDocumentalApplication.class, args);
    }

}

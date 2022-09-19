package ec.org.isspol.servicioreporte.rest;

import ec.org.isspol.mic.reporte.persistence.service.impl.ReporteRepositoryCustomImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ec.org.isspol.mic.reporte.persistence"}, basePackageClasses = ReporteRepositoryCustomImpl.class)
@EntityScan(basePackages = {"ec.org.isspol.mic.reporte.persistence.entities.reporte"})
@ComponentScan(basePackages = {"ec.org.isspol.servicioreporte.rest.controllers"})
public class RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

}

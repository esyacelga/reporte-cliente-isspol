package ec.org.isspol.mic.reporte.persistence.service;

import ec.org.isspol.mic.reporte.alternativo.ReporteRepositoryCustom;
import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer>, ReporteRepositoryCustom {
}

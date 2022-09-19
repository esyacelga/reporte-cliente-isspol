package ec.org.isspol.mic.reporte.alternativo;

import ec.org.isspol.mic.reporte.persistence.entities.reporte.Reporte;

import java.util.List;

public interface ReporteRepositoryCustom {

    List<Reporte> filterBy(String role);


}

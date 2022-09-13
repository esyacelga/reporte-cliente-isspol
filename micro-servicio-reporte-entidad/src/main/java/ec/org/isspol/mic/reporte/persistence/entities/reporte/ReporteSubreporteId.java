package ec.org.isspol.mic.reporte.persistence.entities.reporte;

// Generated 21-ene-2019 10:22:11
/**
* Copyright 2017
* INSTITUTO DE SEGURIDAD SOCIAL DE LA POLICIA NACIONAL - ECUADOR
* Todos los derechos reservados
*/

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.ObjectStreamClass;

@Embeddable
public class ReporteSubreporteId implements java.io.Serializable {

	private static final long serialVersionUID = ObjectStreamClass.lookup(ReporteSubreporteId.class)
			.getSerialVersionUID();

	@Column(name = "id_reporte_padre", nullable = false)
	private Integer idReportePadre;

	@Column(name = "id_reporte_hijo", nullable = false)
	private Integer idReporteHijo;

	public ReporteSubreporteId() {
	}

	public Integer getIdReportePadre() {
		return this.idReportePadre;
	}

	public void setIdReportePadre(Integer idReportePadre) {
		this.idReportePadre = idReportePadre;
	}

	public Integer getIdReporteHijo() {
		return this.idReporteHijo;
	}

	public void setIdReporteHijo(Integer idReporteHijo) {
		this.idReporteHijo = idReporteHijo;
	}

}

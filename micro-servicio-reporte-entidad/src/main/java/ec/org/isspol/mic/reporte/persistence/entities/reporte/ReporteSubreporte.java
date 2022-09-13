package ec.org.isspol.mic.reporte.persistence.entities.reporte;

// Generated 21-ene-2019 10:22:11
/**
 * Copyright 2017
 * INSTITUTO DE SEGURIDAD SOCIAL DE LA POLICIA NACIONAL - ECUADOR
 * Todos los derechos reservados
 */

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.ObjectStreamClass;
import java.util.Date;

@Entity
@Table(name = "reporte_subreporte", schema = "reporte")
@NamedQueries({
        @NamedQuery(name = "ReporteSubReporte.findByIdReportePadre", query = "SELECT r FROM ReporteSubreporte r JOIN r.reporteByIdReporteHijo h where r.reporteByIdReportePadre.idReporte=:idReportePadre")})
public class ReporteSubreporte implements java.io.Serializable {

    private static final long serialVersionUID = ObjectStreamClass.lookup(ReporteSubreporte.class)
            .getSerialVersionUID();

    @EmbeddedId

    @AttributeOverrides({
            @AttributeOverride(name = "idReportePadre", column = @Column(name = "id_reporte_padre", nullable = false)),
            @AttributeOverride(name = "idReporteHijo", column = @Column(name = "id_reporte_hijo", nullable = false))})
    private ReporteSubreporteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reporte_hijo", nullable = false, insertable = false, updatable = false)
    private Reporte reporteByIdReporteHijo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reporte_padre", nullable = false, insertable = false, updatable = false)
    private Reporte reporteByIdReportePadre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "parametro")
    private String parametro;

    @Basic(optional = false)
    @Column(name = "creacion_usuario")
    private String creacionUsuario;

    @Basic(optional = false)
    @Column(name = "creacion_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creacionFecha;

    @Basic(optional = false)
    @Column(name = "creacion_equipo")
    private String creacionEquipo;

    @Column(name = "modifica_usuario")
    private String modificaUsuario;

    @Column(name = "modifica_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificaFecha;

    @Column(name = "modifica_equipo")
    private String modificaEquipo;

    public ReporteSubreporte() {
    }

    public ReporteSubreporteId getId() {
        return this.id;
    }

    public void setId(ReporteSubreporteId id) {
        this.id = id;
    }

    public Reporte getReporteByIdReporteHijo() {
        return this.reporteByIdReporteHijo;
    }

    public void setReporteByIdReporteHijo(Reporte reporteByIdReporteHijo) {
        this.reporteByIdReporteHijo = reporteByIdReporteHijo;
    }

    public Reporte getReporteByIdReportePadre() {
        return this.reporteByIdReportePadre;
    }

    public void setReporteByIdReportePadre(Reporte reporteByIdReportePadre) {
        this.reporteByIdReportePadre = reporteByIdReportePadre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getParametro() {
        return this.parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getCreacionUsuario() {
        return creacionUsuario;
    }

    public void setCreacionUsuario(String creacionUsuario) {
        this.creacionUsuario = creacionUsuario;
    }

    public Date getCreacionFecha() {
        return creacionFecha;
    }

    public void setCreacionFecha(Date creacionFecha) {
        this.creacionFecha = creacionFecha;
    }

    public String getCreacionEquipo() {
        return creacionEquipo;
    }

    public void setCreacionEquipo(String creacionEquipo) {
        this.creacionEquipo = creacionEquipo;
    }

    public String getModificaUsuario() {
        return modificaUsuario;
    }

    public void setModificaUsuario(String modificaUsuario) {
        this.modificaUsuario = modificaUsuario;
    }

    public Date getModificaFecha() {
        return modificaFecha;
    }

    public void setModificaFecha(Date modificaFecha) {
        this.modificaFecha = modificaFecha;
    }

    public String getModificaEquipo() {
        return modificaEquipo;
    }

    public void setModificaEquipo(String modificaEquipo) {
        this.modificaEquipo = modificaEquipo;
    }
}

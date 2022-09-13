package ec.org.isspol.mic.reporte.persistence.entities.reporte;

// Generated 21-ene-2019 10:22:11
/**
 * Copyright 2017
 * INSTITUTO DE SEGURIDAD SOCIAL DE LA POLICIA NACIONAL - ECUADOR
 * Todos los derechos reservados
 */

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.ObjectStreamClass;
import java.util.Date;

@Entity
@Table(name = "reporte_generado", schema = "reporte")
public class ReporteGenerado implements java.io.Serializable {

    private static final long serialVersionUID = ObjectStreamClass.lookup(ReporteGenerado.class)
            .getSerialVersionUID();

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idReporteGenerado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reporte", nullable = false)
    private Reporte reporte;

    @Column(name = "variables")
    private String variables;

    @Column(name = "secuencial")
    private String secuencial;

    @Column(name = "token")
    private String token;

    @Basic(optional = false)
    @Column(name = "eliminado")
    private boolean eliminado;

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

    public ReporteGenerado() {
    }

    public Integer getIdReporteGenerado() {
        return idReporteGenerado;
    }

    public void setIdReporteGenerado(Integer idReporteGenerado) {
        this.idReporteGenerado = idReporteGenerado;
    }

    public Reporte getReporte() {
        return reporte;
    }

    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
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

    public String getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.org.isspol.mic.reporte.persistence.entities.reporte;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author roberto.chasipanta
 */
@Entity
@Table(name = "reporte", schema = "reporte")
@NamedQueries({
        @NamedQuery(name = "Reporte.findByIdentificador", query = "SELECT r FROM Reporte r where r.identificador=:identificador")})
public class Reporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Integer idReporte;

    @Basic(optional = false)
    @Column(name = "identificador")
    private String identificador;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Lob
    @Column(name = "plantilla")
    private byte[] plantilla;

    @Lob
    @Column(name = "recursobundle")
    private byte[] recursobundle;

    @Lob
    @Column(name = "firma")
    private byte[] firma;

    @Transient
    private String nota;

    @Transient
    private String secuencial;

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

    @Column(name = "plantilla_excel")
    private byte[] plantillaExcel;

    @Column(name = "nombre_plantilla_excel", length = 256)
    private String nombrePlantillaExcel;

    @OneToMany(mappedBy = "reporteByIdReporteHijo", fetch = FetchType.LAZY)
    private Collection<ReporteSubreporte> reporteSubreportesHijo;

    @OneToMany(mappedBy = "reporteByIdReportePadre", fetch = FetchType.LAZY)
    private Collection<ReporteSubreporte> reporteSubreportesPadre;

    @OneToMany(mappedBy = "reporte", fetch = FetchType.LAZY)
    private Collection<ReporteGenerado> reporteGenerados;

    @Basic(optional = false)
    @Column(name = "qr_imprime")
    private Boolean qrImprime;

    public Reporte() {
    }

    public Reporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Reporte(String identificador, byte[] plantilla, byte[] plantillaExcel, String creacionUsuario, Date creacionFecha, String creacionEquipo) {
        this.identificador = identificador;
        this.plantilla = plantilla;
        this.plantillaExcel = plantillaExcel;
        this.eliminado = eliminado;
        this.creacionUsuario = creacionUsuario;
        this.creacionFecha = creacionFecha;
        this.creacionEquipo = creacionEquipo;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(byte[] plantilla) {
        this.plantilla = plantilla;
    }

    public byte[] getRecursobundle() {
        return recursobundle;
    }

    public void setRecursobundle(byte[] recursobundle) {
        this.recursobundle = recursobundle;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
    }

    public boolean getEliminado() {
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

    public byte[] getPlantillaExcel() {
        return plantillaExcel;
    }

    public void setPlantillaExcel(byte[] plantillaExcel) {
        this.plantillaExcel = plantillaExcel;
    }

    public String getNombrePlantillaExcel() {
        return nombrePlantillaExcel;
    }

    public void setNombrePlantillaExcel(String nombrePlantillaExcel) {
        this.nombrePlantillaExcel = nombrePlantillaExcel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReporte != null ? idReporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reporte)) {
            return false;
        }
        Reporte other = (Reporte) object;
        if ((this.idReporte == null && other.idReporte != null) || (this.idReporte != null && !this.idReporte.equals(other.idReporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.org.isspol.persistence.entities.reporte.Reporte[ idReporte=" + idReporte + " ]";
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public Collection<ReporteSubreporte> getReporteSubreportesHijo() {
        return reporteSubreportesHijo;
    }

    public void setReporteSubreportesHijo(Collection<ReporteSubreporte> reporteSubreportesHijo) {
        this.reporteSubreportesHijo = reporteSubreportesHijo;
    }

    public Collection<ReporteSubreporte> getReporteSubreportesPadre() {
        return reporteSubreportesPadre;
    }

    public void setReporteSubreportesPadre(Collection<ReporteSubreporte> reporteSubreportesPadre) {
        this.reporteSubreportesPadre = reporteSubreportesPadre;
    }

    public Collection<ReporteGenerado> getReporteGenerados() {
        return reporteGenerados;
    }

    public void setReporteGenerados(Collection<ReporteGenerado> reporteGenerados) {
        this.reporteGenerados = reporteGenerados;
    }

    public Boolean getQrImprime() {
        return qrImprime;
    }

    public void setQrImprime(Boolean qrImprime) {
        this.qrImprime = qrImprime;
    }
}

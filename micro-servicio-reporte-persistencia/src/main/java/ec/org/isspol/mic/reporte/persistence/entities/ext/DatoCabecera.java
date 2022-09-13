package ec.org.isspol.mic.reporte.persistence.entities.ext;

import java.awt.*;

public class DatoCabecera {

    private String codigo_iso;
    private String numero_revision_iso;
    private String fecha_iso;
    private String nombre_empresa;
    private Image logo_empresa;
    private String fecha;
    private String descripcion;
    private Boolean mostrar;
    private String area;
    private String departamento;
    private String nombre;
    private Boolean area_imprime;
    private Boolean departamento_imprime;
    private Boolean nombre_imprime;
    private Boolean fecha_imprime;

    public DatoCabecera() {

    }

    public DatoCabecera(String codigo_iso, String numero_revision_iso, String fecha_iso, String nombre_empresa, Image logo_empresa, String fecha, String descripcion, Boolean mostrar, String area, String departamento, String nombre, Boolean area_imprime, Boolean departamento_imprime, Boolean nombre_imprime, Boolean fecha_imprime) {
        this.codigo_iso = codigo_iso;
        this.numero_revision_iso = numero_revision_iso;
        this.fecha_iso = fecha_iso;
        this.nombre_empresa = nombre_empresa;
        this.logo_empresa = logo_empresa;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.mostrar = mostrar;
        this.area = area;
        this.departamento = departamento;
        this.nombre = nombre;
        this.area_imprime = area_imprime;
        this.departamento_imprime = departamento_imprime;
        this.nombre_imprime = nombre_imprime;
        this.fecha_imprime = fecha_imprime;
    }

    public String getCodigo_iso() {
        return codigo_iso;
    }

    public void setCodigo_iso(String codigo_iso) {
        this.codigo_iso = codigo_iso;
    }

    public String getNumero_revision_iso() {
        return numero_revision_iso;
    }

    public void setNumero_revision_iso(String numero_revision_iso) {
        this.numero_revision_iso = numero_revision_iso;
    }

    public String getFecha_iso() {
        return fecha_iso;
    }

    public void setFecha_iso(String fecha_iso) {
        this.fecha_iso = fecha_iso;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public Image getLogo_empresa() {
        return logo_empresa;
    }

    public void setLogo_empresa(Image logo_empresa) {
        this.logo_empresa = logo_empresa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getMostrar() {
        return mostrar;
    }

    public void setMostrar(Boolean mostrar) {
        this.mostrar = mostrar;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getArea_imprime() {
        return area_imprime;
    }

    public void setArea_imprime(Boolean area_imprime) {
        this.area_imprime = area_imprime;
    }

    public Boolean getDepartamento_imprime() {
        return departamento_imprime;
    }

    public void setDepartamento_imprime(Boolean departamento_imprime) {
        this.departamento_imprime = departamento_imprime;
    }

    public Boolean getNombre_imprime() {
        return nombre_imprime;
    }

    public void setNombre_imprime(Boolean nombre_imprime) {
        this.nombre_imprime = nombre_imprime;
    }

    public Boolean getFecha_imprime() {
        return fecha_imprime;
    }

    public void setFecha_imprime(Boolean fecha_imprime) {
        this.fecha_imprime = fecha_imprime;
    }
}

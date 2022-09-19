package ec.org.isspol.mic.reporte.pojo.ext;

import java.awt.*;
import java.sql.Timestamp;

public class DatoPie {

    private String DIR_EMPRESA;
    private String TEL_EMPRESA;
    private String COR_EMPRESA;
    private Image LOGO_PIE;
    private Timestamp fecha;
    private Integer numero;
    private String id_usuario;

    public DatoPie() {
    }

    public DatoPie(String DIR_EMPRESA, String TEL_EMPRESA, String COR_EMPRESA, Image LOGO_PIE, Timestamp fecha, Integer numero, String id_usuario) {
        this.DIR_EMPRESA = DIR_EMPRESA;
        this.TEL_EMPRESA = TEL_EMPRESA;
        this.COR_EMPRESA = COR_EMPRESA;
        this.LOGO_PIE = LOGO_PIE;
        this.fecha = fecha;
        this.numero = numero;
        this.id_usuario = id_usuario;
    }

    public String getDIR_EMPRESA() {
        return DIR_EMPRESA;
    }

    public void setDIR_EMPRESA(String DIR_EMPRESA) {
        this.DIR_EMPRESA = DIR_EMPRESA;
    }

    public String getTEL_EMPRESA() {
        return TEL_EMPRESA;
    }

    public void setTEL_EMPRESA(String TEL_EMPRESA) {
        this.TEL_EMPRESA = TEL_EMPRESA;
    }

    public String getCOR_EMPRESA() {
        return COR_EMPRESA;
    }

    public void setCOR_EMPRESA(String COR_EMPRESA) {
        this.COR_EMPRESA = COR_EMPRESA;
    }

    public Image getLOGO_PIE() {
        return LOGO_PIE;
    }

    public void setLOGO_PIE(Image LOGO_PIE) {
        this.LOGO_PIE = LOGO_PIE;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }
}

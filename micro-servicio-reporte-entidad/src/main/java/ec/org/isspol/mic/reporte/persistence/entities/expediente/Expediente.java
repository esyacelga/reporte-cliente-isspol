/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.org.isspol.mic.reporte.persistence.entities.expediente;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author diego.hurtado
 */
@Entity
@Table(name = "LIB_EXPEDIENTES_ISSPOL", schema = "dsm.dbo")
@NamedQueries({
        @NamedQuery(name = "Expediente.findByIdentificador", query = "SELECT e FROM Expediente e where e.idExpediente = :idExpediente and e.cedula = :cedula")})
public class Expediente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRAN")
    private Integer idExpediente;

    @Lob
    @Basic(optional = false)
    @Column(name = "IMAGEN")
    private byte[] VAR_IMAGEN;

    @Basic(optional = false)
    @Column(name = "CEDULA")
    private String cedula;

    public Expediente() {
    }

    public byte[] getVAR_IMAGEN() {
        return VAR_IMAGEN;
    }

    public void setVAR_IMAGEN(byte[] VAR_IMAGEN) {
        this.VAR_IMAGEN = VAR_IMAGEN;
    }

    public Integer getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}

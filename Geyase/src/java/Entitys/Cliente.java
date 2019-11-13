/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
    , @NamedQuery(name = "Cliente.findByCodCli", query = "SELECT c FROM Cliente c WHERE c.codCli = :codCli")
    , @NamedQuery(name = "Cliente.findByNomCli", query = "SELECT c FROM Cliente c WHERE c.nomCli = :nomCli")
    , @NamedQuery(name = "Cliente.findByApeCli", query = "SELECT c FROM Cliente c WHERE c.apeCli = :apeCli")
    , @NamedQuery(name = "Cliente.findBySexoCli", query = "SELECT c FROM Cliente c WHERE c.sexoCli = :sexoCli")
    , @NamedQuery(name = "Cliente.findByCedCli", query = "SELECT c FROM Cliente c WHERE c.cedCli = :cedCli")
    , @NamedQuery(name = "Cliente.findByTelCli", query = "SELECT c FROM Cliente c WHERE c.telCli = :telCli")
    , @NamedQuery(name = "Cliente.findByEmailCli", query = "SELECT c FROM Cliente c WHERE c.emailCli = :emailCli")
    , @NamedQuery(name = "Cliente.findByDirCli", query = "SELECT c FROM Cliente c WHERE c.dirCli = :dirCli")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_cli")
    private Integer codCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nom_cli")
    private String nomCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ape_cli")
    private String apeCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "sexo_cli")
    private String sexoCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cedCli")
    private String cedCli;
    @Basic(optional = false)
    @NotNull(message = "No puede estar nulo")
    @Size(min = 1, max = 200)
    @Column(name = "tel_cli")
    private String telCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email_cli")
    private String emailCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "dir_cli")
    private String dirCli;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codCli")
    private List<Factura> facturaList;

    public Cliente() {
    }

    public Cliente(Integer codCli) {
        this.codCli = codCli;
    }

    public Cliente(Integer codCli, String nomCli, String apeCli, String sexoCli, String cedCli, String telCli, String emailCli, String dirCli) {
        this.codCli = codCli;
        this.nomCli = nomCli;
        this.apeCli = apeCli;
        this.sexoCli = sexoCli;
        this.cedCli = cedCli;
        this.telCli = telCli;
        this.emailCli = emailCli;
        this.dirCli = dirCli;
    }

    public Integer getCodCli() {
        return codCli;
    }

    public void setCodCli(Integer codCli) {
        this.codCli = codCli;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getApeCli() {
        return apeCli;
    }

    public void setApeCli(String apeCli) {
        this.apeCli = apeCli;
    }

    public String getSexoCli() {
        return sexoCli;
    }

    public void setSexoCli(String sexoCli) {
        this.sexoCli = sexoCli;
    }

    public String getCedCli() {
        return cedCli;
    }

    public void setCedCli(String cedCli) {
        this.cedCli = cedCli;
    }

    public String getTelCli() {
        return telCli;
    }

    public void setTelCli(String telCli) {
        this.telCli = telCli;
    }

    public String getEmailCli() {
        return emailCli;
    }

    public void setEmailCli(String emailCli) {
        this.emailCli = emailCli;
    }

    public String getDirCli() {
        return dirCli;
    }

    public void setDirCli(String dirCli) {
        this.dirCli = dirCli;
    }

    @XmlTransient
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCli != null ? codCli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.codCli == null && other.codCli != null) || (this.codCli != null && !this.codCli.equals(other.codCli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Cliente[ codCli=" + codCli + " ]";
    }
    
}

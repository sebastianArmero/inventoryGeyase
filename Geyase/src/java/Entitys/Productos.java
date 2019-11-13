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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p")
    , @NamedQuery(name = "Productos.findByCodPro", query = "SELECT p FROM Productos p WHERE p.codPro = :codPro")
    , @NamedQuery(name = "Productos.findByDespro", query = "SELECT p FROM Productos p WHERE p.despro = :despro")
    , @NamedQuery(name = "Productos.findByPrepro", query = "SELECT p FROM Productos p WHERE p.prepro = :prepro")
    , @NamedQuery(name = "Productos.findByCantidadpro", query = "SELECT p FROM Productos p WHERE p.cantidadpro = :cantidadpro")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_pro")
    private Integer codPro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "despro")
    private String despro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prepro")
    private int prepro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadpro")
    private int cantidadpro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPro")
    private List<Detallefactura> detallefacturaList;

    @Transient
    private int cantidadSeleccionada;

    public Productos() {
    }

    public Productos(Integer codPro) {
        this.codPro = codPro;
    }

    public Productos(Integer codPro, String despro, int prepro, int cantidadpro) {
        this.codPro = codPro;
        this.despro = despro;
        this.prepro = prepro;
        this.cantidadpro = cantidadpro;
    }

    public Integer getCodPro() {
        return codPro;
    }

    public void setCodPro(Integer codPro) {
        this.codPro = codPro;
    }

    public String getDespro() {
        return despro;
    }

    public void setDespro(String despro) {
        this.despro = despro;
    }

    public int getPrepro() {
        return prepro;
    }

    public void setPrepro(int prepro) {
        this.prepro = prepro;
    }

    public int getCantidadpro() {
        return cantidadpro;
    }

    public void setCantidadpro(int cantidadpro) {
        this.cantidadpro = cantidadpro;
    }

    @XmlTransient
    public List<Detallefactura> getDetallefacturaList() {
        return detallefacturaList;
    }

    public void setDetallefacturaList(List<Detallefactura> detallefacturaList) {
        this.detallefacturaList = detallefacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPro != null ? codPro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.codPro == null && other.codPro != null) || (this.codPro != null && !this.codPro.equals(other.codPro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Productos[ codPro=" + codPro + " ]";
    }

    public int getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(int cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }

}

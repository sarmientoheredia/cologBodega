/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Cbos- Com. Sarmiento H. Luis A.
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rack.findAll", query = "SELECT r FROM Rack r"),
    @NamedQuery(name = "Rack.findByRackId", query = "SELECT r FROM Rack r WHERE r.rackId = :rackId"),
    @NamedQuery(name = "Rack.findByRackNombre", query = "SELECT r FROM Rack r WHERE r.rackNombre = :rackNombre"),
    @NamedQuery(name = "Rack.findByRackBorrLogi", query = "SELECT r FROM Rack r WHERE r.rackBorrLogi = :rackBorrLogi")})
public class Rack implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rack_Id")
    private Integer rackId;
    @Size(max = 20)
    @Column(name = "rack_Nombre")
    private String rackNombre;
    @Column(name = "rack_BorrLogi")
    private Boolean rackBorrLogi;
    @JoinColumn(name = "rack_Secc_Id", referencedColumnName = "secc_Id")
    @ManyToOne(optional = false)
    private Seccion rackSeccId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodRackId")
    private List<Producto> productoList;

    public Rack() {
    }

    public Rack(Integer rackId) {
        this.rackId = rackId;
    }

    public Integer getRackId() {
        return rackId;
    }

    public void setRackId(Integer rackId) {
        this.rackId = rackId;
    }

    public String getRackNombre() {
        return rackNombre;
    }

    public void setRackNombre(String rackNombre) {
        this.rackNombre = rackNombre;
    }

    public Boolean getRackBorrLogi() {
        return rackBorrLogi;
    }

    public void setRackBorrLogi(Boolean rackBorrLogi) {
        this.rackBorrLogi = rackBorrLogi;
    }

    public Seccion getRackSeccId() {
        return rackSeccId;
    }

    public void setRackSeccId(Seccion rackSeccId) {
        this.rackSeccId = rackSeccId;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rackId != null ? rackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rack)) {
            return false;
        }
        Rack other = (Rack) object;
        if ((this.rackId == null && other.rackId != null) || (this.rackId != null && !this.rackId.equals(other.rackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.entidades.Rack[ rackId=" + rackId + " ]";
    }
    
}

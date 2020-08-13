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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Seccion.findAll", query = "SELECT s FROM Seccion s"),
    @NamedQuery(name = "Seccion.findBySeccId", query = "SELECT s FROM Seccion s WHERE s.seccId = :seccId"),
    @NamedQuery(name = "Seccion.findBySeccNombre", query = "SELECT s FROM Seccion s WHERE s.seccNombre = :seccNombre"),
    @NamedQuery(name = "Seccion.findBySeccBorrLogi", query = "SELECT s FROM Seccion s WHERE s.seccBorrLogi = :seccBorrLogi")})
public class Seccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "secc_Id")
    private Integer seccId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "secc_Nombre")
    private String seccNombre;
    @Column(name = "secc_BorrLogi")
    private Boolean seccBorrLogi;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rackSeccId")
    private List<Rack> rackList;

    public Seccion() {
    }

    public Seccion(Integer seccId) {
        this.seccId = seccId;
    }

    public Seccion(Integer seccId, String seccNombre) {
        this.seccId = seccId;
        this.seccNombre = seccNombre;
    }

    public Integer getSeccId() {
        return seccId;
    }

    public void setSeccId(Integer seccId) {
        this.seccId = seccId;
    }

    public String getSeccNombre() {
        return seccNombre;
    }

    public void setSeccNombre(String seccNombre) {
        this.seccNombre = seccNombre;
    }

    public Boolean getSeccBorrLogi() {
        return seccBorrLogi;
    }

    public void setSeccBorrLogi(Boolean seccBorrLogi) {
        this.seccBorrLogi = seccBorrLogi;
    }

    @XmlTransient
    public List<Rack> getRackList() {
        return rackList;
    }

    public void setRackList(List<Rack> rackList) {
        this.rackList = rackList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seccId != null ? seccId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seccion)) {
            return false;
        }
        Seccion other = (Seccion) object;
        if ((this.seccId == null && other.seccId != null) || (this.seccId != null && !this.seccId.equals(other.seccId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.entidades.Seccion[ seccId=" + seccId + " ]";
    }
    
}

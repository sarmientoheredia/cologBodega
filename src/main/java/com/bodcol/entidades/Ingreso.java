/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Ingreso.findAll", query = "SELECT i FROM Ingreso i"),
    @NamedQuery(name = "Ingreso.findByIngrId", query = "SELECT i FROM Ingreso i WHERE i.ingrId = :ingrId"),
    @NamedQuery(name = "Ingreso.findByIngrFecha", query = "SELECT i FROM Ingreso i WHERE i.ingrFecha = :ingrFecha"),
    @NamedQuery(name = "Ingreso.findByIngrNumeFactura", query = "SELECT i FROM Ingreso i WHERE i.ingrNumeFactura = :ingrNumeFactura"),
    @NamedQuery(name = "Ingreso.findByIngrUnidMilitar", query = "SELECT i FROM Ingreso i WHERE i.ingrUnidMilitar = :ingrUnidMilitar"),
    @NamedQuery(name = "Ingreso.findByIngrTotal", query = "SELECT i FROM Ingreso i WHERE i.ingrTotal = :ingrTotal"),
    @NamedQuery(name = "Ingreso.findByIngrBorrLogi", query = "SELECT i FROM Ingreso i WHERE i.ingrBorrLogi = :ingrBorrLogi")})
public class Ingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ingr_Id")
    private Integer ingrId;
    @Column(name = "ingr_Fecha")
    @Temporal(TemporalType.DATE)
    private Date ingrFecha;
    @Size(max = 80)
    @Column(name = "ingr_NumeFactura")
    private String ingrNumeFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "ingr_UnidMilitar")
    private String ingrUnidMilitar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ingr_Total")
    private BigDecimal ingrTotal;
    @Column(name = "ingr_BorrLogi")
    private Boolean ingrBorrLogi=true;
    @JoinColumn(name = "ingr_Usua_Bodeguero", referencedColumnName = "usua_Id")
    @ManyToOne(optional = false)
    private Usuario ingrUsuaBodeguero;
    @JoinColumn(name = "ingr_Usua_CompPublicas", referencedColumnName = "usua_Id")
    @ManyToOne(optional = false)
    private Usuario ingrUsuaCompPublicas;
    @JoinColumn(name = "ingr_Usua_Logistica", referencedColumnName = "usua_Id")
    @ManyToOne(optional = false)
    private Usuario ingrUsuaLogistica;
    @JoinColumn(name = "ingr_Prove_Id", referencedColumnName = "prov_Id")
    @ManyToOne(optional = false)
    private Proveedor ingrProveId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detaIngrIngrId")
    private List<Detalleingreso> detalleingresoList;

    public Ingreso() {
    }

    public Ingreso(Integer ingrId) {
        this.ingrId = ingrId;
    }

    public Ingreso(Integer ingrId, String ingrUnidMilitar) {
        this.ingrId = ingrId;
        this.ingrUnidMilitar = ingrUnidMilitar;
    }

    public Integer getIngrId() {
        return ingrId;
    }

    public void setIngrId(Integer ingrId) {
        this.ingrId = ingrId;
    }

    public Date getIngrFecha() {
        return ingrFecha;
    }

    public void setIngrFecha(Date ingrFecha) {
        this.ingrFecha = ingrFecha;
    }

    public String getIngrNumeFactura() {
        return ingrNumeFactura;
    }

    public void setIngrNumeFactura(String ingrNumeFactura) {
        this.ingrNumeFactura = ingrNumeFactura;
    }

    public String getIngrUnidMilitar() {
        return ingrUnidMilitar;
    }

    public void setIngrUnidMilitar(String ingrUnidMilitar) {
        this.ingrUnidMilitar = ingrUnidMilitar;
    }

    public BigDecimal getIngrTotal() {
        return ingrTotal;
    }

    public void setIngrTotal(BigDecimal ingrTotal) {
        this.ingrTotal = ingrTotal;
    }

    public Boolean getIngrBorrLogi() {
        return ingrBorrLogi;
    }

    public void setIngrBorrLogi(Boolean ingrBorrLogi) {
        this.ingrBorrLogi = ingrBorrLogi;
    }

    public Usuario getIngrUsuaBodeguero() {
        return ingrUsuaBodeguero;
    }

    public void setIngrUsuaBodeguero(Usuario ingrUsuaBodeguero) {
        this.ingrUsuaBodeguero = ingrUsuaBodeguero;
    }

    public Usuario getIngrUsuaCompPublicas() {
        return ingrUsuaCompPublicas;
    }

    public void setIngrUsuaCompPublicas(Usuario ingrUsuaCompPublicas) {
        this.ingrUsuaCompPublicas = ingrUsuaCompPublicas;
    }

    public Usuario getIngrUsuaLogistica() {
        return ingrUsuaLogistica;
    }

    public void setIngrUsuaLogistica(Usuario ingrUsuaLogistica) {
        this.ingrUsuaLogistica = ingrUsuaLogistica;
    }

    public Proveedor getIngrProveId() {
        return ingrProveId;
    }

    public void setIngrProveId(Proveedor ingrProveId) {
        this.ingrProveId = ingrProveId;
    }

    @XmlTransient
    public List<Detalleingreso> getDetalleingresoList() {
        return detalleingresoList;
    }

    public void setDetalleingresoList(List<Detalleingreso> detalleingresoList) {
        this.detalleingresoList = detalleingresoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingrId != null ? ingrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingreso)) {
            return false;
        }
        Ingreso other = (Ingreso) object;
        if ((this.ingrId == null && other.ingrId != null) || (this.ingrId != null && !this.ingrId.equals(other.ingrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.entidades.Ingreso[ ingrId=" + ingrId + " ]";
    }
    
}

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
    @NamedQuery(name = "Egreso.findAll", query = "SELECT e FROM Egreso e"),
    @NamedQuery(name = "Egreso.findByEgreId", query = "SELECT e FROM Egreso e WHERE e.egreId = :egreId"),
    @NamedQuery(name = "Egreso.findByEgreFecha", query = "SELECT e FROM Egreso e WHERE e.egreFecha = :egreFecha"),
    @NamedQuery(name = "Egreso.findByEgreDepenUsar", query = "SELECT e FROM Egreso e WHERE e.egreDepenUsar = :egreDepenUsar"),
    @NamedQuery(name = "Egreso.findByEgreTotal", query = "SELECT e FROM Egreso e WHERE e.egreTotal = :egreTotal"),
    @NamedQuery(name = "Egreso.findByEgreBorrLogi", query = "SELECT e FROM Egreso e WHERE e.egreBorrLogi = :egreBorrLogi")})
public class Egreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "egre_Id")
    private Integer egreId;
    @Column(name = "egre_Fecha")
    @Temporal(TemporalType.DATE)
    private Date egreFecha;
    @Size(max = 60)
    @Column(name = "egre_DepenUsar")
    private String egreDepenUsar;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "egre_Total")
    private BigDecimal egreTotal;
    @Column(name = "egre_BorrLogi")
    private Boolean egreBorrLogi=true;
    @JoinColumn(name = "egre_Usua_Bodeguero", referencedColumnName = "usua_Id")
    @ManyToOne(optional = false)
    private Usuario egreUsuaBodeguero;
    @JoinColumn(name = "egre_Usua_Logistico", referencedColumnName = "usua_Id")
    @ManyToOne(optional = false)
    private Usuario egreUsuaLogistico;
    @JoinColumn(name = "egre_Usua_Solicita", referencedColumnName = "usua_Id")
    @ManyToOne(optional = false)
    private Usuario egreUsuaSolicita;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detaEgreEgreId")
    private List<Detalleegreso> detalleegresoList;

    public Egreso() {
    }

    public Egreso(Integer egreId) {
        this.egreId = egreId;
    }

    public Integer getEgreId() {
        return egreId;
    }

    public void setEgreId(Integer egreId) {
        this.egreId = egreId;
    }

    public Date getEgreFecha() {
        return egreFecha;
    }

    public void setEgreFecha(Date egreFecha) {
        this.egreFecha = egreFecha;
    }

    public String getEgreDepenUsar() {
        return egreDepenUsar;
    }

    public void setEgreDepenUsar(String egreDepenUsar) {
        this.egreDepenUsar = egreDepenUsar;
    }

    public BigDecimal getEgreTotal() {
        return egreTotal;
    }

    public void setEgreTotal(BigDecimal egreTotal) {
        this.egreTotal = egreTotal;
    }

    public Boolean getEgreBorrLogi() {
        return egreBorrLogi;
    }

    public void setEgreBorrLogi(Boolean egreBorrLogi) {
        this.egreBorrLogi = egreBorrLogi;
    }

    public Usuario getEgreUsuaBodeguero() {
        return egreUsuaBodeguero;
    }

    public void setEgreUsuaBodeguero(Usuario egreUsuaBodeguero) {
        this.egreUsuaBodeguero = egreUsuaBodeguero;
    }

    public Usuario getEgreUsuaLogistico() {
        return egreUsuaLogistico;
    }

    public void setEgreUsuaLogistico(Usuario egreUsuaLogistico) {
        this.egreUsuaLogistico = egreUsuaLogistico;
    }

    public Usuario getEgreUsuaSolicita() {
        return egreUsuaSolicita;
    }

    public void setEgreUsuaSolicita(Usuario egreUsuaSolicita) {
        this.egreUsuaSolicita = egreUsuaSolicita;
    }

    @XmlTransient
    public List<Detalleegreso> getDetalleegresoList() {
        return detalleegresoList;
    }

    public void setDetalleegresoList(List<Detalleegreso> detalleegresoList) {
        this.detalleegresoList = detalleegresoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (egreId != null ? egreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Egreso)) {
            return false;
        }
        Egreso other = (Egreso) object;
        if ((this.egreId == null && other.egreId != null) || (this.egreId != null && !this.egreId.equals(other.egreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.entidades.Egreso[ egreId=" + egreId + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cbos- Com. Sarmiento H. Luis A.
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Historicoproducto.findAll", query = "SELECT h FROM Historicoproducto h"),
    @NamedQuery(name = "Historicoproducto.findByHPId", query = "SELECT h FROM Historicoproducto h WHERE h.hPId = :hPId"),
    @NamedQuery(name = "Historicoproducto.findByHPFecha", query = "SELECT h FROM Historicoproducto h WHERE h.hPFecha = :hPFecha"),
    @NamedQuery(name = "Historicoproducto.findByHPStocAnterior", query = "SELECT h FROM Historicoproducto h WHERE h.hPStocAnterior = :hPStocAnterior"),
    @NamedQuery(name = "Historicoproducto.findByHPCostAnterior", query = "SELECT h FROM Historicoproducto h WHERE h.hPCostAnterior = :hPCostAnterior"),
    @NamedQuery(name = "Historicoproducto.findByHPTotal", query = "SELECT h FROM Historicoproducto h WHERE h.hPTotal = :hPTotal"),
    @NamedQuery(name = "Historicoproducto.findByHPBorrLogi", query = "SELECT h FROM Historicoproducto h WHERE h.hPBorrLogi = :hPBorrLogi")})
public class Historicoproducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "HP_Id")
    private Integer hPId;
    @Column(name = "HP_Fecha")
    @Temporal(TemporalType.DATE)
    private Date hPFecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "HP_StocAnterior")
    private BigDecimal hPStocAnterior;
    @Column(name = "HP_CostAnterior")
    private BigDecimal hPCostAnterior;
    @Column(name = "HP_Total")
    private BigDecimal hPTotal;
    @Column(name = "HP_BorrLogi")
    private Boolean hPBorrLogi=true;
    @JoinColumn(name = "HP_Prod_Id", referencedColumnName = "prod_Id",unique = true)
    @ManyToOne(optional = false,cascade = CascadeType.MERGE)
    private Producto hPProdId;

    public Historicoproducto() {
    }

    public Historicoproducto(Integer hPId) {
        this.hPId = hPId;
    }

    public Integer getHPId() {
        return hPId;
    }

    public void setHPId(Integer hPId) {
        this.hPId = hPId;
    }

    public Date getHPFecha() {
        return hPFecha;
    }

    public void setHPFecha(Date hPFecha) {
        this.hPFecha = hPFecha;
    }

    public BigDecimal getHPStocAnterior() {
        return hPStocAnterior;
    }

    public void setHPStocAnterior(BigDecimal hPStocAnterior) {
        this.hPStocAnterior = hPStocAnterior;
    }

    public BigDecimal getHPCostAnterior() {
        return hPCostAnterior;
    }

    public void setHPCostAnterior(BigDecimal hPCostAnterior) {
        this.hPCostAnterior = hPCostAnterior;
    }

    public BigDecimal getHPTotal() {
        return hPTotal;
    }

    public void setHPTotal(BigDecimal hPTotal) {
        this.hPTotal = hPTotal;
    }

    public Boolean getHPBorrLogi() {
        return hPBorrLogi;
    }

    public void setHPBorrLogi(Boolean hPBorrLogi) {
        this.hPBorrLogi = hPBorrLogi;
    }

    public Producto getHPProdId() {
        return hPProdId;
    }

    public void setHPProdId(Producto hPProdId) {
        this.hPProdId = hPProdId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hPId != null ? hPId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historicoproducto)) {
            return false;
        }
        Historicoproducto other = (Historicoproducto) object;
        if ((this.hPId == null && other.hPId != null) || (this.hPId != null && !this.hPId.equals(other.hPId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.entidades.Historicoproducto[ hPId=" + hPId + " ]";
    }
    
}

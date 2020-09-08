
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Cbos- Com. Sarmiento H. Luis A.
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Detalleegreso.findAll", query = "SELECT d FROM Detalleegreso d"),
    @NamedQuery(name = "Detalleegreso.findByDetaEgreId", query = "SELECT d FROM Detalleegreso d WHERE d.detaEgreId = :detaEgreId"),
    @NamedQuery(name = "Detalleegreso.findByDetaEgreCantEgresa", query = "SELECT d FROM Detalleegreso d WHERE d.detaEgreCantEgresa = :detaEgreCantEgresa"),
    @NamedQuery(name = "Detalleegreso.findByDetaEgrePreciEgresa", query = "SELECT d FROM Detalleegreso d WHERE d.detaEgrePreciEgresa = :detaEgrePreciEgresa"),
    @NamedQuery(name = "Detalleegreso.findByDetaTotal", query = "SELECT d FROM Detalleegreso d WHERE d.detaTotal = :detaTotal"),
    @NamedQuery(name = "Detalleegreso.findByDetaEgreBorrLogi", query = "SELECT d FROM Detalleegreso d WHERE d.detaEgreBorrLogi = :detaEgreBorrLogi")})
public class Detalleegreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detaEgre_Id")
    private Integer detaEgreId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "detaEgre_CantEgresa")
    private BigDecimal detaEgreCantEgresa;

    @Column(name = "detaEgre_PreciEgresa")
    private BigDecimal detaEgrePreciEgresa;

    @Column(name = "deta_Total")
    private BigDecimal detaTotal;
    @Column(name = "detaEgre_BorrLogi")
    private Boolean detaEgreBorrLogi=true;
    @JoinColumn(name = "detaEgre_Egre_Id", referencedColumnName = "egre_Id")
    @ManyToOne(optional = false)
    private Egreso detaEgreEgreId;
    @JoinColumn(name = "detaEgre_Prod_Id", referencedColumnName = "prod_Id")
    @OneToOne
    private Producto detaEgreProdId;

//    public Detalleegreso(BigDecimal detaEgreCantEgresa, BigDecimal detaEgrePreciEgresa, BigDecimal detaTotal, Producto detaEgreProdId) {
//        System.out.println("este es el constructor de l detalle egreso");
//        System.out.println(detaEgreCantEgresa);
//        System.out.println(detaEgrePreciEgresa);
//        System.out.println(detaTotal);
//        System.out.println(detaEgreProdId);
//        this.detaEgreCantEgresa = detaEgreCantEgresa;
//        this.detaEgrePreciEgresa = detaEgrePreciEgresa;
//        this.detaTotal = detaTotal;
//        this.detaEgreProdId = detaEgreProdId;
//    }
//    public Detalleegreso(BigDecimal detaEgreCantEgresa, BigDecimal detaEgrePreciEgresa, BigDecimal detaTotal, Egreso detaEgreEgreId, Producto detaEgreProdId) {
//
//        System.out.println("ingresando al nuevo constructor");
//
//        System.out.println(detaEgrePreciEgresa);
//        System.out.println(detaTotal);
//        System.out.println(detaEgreProdId);
//        System.out.println(detaEgreEgreId);
//        System.out.println(detaEgreId);
//        System.out.println(detaEgreCantEgresa);
//        this.detaEgreCantEgresa = detaEgreCantEgresa;
//        this.detaEgrePreciEgresa = detaEgrePreciEgresa;
//        this.detaTotal = detaTotal;
//        this.detaEgreEgreId = detaEgreEgreId;
//        this.detaEgreProdId = detaEgreProdId;
//    }

    public Detalleegreso(BigDecimal detaEgreCantEgresa, BigDecimal detaEgrePreciEgresa, BigDecimal detaTotal, Producto detaEgreProdId) {
        System.out.println("ingresando al constructor");
        System.out.println(detaEgrePreciEgresa);
        System.out.println(detaTotal);
        System.out.println(detaEgreCantEgresa);
        System.out.println(detaEgreProdId);
        
        this.detaEgreCantEgresa = detaEgreCantEgresa;
        this.detaEgrePreciEgresa = detaEgrePreciEgresa;
        this.detaTotal = detaTotal;
        this.detaEgreProdId = detaEgreProdId;
    }

    public Detalleegreso() {
    }

    public Detalleegreso(Integer detaEgreId) {
        this.detaEgreId = detaEgreId;
    }

    public Integer getDetaEgreId() {
        return detaEgreId;
    }

    public void setDetaEgreId(Integer detaEgreId) {
        this.detaEgreId = detaEgreId;
    }

    public BigDecimal getDetaEgreCantEgresa() {
        return detaEgreCantEgresa;
    }

    public void setDetaEgreCantEgresa(BigDecimal detaEgreCantEgresa) {
        this.detaEgreCantEgresa = detaEgreCantEgresa;
    }

    public BigDecimal getDetaEgrePreciEgresa() {
        return detaEgrePreciEgresa;
    }

    public void setDetaEgrePreciEgresa(BigDecimal detaEgrePreciEgresa) {
        this.detaEgrePreciEgresa = detaEgrePreciEgresa;
    }

    public BigDecimal getDetaTotal() {
        return detaTotal;
    }

    public void setDetaTotal(BigDecimal detaTotal) {
        this.detaTotal = detaTotal;
    }

    public Boolean getDetaEgreBorrLogi() {
        return detaEgreBorrLogi;
    }

    public void setDetaEgreBorrLogi(Boolean detaEgreBorrLogi) {
        this.detaEgreBorrLogi = detaEgreBorrLogi;
    }

    public Egreso getDetaEgreEgreId() {
        return detaEgreEgreId;
    }

    public void setDetaEgreEgreId(Egreso detaEgreEgreId) {
        this.detaEgreEgreId = detaEgreEgreId;
    }

    public Producto getDetaEgreProdId() {
        return detaEgreProdId;
    }

    public void setDetaEgreProdId(Producto detaEgreProdId) {
        this.detaEgreProdId = detaEgreProdId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detaEgreId != null ? detaEgreId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleegreso)) {
            return false;
        }
        Detalleegreso other = (Detalleegreso) object;
        if ((this.detaEgreId == null && other.detaEgreId != null) || (this.detaEgreId != null && !this.detaEgreId.equals(other.detaEgreId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.mavenproject3.Detalleegreso[ detaEgreId=" + detaEgreId + " ]";
    }

}

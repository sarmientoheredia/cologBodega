package com.bodcol.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.OneToOne;

/**
 *
 * @author Cbos- Com. Sarmiento H. Luis A.
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Detalleingreso.findAll", query = "SELECT d FROM Detalleingreso d"),
    @NamedQuery(name = "Detalleingreso.findByDetaIngrId", query = "SELECT d FROM Detalleingreso d WHERE d.detaIngrId = :detaIngrId"),
    
    @NamedQuery(name = "Detalleingreso.findByDetadetaIngrIngrId", query = "SELECT d FROM Detalleingreso d left join fetch d.detaIngrIngrId where d.detaIngrIngrId =:detaIngrIngrId"),
    
    @NamedQuery(name = "Detalleingreso.findByDetaIngrCantIngresa", query = "SELECT d FROM Detalleingreso d WHERE d.detaIngrCantIngresa = :detaIngrCantIngresa"),
    @NamedQuery(name = "Detalleingreso.findByDeraIngrPreciIngresa", query = "SELECT d FROM Detalleingreso d WHERE d.deraIngrPreciIngresa = :deraIngrPreciIngresa"),
    @NamedQuery(name = "Detalleingreso.findByDetaIngrTotal", query = "SELECT d FROM Detalleingreso d WHERE d.detaIngrTotal = :detaIngrTotal"),
    @NamedQuery(name = "Detalleingreso.findByDetaIngrBorrLogi", query = "SELECT d FROM Detalleingreso d WHERE d.detaIngrBorrLogi = :detaIngrBorrLogi")})
public class Detalleingreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "detaIngr_Id")
    private Integer detaIngrId;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "detaIngr_CantIngresa")
    private BigDecimal detaIngrCantIngresa;

    @Column(name = "deraIngr_PreciIngresa")
    private BigDecimal deraIngrPreciIngresa;
    @Column(name = "detaIngr_Total")
    private BigDecimal detaIngrTotal;
    @Column(name = "detaIngr_BorrLogi")
    private Boolean detaIngrBorrLogi=true;
    @JoinColumn(name = "detaIngr_Ingr_Id", referencedColumnName = "ingr_Id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Ingreso detaIngrIngrId;
    @JoinColumn(name = "detaIngr_Prod_Id", referencedColumnName = "prod_Id")
    @OneToOne
    private Producto detaIngrProdId;

    public Detalleingreso() {
    }

 
    public Detalleingreso( BigDecimal detaIngrCantIngresa, BigDecimal deraIngrPreciIngresa, BigDecimal detaIngrTotal, Producto detaIngrProdId) {
        
        System.out.println("este es el constructor"+detaIngrCantIngresa);
        this.detaIngrCantIngresa = detaIngrCantIngresa;
        this.deraIngrPreciIngresa = deraIngrPreciIngresa;
        this.detaIngrTotal = detaIngrTotal;
        this.detaIngrProdId = detaIngrProdId;
 
    }
    
    
    
    

    public Detalleingreso(Integer detaIngrId) {
        this.detaIngrId = detaIngrId;
    }

    public Integer getDetaIngrId() {
        return detaIngrId;
    }

    public void setDetaIngrId(Integer detaIngrId) {
        this.detaIngrId = detaIngrId;
    }

    public BigDecimal getDetaIngrCantIngresa() {
        return detaIngrCantIngresa;
    }

    public void setDetaIngrCantIngresa(BigDecimal detaIngrCantIngresa) {
        this.detaIngrCantIngresa = detaIngrCantIngresa;
    }

    public BigDecimal getDeraIngrPreciIngresa() {
        return deraIngrPreciIngresa;
    }

    public void setDeraIngrPreciIngresa(BigDecimal deraIngrPreciIngresa) {
        this.deraIngrPreciIngresa = deraIngrPreciIngresa;
    }

    public BigDecimal getDetaIngrTotal() {
        return detaIngrTotal;
    }

    public void setDetaIngrTotal(BigDecimal detaIngrTotal) {
        this.detaIngrTotal = detaIngrTotal;
    }

    public Boolean getDetaIngrBorrLogi() {
        return detaIngrBorrLogi;
    }

    public void setDetaIngrBorrLogi(Boolean detaIngrBorrLogi) {
        this.detaIngrBorrLogi = detaIngrBorrLogi;
    }

    public Ingreso getDetaIngrIngrId() {
        return detaIngrIngrId;
    }

    public void setDetaIngrIngrId(Ingreso detaIngrIngrId) {
        this.detaIngrIngrId = detaIngrIngrId;
    }

    public Producto getDetaIngrProdId() {
        return detaIngrProdId;
    }

    public void setDetaIngrProdId(Producto detaIngrProdId) {
        this.detaIngrProdId = detaIngrProdId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detaIngrId != null ? detaIngrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalleingreso)) {
            return false;
        }
        Detalleingreso other = (Detalleingreso) object;
        if ((this.detaIngrId == null && other.detaIngrId != null) || (this.detaIngrId != null && !this.detaIngrId.equals(other.detaIngrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.mavenproject3.Detalleingreso[ detaIngrId=" + detaIngrId + " ]";
    }

}

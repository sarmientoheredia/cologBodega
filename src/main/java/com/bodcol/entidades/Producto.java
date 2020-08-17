/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p "),
    @NamedQuery(name = "Producto.findByProdId", query = "SELECT p FROM Producto p WHERE p.prodId = :prodId"),
    @NamedQuery(name = "Producto.findByProdRackFila", query = "SELECT p FROM Producto p WHERE p.prodRackFila = :prodRackFila"),
    @NamedQuery(name = "Producto.findByProdRackColumna", query = "SELECT p FROM Producto p WHERE p.prodRackColumna = :prodRackColumna"),
    @NamedQuery(name = "Producto.findByProdCodigo", query = "SELECT p FROM Producto p WHERE p.prodCodigo = :prodCodigo"),
    @NamedQuery(name = "Producto.findByProdNombre", query = "SELECT p FROM Producto p WHERE p.prodNombre = :prodNombre"),
    @NamedQuery(name = "Producto.findByProdUnidMedida", query = "SELECT p FROM Producto p WHERE p.prodUnidMedida = :prodUnidMedida"),
    @NamedQuery(name = "Producto.findByProdStocActual", query = "SELECT p FROM Producto p WHERE p.prodStocActual = :prodStocActual"),
    @NamedQuery(name = "Producto.findByProdCostActual", query = "SELECT p FROM Producto p WHERE p.prodCostActual = :prodCostActual"),
    @NamedQuery(name = "Producto.findByProdStatus", query = "SELECT p FROM Producto p WHERE p.prodStatus = :prodStatus"),
    @NamedQuery(name = "Producto.findByProdBorrLogi", query = "SELECT p FROM Producto p WHERE p.prodBorrLogi = :prodBorrLogi")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prod_Id")
    private Integer prodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "prod_RackFila")
    private String prodRackFila;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "prod_RackColumna")
    private String prodRackColumna;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "prod_Codigo")
    private String prodCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "prod_Nombre")
    private String prodNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "prod_UnidMedida")
    private String prodUnidMedida;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prod_StocActual")
    private BigDecimal prodStocActual;
    @Column(name = "prod_CostActual")
    private BigDecimal prodCostActual;
    @Size(max = 30)
    @Column(name = "prod_Status")
    private String prodStatus;
    @Column(name = "prod_BorrLogi")
    private Boolean prodBorrLogi=true;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detaIngrProdId")
    private List<Detalleingreso> detalleingresoList;
    @JoinColumn(name = "prod_Rack_Id", referencedColumnName = "rack_Id")
    @ManyToOne(optional = false)
    private Rack prodRackId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hPProdId")
    private List<Historicoproducto> historicoproductoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detaEgreProdId")
    private List<Detalleegreso> detalleegresoList;

    public Producto() {
    }

    public Producto(Integer prodId) {
        this.prodId = prodId;
    }

    public Producto(Integer prodId, String prodRackFila, String prodRackColumna, String prodCodigo, String prodNombre, String prodUnidMedida) {
        this.prodId = prodId;
        this.prodRackFila = prodRackFila;
        this.prodRackColumna = prodRackColumna;
        this.prodCodigo = prodCodigo;
        this.prodNombre = prodNombre;
        this.prodUnidMedida = prodUnidMedida;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdRackFila() {
        return prodRackFila;
    }

    public void setProdRackFila(String prodRackFila) {
        this.prodRackFila = prodRackFila;
    }

    public String getProdRackColumna() {
        return prodRackColumna;
    }

    public void setProdRackColumna(String prodRackColumna) {
        this.prodRackColumna = prodRackColumna;
    }

    public String getProdCodigo() {
        return prodCodigo;
    }

    public void setProdCodigo(String prodCodigo) {
        this.prodCodigo = prodCodigo;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public String getProdUnidMedida() {
        return prodUnidMedida;
    }

    public void setProdUnidMedida(String prodUnidMedida) {
        this.prodUnidMedida = prodUnidMedida;
    }

    public BigDecimal getProdStocActual() {
        return prodStocActual;
    }

    public void setProdStocActual(BigDecimal prodStocActual) {
        this.prodStocActual = prodStocActual;
    }

    public BigDecimal getProdCostActual() {
        return prodCostActual;
    }

    public void setProdCostActual(BigDecimal prodCostActual) {
        this.prodCostActual = prodCostActual;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public Boolean getProdBorrLogi() {
        return prodBorrLogi;
    }

    public void setProdBorrLogi(Boolean prodBorrLogi) {
        this.prodBorrLogi = prodBorrLogi;
    }

    @XmlTransient
    public List<Detalleingreso> getDetalleingresoList() {
        return detalleingresoList;
    }

    public void setDetalleingresoList(List<Detalleingreso> detalleingresoList) {
        this.detalleingresoList = detalleingresoList;
    }

    public Rack getProdRackId() {
        return prodRackId;
    }

    public void setProdRackId(Rack prodRackId) {
        this.prodRackId = prodRackId;
    }

    @XmlTransient
    public List<Historicoproducto> getHistoricoproductoList() {
        return historicoproductoList;
    }

    public void setHistoricoproductoList(List<Historicoproducto> historicoproductoList) {
        this.historicoproductoList = historicoproductoList;
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
        hash += (prodId != null ? prodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.prodId == null && other.prodId != null) || (this.prodId != null && !this.prodId.equals(other.prodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.entidades.Producto[ prodId=" + prodId + " ]";
    }
    
}

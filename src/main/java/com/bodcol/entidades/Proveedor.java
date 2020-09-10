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
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByProvId", query = "SELECT p FROM Proveedor p WHERE p.provId = :provId"),
    @NamedQuery(name = "Proveedor.findByProvRuc", query = "SELECT p FROM Proveedor p WHERE p.provRuc = :provRuc"),
    @NamedQuery(name = "Proveedor.findByProvRucCedula", query = "SELECT p FROM Proveedor p WHERE p.provRuc = :provRuc OR p.provCedula=:provCedula"),
    @NamedQuery(name = "Proveedor.findByProvRucNatural", query = "SELECT p FROM Proveedor p WHERE p.provRucNatural = :provRucNatural"),
    @NamedQuery(name = "Proveedor.findByProvCedulaRucNatural", query = "SELECT p FROM Proveedor p WHERE p.provRucNatural = :provRucNatural OR p.provCedula = :provCedula"),
    @NamedQuery(name = "Proveedor.findByProvCedula", query = "SELECT p FROM Proveedor p WHERE p.provCedula = :provCedula"),
    @NamedQuery(name = "Proveedor.findByProvRucCedulaNatural", query = "SELECT p FROM Proveedor p WHERE p.provCedula = :provCedula OR p.provRuc=:provRuc OR P.provRucNatural=:provRucNatural"),
    @NamedQuery(name = "Proveedor.findByProvNombre", query = "SELECT p FROM Proveedor p WHERE p.provNombre = :provNombre"),
    @NamedQuery(name = "Proveedor.findByProvDireccion", query = "SELECT p FROM Proveedor p WHERE p.provDireccion = :provDireccion"),
    @NamedQuery(name = "Proveedor.findByProvTelefono", query = "SELECT p FROM Proveedor p WHERE p.provTelefono = :provTelefono"),
    @NamedQuery(name = "Proveedor.findByProvCelular", query = "SELECT p FROM Proveedor p WHERE p.provCelular = :provCelular"),
    @NamedQuery(name = "Proveedor.findByProvEmail", query = "SELECT p FROM Proveedor p WHERE p.provEmail = :provEmail"),
    @NamedQuery(name = "Proveedor.findByProvBorrLogi", query = "SELECT p FROM Proveedor p WHERE p.provBorrLogi = :provBorrLogi")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prov_Id")
    private Integer provId;
    
    
    @Size(max = 14)    
    @Column(name = "prov_Ruc", unique = true)
    private String provRuc;
    
    @Size(max = 14)   
    @Column(name = "prov_RucNatural", unique = true)
    private String provRucNatural;
    
    
    @Size(max = 50)
    @Column(name = "prov_Cedula",unique = true)
    private String provCedula;
    
    
    @Size(min = 1, max = 50)
    @Column(name = "prov_Nombre")
    private String provNombre;
    
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "prov_Direccion")
    private String provDireccion;
    
    
    @Size(max = 30)
    @Column(name = "prov_Telefono")
    private String provTelefono;
    
    
    @Size(max = 50)
    @Column(name = "prov_Celular")
    private String provCelular;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "prov_Email")
    private String provEmail;
    
    
    @Column(name = "prov_BorrLogi")
    private Boolean provBorrLogi = true;
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "ingrProveId")
    private List<Ingreso> ingresoList;

    public Proveedor() {
    }

    public Proveedor(Integer provId) {
        this.provId = provId;
    }

    public Integer getProvId() {
        return provId;
    }

    public void setProvId(Integer provId) {
        this.provId = provId;
    }

    public String getProvCedula() {
        return provCedula;
    }

    public void setProvCedula(String provCedula) {
        this.provCedula = provCedula;
    }

    public String getProvDireccion() {
        return provDireccion;
    }

    public void setProvDireccion(String provDireccion) {
        this.provDireccion = provDireccion;
    }

    public String getProvCelular() {
        return provCelular;
    }

    public String getProvRucNatural() {
        return provRucNatural;
    }

    public void setProvRucNatural(String provRucNatural) {
        this.provRucNatural = provRucNatural;
    }

    
    public void setProvCelular(String provCelular) {
        this.provCelular = provCelular;
    }

    public String getProvRuc() {
        return provRuc;
    }

    public void setProvRuc(String provRuc) {
        this.provRuc = provRuc;
    }

    public String getProvNombre() {
        return provNombre;
    }

    public void setProvNombre(String provNombre) {
        this.provNombre = provNombre;
    }

    public String getProvTelefono() {
        return provTelefono;
    }

    public void setProvTelefono(String provTelefono) {
        this.provTelefono = provTelefono;
    }

    public String getProvEmail() {
        return provEmail;
    }

    public void setProvEmail(String provEmail) {
        this.provEmail = provEmail;
    }

    public Boolean getProvBorrLogi() {
        return provBorrLogi;
    }

 
    public void setProvBorrLogi(Boolean provBorrLogi) {
        this.provBorrLogi = provBorrLogi;
    }

    @XmlTransient
    public List<Ingreso> getIngresoList() {
        return ingresoList;
    }

    public void setIngresoList(List<Ingreso> ingresoList) {
        this.ingresoList = ingresoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provId != null ? provId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.provId == null && other.provId != null) || (this.provId != null && !this.provId.equals(other.provId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.entidades.Proveedor[ provId=" + provId + " ]";
    }

}

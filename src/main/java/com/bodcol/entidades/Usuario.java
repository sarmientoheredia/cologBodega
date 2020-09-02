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
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuaId", query = "SELECT u FROM Usuario u WHERE u.usuaId = :usuaId"),
    @NamedQuery(name = "Usuario.findByUsuaGrado", query = "SELECT u FROM Usuario u WHERE u.usuaGrado = :usuaGrado"),
    @NamedQuery(name = "Usuario.findByUsuaiArma", query = "SELECT u FROM Usuario u WHERE u.usuaiArma = :usuaiArma"),
    @NamedQuery(name = "Usuario.findByUsuaCedula", query = "SELECT u FROM Usuario u WHERE u.usuaCedula = :usuaCedula"),
    @NamedQuery(name = "Usuario.findByUsuaNombre", query = "SELECT u FROM Usuario u WHERE u.usuaNombre = :usuaNombre"),
    @NamedQuery(name = "Usuario.findByUsuaApellido", query = "SELECT u FROM Usuario u WHERE u.usuaApellido = :usuaApellido"),
    @NamedQuery(name = "Usuario.findByUsuaUsuario", query = "SELECT u FROM Usuario u WHERE u.usuaUsuario = :usuaUsuario"),
     @NamedQuery(name = "Usuario.findByLoggin", query = "SELECT u FROM Usuario u WHERE u.usuaUsuario = :usuaUsuario ANd u.usuaPassword=:usuaPassword"),
    @NamedQuery(name = "Usuario.findByUsuaPassword", query = "SELECT u FROM Usuario u WHERE u.usuaPassword = :usuaPassword"),
    @NamedQuery(name = "Usuario.findByUsuaBorrLogi", query = "SELECT u FROM Usuario u WHERE u.usuaBorrLogi = :usuaBorrLogi")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usua_Id")
    private Integer usuaId;
    @Size(max = 20)
    @Column(name = "usua_Grado")
    private String usuaGrado;
    @Size(max = 20)
    @Column(name = "usuai_Arma")
    private String usuaiArma;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "usua_Cedula",unique = true)
    private String usuaCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "usua_Nombre")
    private String usuaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "usua_Apellido")
    private String usuaApellido;
    @Size(max = 45)
    @Column(name = "usua_Usuario",unique = true)
    private String usuaUsuario;
    @Size(max = 45)
    @Column(name = "usua_Password")
    private String usuaPassword;
    @Column(name = "usua_BorrLogi")
    private Boolean usuaBorrLogi=true;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "ingrUsuaBodeguero")
    private List<Ingreso> ingresoList;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "ingrUsuaCompPublicas")
    private List<Ingreso> ingresoList1;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "ingrUsuaLogistica")
    private List<Ingreso> ingresoList2;
    @JoinColumn(name = "usua_Rol_Id", referencedColumnName = "rol_Id")
    @ManyToOne(cascade = CascadeType.MERGE)
    private Rol usuaRolId;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "egreUsuaBodeguero")
    private List<Egreso> egresoList;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "egreUsuaLogistico")
    private List<Egreso> egresoList1;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "egreUsuaSolicita")
    private List<Egreso> egresoList2;

    public Usuario() {
    }

    public Usuario(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public Usuario(Integer usuaId, String usuaCedula, String usuaNombre, String usuaApellido) {
        this.usuaId = usuaId;
        this.usuaCedula = usuaCedula;
        this.usuaNombre = usuaNombre;
        this.usuaApellido = usuaApellido;
    }

    public Integer getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public String getUsuaGrado() {
        return usuaGrado;
    }

    public void setUsuaGrado(String usuaGrado) {
        this.usuaGrado = usuaGrado;
    }

    public String getUsuaiArma() {
        return usuaiArma;
    }

    public void setUsuaiArma(String usuaiArma) {
        this.usuaiArma = usuaiArma;
    }

    public String getUsuaCedula() {
        return usuaCedula;
    }

    public void setUsuaCedula(String usuaCedula) {
        this.usuaCedula = usuaCedula;
    }

    public String getUsuaNombre() {
        return usuaNombre;
    }

    public void setUsuaNombre(String usuaNombre) {
        this.usuaNombre = usuaNombre;
    }

    public String getUsuaApellido() {
        return usuaApellido;
    }

    public void setUsuaApellido(String usuaApellido) {
        this.usuaApellido = usuaApellido;
    }

    public String getUsuaUsuario() {
        return usuaUsuario;
    }

    public void setUsuaUsuario(String usuaUsuario) {
        this.usuaUsuario = usuaUsuario;
    }

    public String getUsuaPassword() {
        return usuaPassword;
    }

    public void setUsuaPassword(String usuaPassword) {
        this.usuaPassword = usuaPassword;
    }

    public Boolean getUsuaBorrLogi() {
        return usuaBorrLogi;
    }

    public void setUsuaBorrLogi(Boolean usuaBorrLogi) {
        this.usuaBorrLogi = usuaBorrLogi;
    }

    @XmlTransient
    public List<Ingreso> getIngresoList() {
        return ingresoList;
    }

    public void setIngresoList(List<Ingreso> ingresoList) {
        this.ingresoList = ingresoList;
    }

    @XmlTransient
    public List<Ingreso> getIngresoList1() {
        return ingresoList1;
    }

    public void setIngresoList1(List<Ingreso> ingresoList1) {
        this.ingresoList1 = ingresoList1;
    }

    @XmlTransient
    public List<Ingreso> getIngresoList2() {
        return ingresoList2;
    }

    public void setIngresoList2(List<Ingreso> ingresoList2) {
        this.ingresoList2 = ingresoList2;
    }

    public Rol getUsuaRolId() {
        return usuaRolId;
    }

    public void setUsuaRolId(Rol usuaRolId) {
        this.usuaRolId = usuaRolId;
    }

    @XmlTransient
    public List<Egreso> getEgresoList() {
        return egresoList;
    }

    public void setEgresoList(List<Egreso> egresoList) {
        this.egresoList = egresoList;
    }

    @XmlTransient
    public List<Egreso> getEgresoList1() {
        return egresoList1;
    }

    public void setEgresoList1(List<Egreso> egresoList1) {
        this.egresoList1 = egresoList1;
    }

    @XmlTransient
    public List<Egreso> getEgresoList2() {
        return egresoList2;
    }

    public void setEgresoList2(List<Egreso> egresoList2) {
        this.egresoList2 = egresoList2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuaId != null ? usuaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuaId == null && other.usuaId != null) || (this.usuaId != null && !this.usuaId.equals(other.usuaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bodcol.entidades.Usuario[ usuaId=" + usuaId + " ]";
    }
    
}

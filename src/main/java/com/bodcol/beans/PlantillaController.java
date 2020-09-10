package com.bodcol.beans;

import com.bodcol.entidades.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Cbos- Com. Sarmiento H. Luis A.
 */
@Named
@ViewScoped
public class PlantillaController implements Serializable {

    private Usuario usuario;

    @PostConstruct
    public void init(){
        usuario=new Usuario();
    }
    
    
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuario = (Usuario) context.getExternalContext().getSessionMap().get("administrador");
            if (usuario == null) {
                context.getExternalContext().redirect("./../../index.xhtml");
                
            }

        } catch (Exception e) {
            //implementacion de un log 
        }
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
}

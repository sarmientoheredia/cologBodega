package com.bodcol.beans;

import com.bodcol.entidades.Usuario;
import com.bodcol.facade.UsuarioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Cbos- Com. Sarmiento H. Luis A.
 */
@Named(value = "logginBean")
@ViewScoped
public class Loggin implements Serializable {

//    private String txtUsuario = null;
//    private String txtPassword = null;
//    private String txtLoggin = null;
    
    @EJB
    private UsuarioFacade EJBUsuario;
    
    private Usuario usuario;
    
      

    @PostConstruct
    public void init(){
        usuario=new Usuario();
    }
    

    public Loggin() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




    
    



    
    
    
    public String iniciarSesion() throws Exception{
        
        Usuario us;
        String redireccion =null;
        try {
            us=EJBUsuario.autenticar(usuario);
            
            if(us!=null){
                //Almacenar en la sesion de JSF
                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador", us);
                redireccion="inicio?faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error de Autenticacion"));
        }
        
        
        return redireccion;
    }
    
   
}

package com.bodcol.facade;

import com.bodcol.entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cbos- Com. Sarmiento H. Luis A.
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "bodColPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    //METODO PARA VERIFICAR SI EL USUARIO CON ESE NUMERO DE CEDULA YA ESTA REGISTRADO 
    public boolean verificarCedula(String cedula) {
        Query query = em.createNamedQuery("Usuario.findByUsuaCedula", Usuario.class);
        query.setParameter("usuaCedula", cedula);
        List<Usuario> listado = query.getResultList();
        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //METODO PARA CONTROLAR QUE NO PUEDE EXISTIR DOS USUARIOS EN LA MISMA TABLA QUE TENGAN EL MISMO USER
    public boolean verificarUsuario(String usuaUsuario) {
        Query query = em.createNamedQuery("Usuario.findByUsuaUsuario", Usuario.class);
        query.setParameter("usuaUsuario", usuaUsuario);
        List<Usuario> listado = query.getResultList();
        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    //METODO PARA AUTENTICAR AL USUARIO QUE PODRA INGRESAR AL SISTEMA EN BASE AL USER Y PASSWORD 
    public Usuario autenticar(Usuario us) {
        Usuario usuario = null;
        try {
            Query query = em.createNamedQuery("Usuario.findByLoggin", Usuario.class);
            query.setParameter("usuaUsuario", us.getUsuaUsuario());
            query.setParameter("usuaPassword", us.getUsuaPassword());

            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);

            }

        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

    
    
    
    
    
    
    
}

































//METODO PARA PODER CONTROLAR EL ACCESO AL SISTEMA DE LOS USUARIOS EN BASE AL USER Y PASSWORD 
//    public Usuario encontrarUsuarioLoggin(String usuaUsuario, String usuaPassword) {
//        Query query = em.createNamedQuery("Usuario.findByLoggin", Usuario.class);
//        query.setParameter("usuaUsuario", usuaUsuario);
//        query.setParameter("usuaPassword", usuaPassword);
//
//        List<Usuario> listado = query.getResultList();
//
//        if (!listado.isEmpty()) {
//
//            return listado.get(0);
//        }
//        return null;
//
//    }

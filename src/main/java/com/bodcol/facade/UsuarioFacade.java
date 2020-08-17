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

    
    
    
//metodo espesiifico utiliizando los namequery  para ver los usuarios 

    public Usuario encontrarUsuarioLoggin(String usuaUsuario, String usuaPassword) {
        Query query = em.createNamedQuery("Usuario.findByLoggin", Usuario.class);

        query.setParameter("usuaUsuario", usuaUsuario);
        query.setParameter("usuaPassword", usuaPassword);

        List<Usuario> listado = query.getResultList();
        
        if (!listado.isEmpty()) {
            
            return listado.get(0);
        }
        return null;

    }

    public Usuario autenticar(Usuario us) {
        Usuario usuario = null;
        try {
            Query query = em.createNamedQuery("Usuario.findByLoggin", Usuario.class);
            query.setParameter("usuaUsuario", us.getUsuaUsuario());
            query.setParameter("usuaPassword",us.getUsuaPassword());
            
            
            
            List<Usuario> lista=query.getResultList();
            if(!lista.isEmpty()){
                usuario=lista.get(0);
               
            }
            
            
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

}

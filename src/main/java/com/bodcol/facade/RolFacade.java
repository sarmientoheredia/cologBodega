/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.facade;

import com.bodcol.entidades.Rol;
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
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "bodColPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    
    //MEMTODO PARA VERIFICAR SI EL ROL YA EXISTE EN LA BASE DE DATOS
    public boolean varificarRol(String rolNombre){
        Query query=em.createNamedQuery("Rol.findByRolNombre",Rol.class);
        query.setParameter("rolNombre", rolNombre);
        List<Rol> listado=query.getResultList();
        if(!listado.isEmpty()){
            return true ;
        }else{
            return false;
        }
        
    }
}

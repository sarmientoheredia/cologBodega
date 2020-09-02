/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.facade;

import com.bodcol.entidades.Seccion;
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
public class SeccionFacade extends AbstractFacade<Seccion> {

    @PersistenceContext(unitName = "bodColPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeccionFacade() {
        super(Seccion.class);
    }
    
    
    
    //METODO PARA VERIFICAR SI EXISTE ALGUNA SECCION CON EL MISMO NOMBRE REGISTRADO
    public boolean verificarNombreSeccion(String seccNombre){
        Query query=em.createNamedQuery("Seccion.findBySeccNombre", Seccion.class);
        query.setParameter("seccNombre", seccNombre);
        List<Seccion> listado=query.getResultList();
        if(!listado.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
    
}

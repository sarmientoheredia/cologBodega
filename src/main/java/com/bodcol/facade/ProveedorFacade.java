/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.facade;

import com.bodcol.entidades.Proveedor;
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
public class ProveedorFacade extends AbstractFacade<Proveedor> {

    @PersistenceContext(unitName = "bodColPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }
    
    
    //METODO PARA VERIFICAR SI EL PROVEEDOR  CON ESE NUMERO DE RUC YA ESTA REGISTRADO 
    public boolean verificarRuc(String provRuc ) {
        Query query = em.createNamedQuery("Proveedor.findByProvRuc", Proveedor.class);
        query.setParameter("provRuc", provRuc);
        List<Proveedor> listado = query.getResultList();
        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
        //METODO PARA VERIFICAR SI EL PROVEEDOR  CON ESE NUMERO DE RUCNATURAL YA ESTA REGISTRADO 
    public boolean verificarRucNatural(String provRucNatural ) {
        Query query = em.createNamedQuery("Proveedor.findByProvRucNatural", Proveedor.class);
        query.setParameter("provRucNatural", provRucNatural);
        List<Proveedor> listado = query.getResultList();
        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    
            //METODO PARA VERIFICAR SI EL PROVEEDOR  CON ESE NUMERO DE RUCNATURAL YA ESTA REGISTRADO 
    public boolean verificarCedulaRucNatural(String provRucNatural, String provCedula ) {
        Query query = em.createNamedQuery("Proveedor.findByProvCedulaRucNatural", Proveedor.class);
        query.setParameter("provRucNatural", provRucNatural);
        query.setParameter("provCedula", provCedula);
        List<Proveedor> listado = query.getResultList();
        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
                //METODO PARA VERIFICAR SI EL PROVEEDOR  CON ESE NUMERO DE RUCNATURAL YA ESTA REGISTRADO 
    public boolean verificarRucCedula(String provCedula, String provRuc ) {
        Query query = em.createNamedQuery("Proveedor.findByProvRucCedula", Proveedor.class);
        query.setParameter("provCedula", provCedula);
        query.setParameter("provRuc", provRuc);
        List<Proveedor> listado = query.getResultList();
        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
        //METODO PARA VERIFICAR SI EL PROVEEDOR  CON ESE NUMERO DE RUC YA ESTA REGISTRADO 
    public boolean verificarCedula(String provCedula ) {
        Query query = em.createNamedQuery("Proveedor.findByProvCedula", Proveedor.class);
        query.setParameter("provCedula", provCedula);
        List<Proveedor> listado = query.getResultList();
        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
            //METODO PARA VERIFICAR SI EL PROVEEDOR  CON ESE NUMERO DE RUC YA ESTA REGISTRADO 
    public boolean verificarRucCedulaNatural(String provRuc,String provCedula,String provRucNatural) {
        Query query = em.createNamedQuery("Proveedor.findByProvRucCedulaNatural", Proveedor.class);
       query.setParameter("provRuc", provRuc);
        query.setParameter("provCedula", provCedula);
        query.setParameter("provRucNatural", provRucNatural);
        
        List<Proveedor> listado = query.getResultList();
        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}

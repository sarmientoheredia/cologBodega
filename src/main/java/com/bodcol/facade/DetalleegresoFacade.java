/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.facade;

import com.bodcol.entidades.Detalleegreso;
import com.bodcol.entidades.Egreso;
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
public class DetalleegresoFacade extends AbstractFacade<Detalleegreso> {

    @PersistenceContext(unitName = "bodColPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleegresoFacade() {
        super(Detalleegreso.class);
    }
    
    
    
        //metod para guardar el detalle de las ventas
    public boolean guardarDetallesIngreso(Detalleegreso detalleEgreso){
        System.out.println("inicio del metodo para guardar el detalle..........");
        em.persist(detalleEgreso);
        System.out.println("despues del ingreso del detalle............");
        return true;
    }
    
    
    // METODO PARA RECUPERAR LOS DETALLES DE LOS EGRESOS 
        public List<Detalleegreso> obtenerDetalleEgreso(Egreso egreId)throws Exception{
        Query query=em.createNamedQuery("Detalleegreso.findByDetadetaEgreEgreId", Detalleegreso.class);
        query.setParameter("detaEgreEgreId", egreId);
        return query.getResultList();
    }
}

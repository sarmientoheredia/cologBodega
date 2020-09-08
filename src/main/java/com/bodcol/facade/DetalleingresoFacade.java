/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.facade;

import com.bodcol.entidades.Detalleingreso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cbos- Com. Sarmiento H. Luis A.
 */
@Stateless
public class DetalleingresoFacade extends AbstractFacade<Detalleingreso> {

    @PersistenceContext(unitName = "bodColPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleingresoFacade() {
        super(Detalleingreso.class);
    }
    
    //metod para guardar el detalle de las ventas
    public boolean guardarDetallesIngreso(Detalleingreso detalleIngreso){
        System.out.println("inicio del metodo para guardar el detalle..........");
        em.persist(detalleIngreso);
        System.out.println("despues del ingreso del detalle............");
        return true;
    }
    
}

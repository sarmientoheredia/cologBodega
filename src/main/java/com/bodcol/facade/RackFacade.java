/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.facade;

import com.bodcol.entidades.Rack;
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
public class RackFacade extends AbstractFacade<Rack> {

    @PersistenceContext(unitName = "bodColPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RackFacade() {
        super(Rack.class);
    }

    public boolean verificarNombreRack(String rackNombre) {
        Query query = em.createNamedQuery("Rack.findByRackNombre", Rack.class);
        query.setParameter("rackNombre", rackNombre);
        List<Rack> listado = query.getResultList();
        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }



    public List<Rack> recuperarRaks(Seccion seccion) {
        System.out.println("inicio del metodo de recuperar");
        String jpql=("SELECT r FROM Rack r where r.rackSeccId=:rackCodigo");
        Query query=em.createQuery(jpql);
        query.setParameter("rackCodigo", seccion);
        return query.getResultList();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bodcol.facade;

import com.bodcol.entidades.Producto;
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
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "bodColPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    //MEMTODO PARA VER SI EN LA BASE DE DATOS YA EXISTE UN PRODUCTO CON EL MISMO CODIGO NO LO PERMITA REGISTRER
    public boolean verificarCodigoProducto(String prodCodigo, String prodNombre) {
        Query query = em.createNamedQuery("Producto.findByProdCodigo", Producto.class);
        query.setParameter("prodCodigo", prodCodigo);
        query.setParameter("prodNombre", prodNombre);
        List<Producto> listado = query.getResultList();

        if (!listado.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    


}

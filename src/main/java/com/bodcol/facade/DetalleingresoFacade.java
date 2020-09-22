package com.bodcol.facade;

import com.bodcol.entidades.Detalleingreso;
import com.bodcol.entidades.Ingreso;
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
    
    
    public List<Detalleingreso> obtenerDetalleIngreso(Ingreso ingrId)throws Exception{
        Query query=em.createNamedQuery("Detalleingreso.findByDetadetaIngrIngrId", Detalleingreso.class);
        query.setParameter("detaIngrIngrId", ingrId);
        return query.getResultList();
    }
    
}

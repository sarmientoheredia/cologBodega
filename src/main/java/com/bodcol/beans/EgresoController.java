package com.bodcol.beans;

import com.bodcol.entidades.Egreso;
import com.bodcol.beans.util.JsfUtil;
import com.bodcol.beans.util.JsfUtil.PersistAction;
import com.bodcol.entidades.Detalleegreso;
import com.bodcol.entidades.Producto;
import com.bodcol.facade.DetalleegresoFacade;
import com.bodcol.facade.EgresoFacade;
import com.bodcol.facade.ProductoFacade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

@Named("egresoController")
@ViewScoped
public class EgresoController implements Serializable {

    @EJB
    private DetalleegresoFacade detalleegresoFacade;

        
    @EJB
    private ProductoFacade productoFacade;
    @EJB
    private com.bodcol.facade.EgresoFacade ejbFacade;
    private List<Egreso> items = null;
    private Egreso selected;
    private Producto productoSeleccionado;

    private List<Detalleegreso> detalleEgreso;
    
    //lista para acceder al la lista que tiene ese id de ingreso
    private List<Detalleegreso> detallesFacturaEgreso;
    
    
    private Producto prod;

    private boolean enabled = false;
    private int numeroOrdenEgreso;
    private BigDecimal totalCompra;
    private BigDecimal cantidadProducto;

    @PostConstruct
    public void init() {
        selected = new Egreso();
        detalleEgreso=new ArrayList<>();
        totalCompra = new BigDecimal(0);
        productoSeleccionado = new Producto();
        detallesFacturaEgreso = new ArrayList<>();
    }

    public EgresoController() {
    }

    public int getNumeroOrdenEgreso() {
        return numeroOrdenEgreso;
    }



    //metodo para activar y desactvar
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void enableButton() {
        enabled = true;

    }

    public void disableButton() {
        enabled = false;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }

    public List<Detalleegreso> getDetalleEgreso() {
        return detalleEgreso;
    }

    public void setDetalleEgreso(List<Detalleegreso> detalleEgreso) {
        this.detalleEgreso = detalleEgreso;
    }

    public BigDecimal getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(BigDecimal cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public List<Detalleegreso> getDetallesFacturaEgreso() {
        return detallesFacturaEgreso;
    }

    public void setDetallesFacturaEgreso(List<Detalleegreso> detallesFacturaEgreso) {
        this.detallesFacturaEgreso = detallesFacturaEgreso;
    }
    
        //METODO PARA FILTRAR POR CUALQUIER CAMPO
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();

        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Egreso egreso = (Egreso) value;
        return egreso.getEgreId() == filterInt
                || egreso.getEgreDepenUsar().toLowerCase().contains(filterText)
                || egreso.getEgreUsuaSolicita().getUsuaApellido().toLowerCase().contains(filterText);
    }

//    ingreso.getProdNombre().toLowerCase().contains(filterText)
    //METODO PARA CONVERTIR EL ID
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    

    //METODO QUE ENVIA EL OBJETO DE LA VISTA AL FACADE PARA RECUPERAR LOS DATOS
        
    public void verDetalle(Egreso egreso) throws Exception{
        detallesFacturaEgreso=detalleegresoFacade.obtenerDetalleEgreso(egreso);
    }
    
    
    
    //metodo para contar todas las ordenas de ingreso que se encuantran en la base de datos 
    public void contarIngreso() {
        Egreso egreso;
        egreso = ejbFacade.contarEgresos();
        numeroOrdenEgreso = egreso.getEgreId()+1;
    }

    //METODO PARA LIMPIAR LA FACTURA DE INGRESO
    public void limpiarFactura() {
        System.out.println("metodo para limpiar ");
        selected = new Egreso();
        totalCompra = new BigDecimal(0);
        detalleEgreso = new ArrayList<>();
        numeroOrdenEgreso = 0;
        disableButton();
    }

    //metodo para capturar el producto seleccionado
    public void capturarProducto(Producto producto) {
        productoSeleccionado = producto;
    }
    
    //MERODO PARA OBTENR EL OBJETO DE LA TABLA INGRESO
    public void capturarEgreso(Egreso egreso){
        System.out.println(egreso);
        selected=egreso;
    }

    public Producto getProd() {
        return prod;
    }

    public void setProd(Producto prod) {
        this.prod = prod;
    }
    
    

    //merodo para pasar el producto a la tabla 
    public void agragarProducto() {
        System.out.println("inicio del metodo agregar producto..............");
        try {
            
            if (this.cantidadProducto.equals(0) || this.cantidadProducto == null) {
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Producto no agregado"));
            } else {
                prod = productoFacade.obtenerProducto(productoSeleccionado);
                BigDecimal precio= prod.getProdCostActual();
                
                this.detalleEgreso.add(new Detalleegreso(cantidadProducto,precio, precio.multiply(cantidadProducto),prod));
                System.out.println("salida de guardado en el detalle");
                cantidadProducto = null;
                CalcularTotalFactura();
            }
        } catch (Exception e) {
        }
    }

    //metodo para calcular el toral de la factura
    public void CalcularTotalFactura() {
        System.out.println("ingresando al metodo de calcular el total"+totalCompra);
        BigDecimal totalXProducto = new BigDecimal(0);
        try {
            for (Detalleegreso itemProducto : detalleEgreso) {
                totalXProducto = itemProducto.getDetaEgreCantEgresa().multiply(prod.getProdCostActual());
            }
            totalCompra = totalCompra.add(totalXProducto);
        } catch (Exception e) {
        }
    }

    
        //METODO PARA GUARDAR EL INGRESO Y EL DETALLE DEL INGRESO 
    public void guradarIngreso() {
        try {
            Egreso egre;
            selected.setEgreTotal(totalCompra);
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IngresoCreated"));
            egre = ejbFacade.contarEgresos();
            for (Detalleegreso listado : detalleEgreso) {
                listado.setDetaEgreEgreId(egre);
                detalleegresoFacade.guardarDetallesIngreso(listado);
            }
            
        } catch (Exception e) {
        }

    }
    
    
    
    
    
    
    
    
    public Egreso getSelected() {
        return selected;
    }

    public void setSelected(Egreso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EgresoFacade getFacade() {
        return ejbFacade;
    }

    public Egreso prepareCreate() {
        selected = new Egreso();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        System.out.println("para ver de que forma envia a la base ");
        System.out.println(selected.getEgreFecha());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EgresoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EgresoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EgresoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Egreso> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Egreso getEgreso(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Egreso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Egreso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Egreso.class)
    public static class EgresoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EgresoController controller = (EgresoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "egresoController");
            return controller.getEgreso(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Egreso) {
                Egreso o = (Egreso) object;
                return getStringKey(o.getEgreId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Egreso.class.getName()});
                return null;
            }
        }

    }

}

package com.bodcol.beans;

import com.bodcol.entidades.Ingreso;
import com.bodcol.beans.util.JsfUtil;
import com.bodcol.beans.util.JsfUtil.PersistAction;
import com.bodcol.entidades.Detalleingreso;
import com.bodcol.entidades.Producto;
import com.bodcol.facade.DetalleingresoFacade;
import com.bodcol.facade.IngresoFacade;
import com.bodcol.facade.ProductoFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;
import javax.faces.view.ViewScoped;

@Named("ingresoController")
@ViewScoped
public class IngresoController implements Serializable {

    @EJB
    private DetalleingresoFacade detalleingresoFacade;

    @EJB
    private ProductoFacade productoFacade;

    @EJB
    private com.bodcol.facade.IngresoFacade ejbFacade;

    private List<Ingreso> items = null;
    private List<Detalleingreso> detalles;

    //lista para acceder al la lista que tiene ese id de ingreso
    private List<Detalleingreso> detallesFactura;

    private Ingreso selected;
    private Ingreso ingresoSeleccionado;

    private int numeroOrdenIngreso;

    private boolean activador = false;

    private Producto productoSeleccionado;

    private BigDecimal cantidadProducto;
    private BigDecimal precioProducto;
    private BigDecimal totalCompra;

    public IngresoController() {
    }

    @PostConstruct
    public void init() {
        selected = new Ingreso();
        productoSeleccionado = new Producto();
        detalles = new ArrayList<>();
        totalCompra = new BigDecimal(0);
        detallesFactura = new ArrayList<>();
    }

    public boolean isActivador() {
        return activador;
    }

    public void setActivador(boolean activador) {
        this.activador = activador;
    }

    //METODO PARA FILTRAR POR CUALQUIER CAMPO
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();

        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Ingreso ingreso = (Ingreso) value;
        return ingreso.getIngrId() == filterInt
                || ingreso.getIngrNumeFactura().toLowerCase().contains(filterText);
    }

    //METODO PARA CONVERTIR EL ID
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public Ingreso getSelected() {
        return selected;
    }

    public void setSelected(Ingreso selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IngresoFacade getFacade() {
        return ejbFacade;
    }

    public int getNumeroOrdenIngreso() {
        return numeroOrdenIngreso;
    }

    public BigDecimal getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(BigDecimal cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public List<Detalleingreso> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalleingreso> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Ingreso getIngresoSeleccionado() {
        return ingresoSeleccionado;
    }

    public void setIngresoSeleccionado(Ingreso ingresoSeleccionado) {
        this.ingresoSeleccionado = ingresoSeleccionado;
    }

    public List<Detalleingreso> getDetallesFactura() {
        return detallesFactura;
    }

    public void setDetallesFactura(List<Detalleingreso> detallesFactura) {
        this.detallesFactura = detallesFactura;
    }


    
    public void prueba(Ingreso ingreso) throws Exception{
        detallesFactura=detalleingresoFacade.obtenerDetalleIngreso(ingreso);
        
    }

    //METODO PARA LIMPIAR LA FACTURA DE INGRESO
    public void limpiarFactura() {
        selected = new Ingreso();
        totalCompra = new BigDecimal(0);
        detalles = new ArrayList<>();
        numeroOrdenIngreso = 0;
        disableButton();
    }

    //METODO PARA LIMPIAR LA VISTA DE LA FATURA
    public void limpiarDetalle() {
        selected = null;
        detalles = null;
        detallesFactura = null;
    }

    //metodo para capturar el producto seleccionado
    public void capturarProducto(Producto producto) {
        productoSeleccionado = producto;

    }

    //merodo para pasar el producto a la tabla 
    public void agragarProducto() {
        try {
            if (this.cantidadProducto.equals(0) || this.cantidadProducto == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Producto no agregado"));
            } else {
                Producto prod = productoFacade.obtenerProducto(productoSeleccionado);
                this.detalles.add(new Detalleingreso(cantidadProducto, precioProducto, cantidadProducto.multiply(precioProducto), prod));
                cantidadProducto = null;
                precioProducto = null;
                CalcularTotalFactura();
            }
        } catch (Exception e) {
        }
    }

    //metodo para calcular el toral de la factura
    public void CalcularTotalFactura() {
        BigDecimal totalXProducto = new BigDecimal(0);
        try {
            for (Detalleingreso itemProducto : detalles) {
                totalXProducto = itemProducto.getDetaIngrCantIngresa().multiply(itemProducto.getDeraIngrPreciIngresa());
            }
            totalCompra = totalCompra.add(totalXProducto);
        } catch (Exception e) {
        }
    }

    //METODO PARA GUARDAR EL INGRESO Y EL DETALLE DEL INGRESO 
    public void guradarIngreso() {
        try {
            Ingreso ingr;
            selected.setIngrTotal(totalCompra);
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IngresoCreated"));
            ingr = ejbFacade.contarIngresos();
            for (Detalleingreso items : detalles) {
                items.setDetaIngrIngrId(ingr);
                detalleingresoFacade.guardarDetallesIngreso(items);
            }

        } catch (Exception e) {
        }

    }

    public void enableButton() {
        activador = true;

    }

    public void disableButton() {
        activador = false;
    }

    //metodo para contar todas las ordenas de ingreso que se encuantran en la base de datos 
    public void contarIngreso() {
        Ingreso ingreso;
        ingreso = ejbFacade.contarIngresos();
        numeroOrdenIngreso = ingreso.getIngrId() + 1;

    }

    public Ingreso prepareCreate() {
        selected = new Ingreso();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IngresoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {

        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IngresoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IngresoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Ingreso> getItems() {
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

    public Ingreso getIngreso(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Ingreso> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ingreso> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ingreso.class)
    public static class IngresoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IngresoController controller = (IngresoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ingresoController");
            return controller.getIngreso(getKey(value));
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
            if (object instanceof Ingreso) {
                Ingreso o = (Ingreso) object;
                return getStringKey(o.getIngrId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ingreso.class.getName()});
                return null;
            }
        }

    }

}

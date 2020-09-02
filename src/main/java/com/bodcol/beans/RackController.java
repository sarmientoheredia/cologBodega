package com.bodcol.beans;

import com.bodcol.entidades.Rack;
import com.bodcol.beans.util.JsfUtil;
import com.bodcol.beans.util.JsfUtil.PersistAction;
import com.bodcol.facade.RackFacade;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;

@Named("rackController")
@ViewScoped
public class RackController implements Serializable {

    @EJB
    private com.bodcol.facade.RackFacade ejbFacade;
    private List<Rack> items = null;
    private Rack selected;

    public RackController() {
    }

    //METODO PARA FILTRAR POR CUALQUIER CAMPO
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Rack rack = (Rack) value;
        return rack.getRackNombre().toLowerCase().contains(filterText)
                || rack.getRackSeccId().getSeccNombre().toLowerCase().contains(filterText)
                || rack.getRackId() == filterInt;
    }

    //METODO PARA CONVERTIR EL ID
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    //METODO PARA LIMPIAR LOS COMPONENTES
    public void limpiar() {
        selected = null;
        items = null;
    }

    public Rack getSelected() {
        return selected;
    }

    public void setSelected(Rack selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RackFacade getFacade() {
        return ejbFacade;
    }

    public Rack prepareCreate() {
        selected = new Rack();
        initializeEmbeddableKey();
        return selected;
    }



    public void create() {
        if (ejbFacade.verificarNombreRack(selected.getRackNombre())) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El rack ya esta registrado", "Error"));
            selected = null;
        } else {
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RackCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }
    }

    public void update() {
        if (ejbFacade.verificarNombreRack(selected.getRackNombre())) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El rack ya esta registrado", "Error"));
            selected = null;
        } else {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RackUpdated"));
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RackDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Rack> getItems() {
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

    public Rack getRack(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Rack> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Rack> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Rack.class)
    public static class RackControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RackController controller = (RackController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rackController");
            return controller.getRack(getKey(value));
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
            if (object instanceof Rack) {
                Rack o = (Rack) object;
                return getStringKey(o.getRackId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Rack.class.getName()});
                return null;
            }
        }

    }

}

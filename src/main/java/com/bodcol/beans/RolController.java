package com.bodcol.beans;

import com.bodcol.entidades.Rol;
import com.bodcol.beans.util.JsfUtil;
import com.bodcol.beans.util.JsfUtil.PersistAction;
import com.bodcol.facade.RolFacade;

import java.io.Serializable;
import java.util.List;
import java.util.*;
import java.util.logging.*;
import javax.ejb.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.*;
import javax.faces.view.ViewScoped;

@Named("rolController")
@ViewScoped
public class RolController implements Serializable {

    @EJB
    private com.bodcol.facade.RolFacade ejbFacade;
    private List<Rol> items = null;
    private Rol selected;
    private static final Logger LOG = Logger.getLogger(RolController.class.getName());

    public RolController() {
    }

    //METODO PARA FILTRAR POR CUALQUIER CAMPO
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Rol rol = (Rol) value;
        return rol.getRolNombre().toLowerCase().contains(filterText)
                || rol.getRolId() == filterInt;
    }

    //METODO PARA CONVERTIR EL ID
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public void limpiar(){
    selected=null;
    items=null;
}
    
    public Rol getSelected() {
        return selected;
    }

    public void setSelected(Rol selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RolFacade getFacade() {
        return ejbFacade;
    }

    public Rol prepareCreate() {
        selected = new Rol();
        initializeEmbeddableKey();
        return selected;
    }

//    LOG.log(Level.SEVERE, "error de base", ex);
    public void create() {
        if (ejbFacade.varificarRol(selected.getRolNombre())) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El rol ya esta registrado", "Error"));
            selected = null;
        } else {
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("RolCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;
            }
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("RolUpdated"));
    }

    public void destroy() {

        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("RolDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Rol> getItems() {
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
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "prueba", ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Rol getRol(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Rol> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Rol> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Rol.class)
    public static class RolControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RolController controller = (RolController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "rolController");
            return controller.getRol(getKey(value));
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
            if (object instanceof Rol) {
                Rol o = (Rol) object;
                return getStringKey(o.getRolId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Rol.class.getName()});
                return null;
            }
        }

    }

}

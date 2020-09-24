package com.bodcol.beans;

import com.bodcol.entidades.Seccion;
import com.bodcol.beans.util.JsfUtil;
import com.bodcol.beans.util.JsfUtil.PersistAction;
import com.bodcol.entidades.Rack;
import com.bodcol.facade.SeccionFacade;
import java.awt.event.ActionEvent;
import java.io.File;

import java.io.Serializable;
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
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named("seccionController")
@ViewScoped
public class SeccionController implements Serializable {

    @EJB
    private com.bodcol.facade.SeccionFacade ejbFacade;
    private List<Seccion> items = null;
    private Seccion selected;
    private List<Rack> listaracks;
    private Rack racks;

    @PostConstruct
    public void init() {
        racks = new Rack();
    }

    public SeccionController() {
    }

    //METODO PARA FILTRAR POR CUALQUIER CAMPO
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Seccion seccion = (Seccion) value;
        return seccion.getSeccNombre().toLowerCase().contains(filterText)
                || seccion.getSeccId() == filterInt;

    }

    public void limpiar() {
        selected = null;
        items = null;
    }

    //METODO PARA CONVERTIR EL ID
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public Seccion getSelected() {
        return selected;
    }

    public void setSelected(Seccion selected) {
        this.selected = selected;
    }

    public List<Rack> getRacks() {
        return listaracks;
    }

    public void setRacks(List<Rack> racks) {
        this.listaracks = racks;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SeccionFacade getFacade() {
        return ejbFacade;
    }

    public Seccion prepareCreate() {
        selected = new Seccion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (ejbFacade.verificarNombreSeccion(selected.getSeccNombre())) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La seccion ya esta registrada", "Error"));
            selected = null;
        } else {
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SeccionCreated"));
            if (!JsfUtil.isValidationFailed()) {
                items = null;    // Invalidate list of items to trigger re-query.
            }
        }

    }

    //METODO PARA VER EL PDF EN EL NAVEGADOR
    public void verPDF(ActionEvent actionEvent) throws Exception {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/Seccion.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getItems()));
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void update() {
        if (ejbFacade.verificarNombreSeccion(selected.getSeccNombre())) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "La seccion ya esta registrada", "Error"));
            selected = null;
        } else {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SeccionUpdated"));
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SeccionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Seccion> getItems() {
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

    public Seccion getSeccion(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Seccion> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Seccion> getItemsAvailableSelectOne() {

        return getFacade().findAll();
    }

    @FacesConverter(forClass = Seccion.class)
    public static class SeccionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SeccionController controller = (SeccionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "seccionController");
            return controller.getSeccion(getKey(value));
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
            if (object instanceof Seccion) {
                Seccion o = (Seccion) object;
                return getStringKey(o.getSeccId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Seccion.class.getName()});
                return null;
            }
        }

    }

}

package com.bodcol.beans;

import com.bodcol.entidades.Proveedor;
import com.bodcol.beans.util.JsfUtil;
import com.bodcol.beans.util.JsfUtil.PersistAction;
import com.bodcol.facade.ProveedorFacade;

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

@Named("proveedorController")
@ViewScoped
public class ProveedorController implements Serializable {

    @EJB
    private com.bodcol.facade.ProveedorFacade ejbFacade;
    private List<Proveedor> items = null;
    private Proveedor selected;
    private int banderaRuc;
    private int banderaCedula;
    private static boolean isValid = false;

    private int tipoProv;
    
    private boolean enabled;
    private boolean activoRuc = false;
    private boolean activoRucPersonaNatural = false;

    private boolean activoCedula = true;

    public ProveedorController() {
    }

    //METODO PARA FILTRAR POR CUALQUIER CAMPO
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Proveedor proveedor = (Proveedor) value;
        return proveedor.getProvCedula().toLowerCase().contains(filterText)
                || proveedor.getProvNombre().toLowerCase().contains(filterText);
    }

    //METODO PARA CONVERTIR EL ID
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public int getTipoProv() {
        return tipoProv;
    }

    public void setTipoProv(int tipoProv) {
        this.tipoProv = tipoProv;
    }

    
    
    
    
    
    public void activarCajas() {
        switch (tipoProv) {
            case 0:
                desactivarCajas();
                break;
            case 1:
                activarRucNatural();

                break;
            case 2:
                activarRuc();
                break;

            case 3:
                desactivarCajas();
                break;
        }
    }

    private static final int NUM_PROVINCIAS = 24;
    private static int[] coeficientes = {4, 3, 2, 7, 6, 5, 4, 3, 2};
    private static int constante = 11;

    //METODO DE OPERACION PAR VALIDAR EL RUC
    public static Boolean operacionRuc(String ruc) {
        boolean resp_dato = false;
        final int prov = Integer.parseInt(ruc.substring(0, 2));
        if (!((prov > 0) && (prov <= NUM_PROVINCIAS))) {
            resp_dato = false;
        }

        int[] d = new int[10];
        int suma = 0;

        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(ruc.charAt(i) + "");
        }

        for (int i = 0; i < d.length - 1; i++) {
            d[i] = d[i] * coeficientes[i];
            suma += d[i];
        }

        int aux, resp;

        aux = suma % constante;
        resp = constante - aux;

        resp = (aux == 0) ? 0 : resp;

        if (resp == d[9]) {
            resp_dato = true;
        } else {
            resp_dato = false;
        }
        return resp_dato;
    }

    //METODO PARA QUE SE EJECUTE LA COMPROBACION DEL RUC
    public void validarRuc() {
        //String ruc_dato = "1790011674001";
        String ruc_dato = selected.getProvRuc();
        if (operacionRuc(ruc_dato)) {
            banderaRuc = 1;
            System.out.println("El RUC es correcto");
        } else {
            banderaRuc = 0;
            System.out.println("El RUC es incorrecto");
        }
    }

    //METODO PARA EJECUTAR LA VALIDACION DEL RUC PERO CON AJAX
    public void validarRucAjax() {
        if (selected.getProvRuc().length() == 13) {
            validarRuc();
        }
    }

    //METODO PARA EJECUTAR LA VALIDACION DEL RUC PERO CON AJAX
    public void validarRucNaturalAjax() {

        if (validacionRucPersonaNatural(selected.getProvRucNatural().substring(0, 10))) {
            System.out.println("el ruc es correcto");
        } else {
            System.out.println("el ruc es incorrecto");
        }
    }

    //metod ajax para ver que tenga los 10 digitos de la cedula
    public void validarCedula() {
        if (selected.getProvCedula().length() == 10) {
            operacionCedula();
        }
    }

    public void operacionCedula() {
        int c, suma = 0, acum, resta = 0;

        for (int i = 0; i < selected.getProvCedula().length() - 1; i++) {
            c = Integer.parseInt(selected.getProvCedula().charAt(i) + "");
            if (i % 2 == 0) {
                c = c * 2;
                if (c > 9) {
                    c = c - 9;
                }
            }
            suma = suma + c;
        }
        if (suma % 10 != 0) {
            acum = ((suma / 10) + 1) * 10;
            resta = acum - suma;
        }
        int ultimo = Integer.parseInt(selected.getProvCedula().charAt(9) + "");
        if (ultimo == resta) {
            banderaCedula = 1;
            System.out.println("cedula correcta");

        } else {
            banderaCedula = 0;
            System.out.println("cedula incorrecta");
        }
    }

    //METODO PARA VALIDAR EL RUC DE LA PERSONA NATURAL
    public static Boolean validacionRucPersonaNatural(String cedula) {

        if (cedula == null || cedula.length() != 10) {
            isValid = false;
        }
        final int prov = Integer.parseInt(cedula.substring(0, 2));

        if (!((prov > 0) && (prov <= NUM_PROVINCIAS))) {
            isValid = false;
        }

        int[] d = new int[10];
        for (int i = 0; i < d.length; i++) {
            d[i] = Integer.parseInt(cedula.charAt(i) + "");
        }

        int imp = 0;
        int par = 0;

        for (int i = 0; i < d.length; i += 2) {
            d[i] = ((d[i] * 2) > 9) ? ((d[i] * 2) - 9) : (d[i] * 2);
            imp += d[i];
        }

        for (int i = 1; i < (d.length - 1); i += 2) {
            par += d[i];
        }

        final int suma = imp + par;

        int d10 = Integer.parseInt(String.valueOf(suma + 10).substring(0, 1)
                + "0")
                - suma;

        d10 = (d10 == 10) ? 0 : d10;

        if (d10 == d[9]) {
            isValid = true;
        } else {
            isValid = false;
        }

        return isValid;
    }

    //METODO PARA ACTIVAR O DESACTIVAR EL IMPUT TEXT DEL RUC
    public void activarRuc() {

        activoRuc = true;
        activoRucPersonaNatural = false;
    }

    public void activarRucNatural() {

        activoRucPersonaNatural = true;
        activoRuc = false;
    }

    public void desactivarCajas() {
        activoRucPersonaNatural = false;
        activoRuc = false;
    }

    //METODO PARA ACTIVAR O DESACTIVAR EL IMPUT TEXT DE LA CEDULA 
    public void activarCedula() {
        enabled = true;
    }

    public void desactivarBoton() {
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isActivoRuc() {
        return activoRuc;
    }

    public void setActivoRuc(boolean activoRuc) {
        this.activoRuc = activoRuc;
    }

    public boolean isActivoCedula() {
        return activoCedula;
    }

    public void setActivoCedula(boolean activoCedula) {
        this.activoCedula = activoCedula;
    }

    public Proveedor getSelected() {
        return selected;
    }

    public void setSelected(Proveedor selected) {
        this.selected = selected;
    }

    public boolean isActivoRucPersonaNatural() {
        return activoRucPersonaNatural;
    }

    public void setActivoRucPersonaNatural(boolean activoRucPersonaNatural) {
        this.activoRucPersonaNatural = activoRucPersonaNatural;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ProveedorFacade getFacade() {
        return ejbFacade;
    }

    public Proveedor prepareCreate() {
        selected = new Proveedor();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        System.out.println("bendera ruc " + banderaRuc);
        System.out.println("bendera cedula " + banderaCedula);
        System.out.println("bendera cedula " + isValid);

        if (banderaRuc == 1 && banderaCedula == 1 && isValid == true) {
            if (ejbFacade.verificarRucCedulaNatural(selected.getProvRuc(), selected.getProvCedula(), selected.getProvRucNatural())) {
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El proveedor ya esta registrado", "Error"));
                selected = null;
            } else {
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    selected = null;
                    items = null;
                }
            }
        } else if (banderaRuc == 0 && banderaCedula == 1 && isValid == false) {
            if (ejbFacade.verificarCedula(selected.getProvCedula())) {
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Este proveeedor ya esta registrado.", "Error"));
                selected = null;
            } else {
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    selected = null;
                    items = null;
                }
            }
        } else if (banderaRuc == 1 && banderaCedula == 0 && isValid == false) {
            if (ejbFacade.verificarRuc(selected.getProvRuc())) {
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El ruc ya registrada", "Error"));
                selected = null;
            } else {
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    selected = null;
                    items = null;
                }
            }
        } else if (banderaRuc == 0 && banderaCedula == 0 && isValid == true) {
            if (ejbFacade.verificarRucNatural(selected.getProvRucNatural())) {
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El proveedor ya existe", "Error"));
                selected = null;
            } else {
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    selected = null;
                    items = null;
                }
            }
        } else if (banderaRuc == 1 && banderaCedula == 0 && isValid == true) {
            if (ejbFacade.verificarCedulaRucNatural(selected.getProvRucNatural(), selected.getProvRucNatural())) {
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El proveedor ya existe", "Error"));
                selected = null;
            } else {
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    selected = null;
                    items = null;
                }
            }
        } else if (banderaRuc == 1 && banderaCedula == 1 && isValid == false) {
            if (ejbFacade.verificarRucCedula(selected.getProvRuc(), selected.getProvCedula())) {
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El proveedor ya existe", "Error"));
                selected = null;
            } else {
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    selected = null;
                    items = null;
                }
            }
        } else if (banderaRuc == 0 && banderaCedula == 1 && isValid == true) {
            if (ejbFacade.verificarCedulaRucNatural(selected.getProvCedula(), selected.getProvRucNatural())) {
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El proveedor ya existe", "Error"));
                selected = null;
            } else {
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    selected = null;
                    items = null;
                }
            }
        } else if (banderaRuc == 0 && banderaCedula == 0 && isValid == false) {
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numero de documentos no validos", "Error"));
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ProveedorUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ProveedorDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Proveedor> getItems() {
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

    public Proveedor getProveedor(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Proveedor> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Proveedor> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Proveedor.class)
    public static class ProveedorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProveedorController controller = (ProveedorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "proveedorController");
            return controller.getProveedor(getKey(value));
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
            if (object instanceof Proveedor) {
                Proveedor o = (Proveedor) object;
                return getStringKey(o.getProvId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Proveedor.class.getName()});
                return null;
            }
        }

    }

}

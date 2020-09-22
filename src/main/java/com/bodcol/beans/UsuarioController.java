package com.bodcol.beans;

import com.bodcol.entidades.Usuario;
import com.bodcol.beans.util.JsfUtil;
import com.bodcol.beans.util.JsfUtil.PersistAction;
import com.bodcol.facade.UsuarioFacade;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Named("usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

    private static final Logger LOG = Logger.getLogger(UsuarioController.class.getName());

    @EJB
    private com.bodcol.facade.UsuarioFacade ejbFacade;
    private List<Usuario> items = null;
    private Usuario selected;

    private PlantillaController usuarioPlantilla;

    private Usuario usuarioSeleccionado;
    private int bandera;
    
    public UsuarioController() {
    }

    @PostConstruct
    public void init() {
        usuarioSeleccionado = new Usuario();
        selected = new Usuario();
    }

    
    //METODO PARA GENERAR LOS REPORTES
    	public void exportarPDF(ActionEvent actionEvent) throws JRException, IOException{
            try {
                
                
               	Map<String,Object> parametros= new HashMap<String,Object>();
		parametros.put("txtUsuario", "Sarmiento");
		
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/Usuario.jasper"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(),parametros, new JRBeanCollectionDataSource(this.getItems()));
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition","attachment; filename=jsfReporte.pdf");
		ServletOutputStream stream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		stream.flush();
		stream.close();
		FacesContext.getCurrentInstance().responseComplete(); 
            } catch (IOException | JRException e) {
                System.out.println("el error es "+e);
            }
	}
    
        
        //METODO PARA VER EL PDF EN EL NAVEGADOR
        	public void verPDF(ActionEvent actionEvent) throws Exception{
		File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("reportes/Usuario.jasper"));		
		
		byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, new JRBeanCollectionDataSource(this.getItems()));
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(bytes, 0 , bytes.length);
		outStream.flush();
		outStream.close();
			
		FacesContext.getCurrentInstance().responseComplete();
	
                }
    
    
    
    
    
    
    
    
    
    
    
    
    
    //METODO PARA FILTRAR POR CUALQUIER CAMPO
    public boolean globalFilterFunction(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase();
        if (filterText == null || filterText.equals("")) {
            return true;
        }
        int filterInt = getInteger(filterText);

        Usuario usuario = (Usuario) value;
        return usuario.getUsuaCedula().toLowerCase().contains(filterText)
                || usuario.getUsuaApellido().toLowerCase().contains(filterText)
                || usuario.getUsuaNombre().toLowerCase().contains(filterText)
                || usuario.getUsuaGrado().toLowerCase().contains(filterText)
                || usuario.getUsuaiArma().toLowerCase().contains(filterText)
                || usuario.getUsuaRolId().getRolNombre().toLowerCase().contains(filterText)
                || usuario.getUsuaId() == filterInt;
    }

    //METODO PARA CONVERTIR EL ID
    private int getInteger(String string) {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    public PlantillaController getUsuarioPlantilla() {
        return usuarioPlantilla;
    }

    public void setUsuarioPlantilla(PlantillaController usuarioPlantilla) {
        this.usuarioPlantilla = usuarioPlantilla;
    }

    //METODO PARA ACTUALIZAR EL USUARIO Y LA CLAVE
    public void updateCredenciales(Usuario usuario) {
        if (usuario != null) {
            selected = ejbFacade.editarCredenciales(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Credenciales Modificadas con exito", "Credenciales Modificadas"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No hay credenciales"));
        }

    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();

        initializeEmbeddableKey();
        return selected;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public void create() {
        //metodo para verificar si existe en la base de datos 
        System.out.println("bandera " + bandera);
        if (bandera == 1) {
            if (ejbFacade.verificarCedula(selected.getUsuaCedula())) {
                FacesContext contexto = FacesContext.getCurrentInstance();
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "El usuario ya esta registrado.", "Error"));
                selected = null;
            } else {
                selected.setUsuaUsuario(selected.getUsuaCedula());
                selected.setUsuaPassword(selected.getUsuaCedula());
                persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    items = null;    // Invalidate list of items to trigger re-query.
                }
            }
        } else {
            FacesContext contexto = FacesContext.getCurrentInstance();
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Numero de cedula invalido", "Error"));
        }

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void limpiar() {
        selected = null;
        items = null;
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
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

    public Usuario getUsuario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    //metod ajax para ver que tenga los 10 digitos de la cedula
    public void validarCedula() {

        if (selected.getUsuaCedula().length() == 10) {
            operacionCedula();

        }
    }

    public void operacionCedula() {
        int c, suma = 0, acum, resta = 0;

        for (int i = 0; i < selected.getUsuaCedula().length() - 1; i++) {
            c = Integer.parseInt(selected.getUsuaCedula().charAt(i) + "");
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
        int ultimo = Integer.parseInt(selected.getUsuaCedula().charAt(9) + "");
        if (ultimo == resta) {
            bandera = 1;

        } else {
            bandera = 0;
        }
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getUsuaId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

}

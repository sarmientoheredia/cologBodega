package com.bodcol.beans;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Cbos- Com. Sarmiento H. Luis A.
 */
@Named("validadorController")
@RequestScoped
public class ValidadorController implements Serializable {

    //METODO QUE PERMITE SOLO EL USO DE TEXTO EN LOS INPUT TEXT 
    public void validarLetras(FacesContext context, UIComponent toValidate, Object value) {
        context = FacesContext.getCurrentInstance();
        String texto = (String) value;

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                ((UIInput) toValidate).setValid(false);
                context.addMessage(toValidate.getClientId(context), new FacesMessage("Unicamente Puede Ingresar Letras"));
            }
        }
    }

    //METODO PARA VALIDAR SOLO NUMEROS EN LOS IMPUTTEXT
    public void validarNumeros(FacesContext context, UIComponent toValidate, Object value) {
        context = FacesContext.getCurrentInstance();
        String texto = (String) value;
            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i);
                if (!((c >= 0 && c <= 9))) {
                    ((UIInput) toValidate).setValid(false);
                    context.addMessage(toValidate.getClientId(context), new FacesMessage("Unicamente Puede Ingresar Numeros"));
                }
            }
        
    }
    
    
    
        //METODO PARA VALIDAR SOLO NUMEROS EN LOS IMPUTTEXT
    public void validarEmail(FacesContext context, UIComponent toValidate, Object value) {
        context = FacesContext.getCurrentInstance();
        String texto = (String) value;
        if (texto.length() == 10) {
            for (int i = 0; i < texto.length(); i++) {
                char c = texto.charAt(i);
                if (!((c >= 0 && c <= 9))) {
                    ((UIInput) toValidate).setValid(false);
                    context.addMessage(toValidate.getClientId(context), new FacesMessage("Unicamente Puede Ingresar Numeros"));
                }
            }
        }
    }

}

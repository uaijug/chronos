/*
 * 
 */
package br.com.uaijug.chronos.web.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

// TODO: Auto-generated Javadoc
/**
 * The Class JsfUtils.
 *
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 */
public class JsfUtils {
    
    /**
     * Gets the request parameter.
     *
     * @param name the name
     * @return the request parameter
     */
    public static String getRequestParameter(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext().getRequestParameterMap().get(name);
    }
    
    /**
     * Checks if is blank.
     *
     * @param string the string
     * @return true, if is blank
     */
    public static boolean isBlank(String string) {
        return string == null || string.isEmpty();
    }
    
    /**
     * Show message.
     *
     * @param message the message
     * @param severity the severity
     */
    protected static void showMessage(String message, FacesMessage.Severity severity) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(severity, message, message));
    }
    
    /**
     * Show info message.
     *
     * @param message the message
     */
    public static void showInfoMessage(String message) {
        showMessage(message, FacesMessage.SEVERITY_INFO);
    }
    
    /**
     * Show warn message.
     *
     * @param message the message
     */
    public static void showWarnMessage(String message) {
        showMessage(message, FacesMessage.SEVERITY_WARN);
    }
    
    /**
     * Show error message.
     *
     * @param message the message
     */
    public static void showErrorMessage(String message) {
        showMessage(message, FacesMessage.SEVERITY_ERROR);
    }
}

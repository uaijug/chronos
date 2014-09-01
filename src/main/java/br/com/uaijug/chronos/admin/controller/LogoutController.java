/*
 * 
 */
package br.com.uaijug.chronos.admin.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.uaijug.chronos.controller.AbstractManageBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class LogoutController.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Named
@SessionScoped
public class LogoutController extends AbstractManageBeans implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6920835789315490285L;

	
	/**
	 * Logout.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void logout() throws IOException {
		
		getFacesContext().getExternalContext().invalidateSession();
		
		redirect("/admin");
	
	}

}
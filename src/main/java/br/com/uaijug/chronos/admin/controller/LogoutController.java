package br.com.uaijug.chronos.admin.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.uaijug.chronos.controller.AbstractManageBeans;

@Named
@SessionScoped
public class LogoutController extends AbstractManageBeans implements Serializable {
	private static final long serialVersionUID = 6920835789315490285L;

	
	public void logout() throws IOException {
		
		getFacesContext().getExternalContext().invalidateSession();
		
		redirect("/admin");
	
	}

}
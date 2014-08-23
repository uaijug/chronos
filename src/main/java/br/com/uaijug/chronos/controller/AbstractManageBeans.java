package br.com.uaijug.chronos.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public abstract class AbstractManageBeans {

	// bundle com as mensagens internacionalizaveis
	protected ResourceBundle bundle;
	
	/** The ext context. */
	ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();

	/*
	 * @Inject private FacesContext facesContext;
	 * 
	 * protected void successMessage(String message) { FacesMessage m = new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", message);
	 * facesContext.addMessage(null, m); }
	 * 
	 * protected void unSuccessMessage(String message, Exception e) { String
	 * errorMessage = getRootErrorMessage(e); FacesMessage m = new
	 * FacesMessage(FacesMessage.SEVERITY_INFO, errorMessage, message);
	 * facesContext.addMessage(null, m); }
	 * 
	 * protected String getRootErrorMessage(Exception e) { // Default to general
	 * error message that registration failed. String errorMessage =
	 * "Registration failed. See server log for more information"; if (e ==
	 * null) { // This shouldn't happen, but return the default messages return
	 * errorMessage; }
	 * 
	 * // Start with the exception and recurse to find the root cause Throwable
	 * t = e; while (t != null) { // Get the message from the Throwable class
	 * instance errorMessage = t.getLocalizedMessage(); t = t.getCause(); } //
	 * This is the root cause message return errorMessage; }
	 * 
	 * public FacesContext getFacesContext() { return facesContext; }
	 */

	protected void successMessage(String message) {
		addMessageFromBundleInFlash(FacesMessage.SEVERITY_INFO, message);
	}

	protected void unSuccessMessage(String message) {
		getFacesContext().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "", bundle
						.getString(message)));
	}

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Faz o redirecionamento para uma pagina (caminho relativo)
	 * 
	 * @param pagina
	 */
	protected void redirect(String pagina) {
		try {
			getFacesContext().getExternalContext().redirect( extContext.getRequestContextPath() + pagina);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recebe como parametro uma chave, faz a traducao da chave para a mensagem
	 * olhando o bundle e a adiciona no escopo {@link Flash} da aplicacao.
	 * 
	 * @param chave
	 * @param severidade
	 */
	public void addMessageFromBundleInFlash(Severity severidade, String chave) {
		Flash flash = getFacesContext().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		getFacesContext().addMessage(null,
				new FacesMessage(severidade, null, bundle.getString(chave)));
	}

}

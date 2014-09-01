/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum InputOutput.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum InputOutput {
	/** The YES. */
	ENTRADA("ENTRADA"),
	/** The NO. */
	SAIDA("SAIDA");

	/** The status. */
	String status;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private InputOutput(String status) {
		this.status = status;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}

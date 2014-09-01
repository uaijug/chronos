/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum PaimentType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum PaimentType {

	/** The YES. */
	Dinheiro("Dinheiro"),
	/** The NO. */
	PagueSeguro("PagueSeguro");

	/** The status. */
	String paimentType;

	/**
	 * Instantiates a new status.
	 *
	 * @param paimentType the paiment type
	 */
	private PaimentType(String paimentType) {
		this.paimentType = paimentType;
	}

	/**
	 * Gets the paiment type.
	 *
	 * @return the paiment type
	 */
	public String getPaimentType() {
		return paimentType;
	}

	/**
	 * Sets the paiment type.
	 *
	 * @param paimentType the new paiment type
	 */
	public void setPaimentType(String paimentType) {
		this.paimentType = paimentType;
	}

}

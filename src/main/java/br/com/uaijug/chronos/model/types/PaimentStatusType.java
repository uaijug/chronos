/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum PaimentStatusType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum PaimentStatusType {

	/** The YES. */
	PAGO("Pago"),
	/** The NO. */
	NAO_PAGO("Não Pago");

	/** The status. */
	String paimentStatusType;

	/**
	 * Instantiates a new status.
	 *
	 * @param paimentStatusType the paiment status type
	 */
	private PaimentStatusType(String paimentStatusType) {
		this.paimentStatusType = paimentStatusType;
	}

	/**
	 * Gets the paiment status type.
	 *
	 * @return the paiment status type
	 */
	public String getPaimentStatusType() {
		return paimentStatusType;
	}

	/**
	 * Sets the paiment status type.
	 *
	 * @param paimentStatusType the new paiment status type
	 */
	public void setPaimentStatusType(String paimentStatusType) {
		this.paimentStatusType = paimentStatusType;
	}

}

package br.com.uaijug.chronos.model.types;

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
	 * @param status
	 *            the status
	 */
	private PaimentStatusType(String paimentStatusType) {
		this.paimentStatusType = paimentStatusType;
	}

	public String getPaimentStatusType() {
		return paimentStatusType;
	}

	public void setPaimentStatusType(String paimentStatusType) {
		this.paimentStatusType = paimentStatusType;
	}

}

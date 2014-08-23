package br.com.uaijug.chronos.model.types;

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
	 * @param status
	 *            the status
	 */
	private PaimentType(String paimentType) {
		this.paimentType = paimentType;
	}

	public String getPaimentType() {
		return paimentType;
	}

	public void setPaimentType(String paimentType) {
		this.paimentType = paimentType;
	}

}

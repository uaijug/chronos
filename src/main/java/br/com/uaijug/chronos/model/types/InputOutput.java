package br.com.uaijug.chronos.model.types;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

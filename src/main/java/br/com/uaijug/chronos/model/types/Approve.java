package br.com.uaijug.chronos.model.types;

public enum Approve {

	/** The YES. */
	SIM("SIM"),
	/** The NO. */
	NAO("NAO");

	/** The status. */
	String approve;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private Approve(String approve) {
		this.approve = approve;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

}

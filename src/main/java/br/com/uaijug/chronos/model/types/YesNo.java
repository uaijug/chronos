package br.com.uaijug.chronos.model.types;

public enum YesNo {

	/** The YES. */
	SIM("SIM"),
	/** The NO. */
	NAO("NAO");

	/** The status. */
	String yesNo;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private YesNo(String yesNo) {
		this.yesNo = yesNo;
	}

	public String getYesNo() {
		return yesNo;
	}

	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}

}

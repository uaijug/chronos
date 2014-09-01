/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum YesNo.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
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
	 * @param yesNo the yes no
	 */
	private YesNo(String yesNo) {
		this.yesNo = yesNo;
	}

	/**
	 * Gets the yes no.
	 *
	 * @return the yes no
	 */
	public String getYesNo() {
		return yesNo;
	}

	/**
	 * Sets the yes no.
	 *
	 * @param yesNo the new yes no
	 */
	public void setYesNo(String yesNo) {
		this.yesNo = yesNo;
	}

}

/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum Approve.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
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
	 * @param approve the approve
	 */
	private Approve(String approve) {
		this.approve = approve;
	}

	/**
	 * Gets the approve.
	 *
	 * @return the approve
	 */
	public String getApprove() {
		return approve;
	}

	/**
	 * Sets the approve.
	 *
	 * @param approve the new approve
	 */
	public void setApprove(String approve) {
		this.approve = approve;
	}

}

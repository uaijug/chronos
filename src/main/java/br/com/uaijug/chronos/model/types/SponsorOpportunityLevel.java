/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum SponsorOpportunityLevel.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum SponsorOpportunityLevel {

	/** The YES. */
	OPEN("Open"),

	/** The closed. */
	CLOSED("Closed");

	/** The status. */
	String sponsorOpportunityLevel;

	/**
	 * Instantiates a new status.
	 *
	 * @param sponsorOpportunityLevel the sponsor opportunity level
	 */
	private SponsorOpportunityLevel(String sponsorOpportunityLevel) {
		this.sponsorOpportunityLevel = sponsorOpportunityLevel;
	}

	/**
	 * Gets the sponsor opportunity level.
	 *
	 * @return the sponsor opportunity level
	 */
	public String getSponsorOpportunityLevel() {
		return sponsorOpportunityLevel;
	}

	/**
	 * Sets the sponsor opportunity level.
	 *
	 * @param sponsorOpportunityLevel the new sponsor opportunity level
	 */
	public void setSponsorOpportunityLevel(String sponsorOpportunityLevel) {
		this.sponsorOpportunityLevel = sponsorOpportunityLevel;
	}

}

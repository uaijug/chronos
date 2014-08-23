package br.com.uaijug.chronos.model.types;

public enum SponsorOpportunityLevel {

	/** The YES. */
	OPEN("Open"),

	CLOSED("Closed");

	/** The status. */
	String sponsorOpportunityLevel;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private SponsorOpportunityLevel(String sponsorOpportunityLevel) {
		this.sponsorOpportunityLevel = sponsorOpportunityLevel;
	}

	public String getSponsorOpportunityLevel() {
		return sponsorOpportunityLevel;
	}

	public void setSponsorOpportunityLevel(String sponsorOpportunityLevel) {
		this.sponsorOpportunityLevel = sponsorOpportunityLevel;
	}

}

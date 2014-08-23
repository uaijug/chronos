package br.com.uaijug.chronos.model.types;

public enum SponsorshipLevel {

	/** The YES. */
	DIAMOND("DIAMOND"),

	TITANIUM("TITANIUM"),
	
	PLATINUM("PLATINUM"),
	
	GOLD("GOLD"),
	
	SILVER("SILVER"),

	BRONZE("BRONZE");

	/** The status. */
	String sponsorshipLevel;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private SponsorshipLevel(String sponsorshipLevel) {
		this.sponsorshipLevel = sponsorshipLevel;
	}

	public String getSponsorshipLevel() {
		return sponsorshipLevel;
	}

	public void setSponsorshipLevel(String sponsorshipLevel) {
		this.sponsorshipLevel = sponsorshipLevel;
	}

}

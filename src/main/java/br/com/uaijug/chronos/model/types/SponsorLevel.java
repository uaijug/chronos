package br.com.uaijug.chronos.model.types;

public enum SponsorLevel {

	/** The YES. */
	DIAMOND("DIAMOND"),

	TITANIUM("TITANIUM"),
	
	PLATINUM("PLATINUM"),
	
	GOLD("GOLD"),
	
	SILVER("SILVER"),

	BRONZE("BRONZE");

	/** The status. */
	String sponsorLevel;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private SponsorLevel(String sponsorLevel) {
		this.sponsorLevel = sponsorLevel;
	}

	public String getSponsorLevel() {
		return sponsorLevel;
	}

	public void setSponsorLevel(String sponsorLevel) {
		this.sponsorLevel = sponsorLevel;
	}

}

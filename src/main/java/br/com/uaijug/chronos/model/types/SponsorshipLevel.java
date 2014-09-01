/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum SponsorshipLevel.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum SponsorshipLevel {

	/** The YES. */
	DIAMOND("DIAMOND"),

	/** The titanium. */
	TITANIUM("TITANIUM"),
	
	/** The platinum. */
	PLATINUM("PLATINUM"),
	
	/** The gold. */
	GOLD("GOLD"),
	
	/** The silver. */
	SILVER("SILVER"),

	/** The bronze. */
	BRONZE("BRONZE");

	/** The status. */
	String sponsorshipLevel;

	/**
	 * Instantiates a new status.
	 *
	 * @param sponsorshipLevel the sponsorship level
	 */
	private SponsorshipLevel(String sponsorshipLevel) {
		this.sponsorshipLevel = sponsorshipLevel;
	}

	/**
	 * Gets the sponsorship level.
	 *
	 * @return the sponsorship level
	 */
	public String getSponsorshipLevel() {
		return sponsorshipLevel;
	}

	/**
	 * Sets the sponsorship level.
	 *
	 * @param sponsorshipLevel the new sponsorship level
	 */
	public void setSponsorshipLevel(String sponsorshipLevel) {
		this.sponsorshipLevel = sponsorshipLevel;
	}

}

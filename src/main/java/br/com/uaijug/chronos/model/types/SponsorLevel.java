/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum SponsorLevel.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum SponsorLevel {

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
	String sponsorLevel;

	/**
	 * Instantiates a new status.
	 *
	 * @param sponsorLevel the sponsor level
	 */
	private SponsorLevel(String sponsorLevel) {
		this.sponsorLevel = sponsorLevel;
	}

	/**
	 * Gets the sponsor level.
	 *
	 * @return the sponsor level
	 */
	public String getSponsorLevel() {
		return sponsorLevel;
	}

	/**
	 * Sets the sponsor level.
	 *
	 * @param sponsorLevel the new sponsor level
	 */
	public void setSponsorLevel(String sponsorLevel) {
		this.sponsorLevel = sponsorLevel;
	}

}

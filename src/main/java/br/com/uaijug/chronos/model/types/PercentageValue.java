/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum PercentageValue.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum PercentageValue {

	/** The percent_10. */
	percent_10("10% desconto"),
	
	/** The percent_20. */
	percent_20("20% desconto"),
	
	/** The percent_25. */
	percent_25("25% desconto"),
	
	/** The percent_50. */
	percent_50("50% desconto"),
	
	/** The percent_100. */
	percent_100("100% desconto");

	/** The status. */
	String percentageValue;

	/**
	 * Instantiates a new status.
	 *
	 * @param percentageValue the percentage value
	 */
	private PercentageValue(String percentageValue) {
		this.percentageValue = percentageValue;
	}

	/**
	 * Gets the percentage value.
	 *
	 * @return the percentage value
	 */
	public String getPercentageValue() {
		return percentageValue;
	}

	/**
	 * Sets the percentage value.
	 *
	 * @param percentageValue the new percentage value
	 */
	public void setPercentageValue(String percentageValue) {
		this.percentageValue = percentageValue;
	}

}

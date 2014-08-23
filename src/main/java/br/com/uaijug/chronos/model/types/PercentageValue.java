package br.com.uaijug.chronos.model.types;

public enum PercentageValue {

	percent_10("10% desconto"),
	
	percent_20("20% desconto"),
	
	percent_25("25% desconto"),
	
	percent_50("50% desconto"),
	
	percent_100("100% desconto");

	/** The status. */
	String percentageValue;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private PercentageValue(String percentageValue) {
		this.percentageValue = percentageValue;
	}

	public String getPercentageValue() {
		return percentageValue;
	}

	public void setPercentageValue(String percentageValue) {
		this.percentageValue = percentageValue;
	}

}

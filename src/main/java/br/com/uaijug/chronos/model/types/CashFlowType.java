package br.com.uaijug.chronos.model.types;

public enum CashFlowType {

	/** The YES. */
	revenue("Receita"),
	/** The NO. */
	expense("Despesa");

	/** The status. */
	String cashFlowType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private CashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
	}

	public String getCashFlowType() {
		return cashFlowType;
	}

	public void setCashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
	}

}

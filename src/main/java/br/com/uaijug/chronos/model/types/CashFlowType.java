/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum CashFlowType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
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
	 * @param cashFlowType the cash flow type
	 */
	private CashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
	}

	/**
	 * Gets the cash flow type.
	 *
	 * @return the cash flow type
	 */
	public String getCashFlowType() {
		return cashFlowType;
	}

	/**
	 * Sets the cash flow type.
	 *
	 * @param cashFlowType the new cash flow type
	 */
	public void setCashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
	}

}

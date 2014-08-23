package br.com.uaijug.chronos.model.types;

public enum SupplierType {

	/** The YES. */
	FIXED_SUPPLIER("Fornecedor Fixo"),

	RANDOM_SUPPLIER("Fornecedor Aleatorio");

	/** The status. */
	String supplierType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private SupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

}

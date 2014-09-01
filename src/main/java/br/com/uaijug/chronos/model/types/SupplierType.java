/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum SupplierType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum SupplierType {

	/** The YES. */
	FIXED_SUPPLIER("Fornecedor Fixo"),

	/** The random supplier. */
	RANDOM_SUPPLIER("Fornecedor Aleatorio");

	/** The status. */
	String supplierType;

	/**
	 * Instantiates a new status.
	 *
	 * @param supplierType the supplier type
	 */
	private SupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	/**
	 * Gets the supplier type.
	 *
	 * @return the supplier type
	 */
	public String getSupplierType() {
		return supplierType;
	}

	/**
	 * Sets the supplier type.
	 *
	 * @param supplierType the new supplier type
	 */
	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

}

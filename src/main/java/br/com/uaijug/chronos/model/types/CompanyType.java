/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum CompanyType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum CompanyType {

	/** The YES. */
	EMPRESAPALESTRANTE("Empresa do Palestrante"),
	/** The NO. */
	EMPRESAPARCEIRA("Empresa Parceira"),

	/** The empresapatrocinadora. */
	EMPRESAPATROCINADORA("Empresa Patrocinadora");

	/** The status. */
	String companyType;

	/**
	 * Instantiates a new status.
	 *
	 * @param companyType the company type
	 */
	private CompanyType(String companyType) {
		this.companyType = companyType;
	}

	/**
	 * Gets the company type.
	 *
	 * @return the company type
	 */
	public String getCompanyType() {
		return companyType;
	}

	/**
	 * Sets the company type.
	 *
	 * @param companyType the new company type
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

}

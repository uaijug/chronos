package br.com.uaijug.chronos.model.types;

public enum CompanyType {

	/** The YES. */
	EMPRESAPALESTRANTE("Empresa do Palestrante"),
	/** The NO. */
	EMPRESAPARCEIRA("Empresa Parceira"),

	EMPRESAPATROCINADORA("Empresa Patrocinadora");

	/** The status. */
	String companyType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private CompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

}

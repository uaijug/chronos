package br.com.uaijug.chronos.model.types;

public enum ResourceType {

	/** The YES. */
	HUMAN_RESOURCES("Rercursos humanos"),
	/** The NO. */
	MATERIAL_RESOURCES("Recursos Materiais"),

	FINANCIAL_RESOURCES("Recursos Financeiros");

	/** The status. */
	String companyType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private ResourceType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

}

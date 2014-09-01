/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum ResourceType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum ResourceType {

	/** The YES. */
	HUMAN_RESOURCES("Rercursos humanos"),
	/** The NO. */
	MATERIAL_RESOURCES("Recursos Materiais"),

	/** The financial resources. */
	FINANCIAL_RESOURCES("Recursos Financeiros");

	/** The status. */
	String companyType;

	/**
	 * Instantiates a new status.
	 *
	 * @param companyType the company type
	 */
	private ResourceType(String companyType) {
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

/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum InstitutionType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum InstitutionType {

	/** The ONG. */
	ONG("Ong"),
	
	/** The NO. */
	OSCIP("Oscip"),

	/** The CNPJ. */
	CNPJ("Cnpj");

	/** The status. */
	String institutionType;

	/**
	 * Instantiates a new status.
	 *
	 * @param institutionType the institution type
	 */
	private InstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	/**
	 * Gets the institution type.
	 *
	 * @return the institution type
	 */
	public String getInstitutionType() {
		return institutionType;
	}

	/**
	 * Sets the institution type.
	 *
	 * @param institutionType the new institution type
	 */
	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

}

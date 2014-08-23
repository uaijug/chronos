package br.com.uaijug.chronos.model.types;

public enum InstitutionType {

	/** The YES. */
	EMPRESAPALESTRANTE("Empresa do Palestrante"),
	/** The NO. */
	EMPRESAPARCEIRA("Empresa Parceira"),

	EMPRESAPATROCINADORA("Empresa Patrocinadora"),

	EMPRESAEXPOSITORA("Empresa Expositora");

	/** The status. */
	String institutionType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private InstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	public String getInstitutionType() {
		return institutionType;
	}

	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

}

package br.com.uaijug.chronos.model.types;

public enum TipoCadastro {

	/** The YES. */
	ESTUDANTE("Estudante"),
	
	PATROCINADOR("Patrocinador"),
	/** The NO. */
	PROFISSIONAL("PROFISSIONAL"),
	
	PALESTRANTE("PALESTRANTE");

	/** The status. */
	String approve;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private TipoCadastro(String approve) {
		this.approve = approve;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

}

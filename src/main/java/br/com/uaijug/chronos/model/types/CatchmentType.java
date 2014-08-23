package br.com.uaijug.chronos.model.types;

public enum CatchmentType {

	/** The YES. */
	own_source("Fonte propria"),
	
	/** The NO. */
	external_source_sponsorship("Fonte extrena (patrocionio"),
	external_source_support("Fonte extrena (apoio"),
	external_source_ticket_sale("Fonte extrena (venda de ingresso"),
	external_source_actions_in_the_media("Fonte extrena (acoes na midia"),
	external_source_selling_spaces("Fonte extrena (venda de espacos"),
	external_source_donations("Fonte extrena (doacoes"),
	external_source_partner("Fonte extrena (parceiro");
	
	/** The status. */
	String catchmentType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private CatchmentType(String catchmentType) {
		this.catchmentType = catchmentType;
	}

	public String getCatchmentType() {
		return catchmentType;
	}

	public void setCatchmentType(String catchmentType) {
		this.catchmentType = catchmentType;
	}

}

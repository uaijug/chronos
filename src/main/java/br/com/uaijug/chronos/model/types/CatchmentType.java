/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum CatchmentType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum CatchmentType {

	/** The YES. */
	own_source("Fonte propria"),
	
	/** The NO. */
	external_source_sponsorship("Fonte extrena (patrocionio"),
	
	/** The external_source_support. */
	external_source_support("Fonte extrena (apoio"),
	
	/** The external_source_ticket_sale. */
	external_source_ticket_sale("Fonte extrena (venda de ingresso"),
	
	/** The external_source_actions_in_the_media. */
	external_source_actions_in_the_media("Fonte extrena (acoes na midia"),
	
	/** The external_source_selling_spaces. */
	external_source_selling_spaces("Fonte extrena (venda de espacos"),
	
	/** The external_source_donations. */
	external_source_donations("Fonte extrena (doacoes"),
	
	/** The external_source_partner. */
	external_source_partner("Fonte extrena (parceiro");
	
	/** The status. */
	String catchmentType;

	/**
	 * Instantiates a new status.
	 *
	 * @param catchmentType the catchment type
	 */
	private CatchmentType(String catchmentType) {
		this.catchmentType = catchmentType;
	}

	/**
	 * Gets the catchment type.
	 *
	 * @return the catchment type
	 */
	public String getCatchmentType() {
		return catchmentType;
	}

	/**
	 * Sets the catchment type.
	 *
	 * @param catchmentType the new catchment type
	 */
	public void setCatchmentType(String catchmentType) {
		this.catchmentType = catchmentType;
	}

}

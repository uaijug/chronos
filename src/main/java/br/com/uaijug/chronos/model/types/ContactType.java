/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum ContactType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum ContactType {

	/** The pessonal. */
	PESSONAL("Pessoal"),
	
	/** The phone. */
	PHONE("Telefone"),
	
	/** The email. */
	EMAIL("email");

	/** The status. */
	String contactType;

	/**
	 * Instantiates a new status.
	 *
	 * @param contactType the contact type
	 */
	private ContactType(String contactType) {
		this.contactType = contactType;
	}

	/**
	 * Gets the contact type.
	 *
	 * @return the contact type
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 * Sets the contact type.
	 *
	 * @param contactType the new contact type
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

}

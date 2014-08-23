package br.com.uaijug.chronos.model.types;

public enum ContactType {

	PESSONAL("Pessoal"),
	
	PHONE("Telefone"),
	
	EMAIL("email");

	/** The status. */
	String contactType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private ContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

}

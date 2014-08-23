package br.com.uaijug.chronos.model.types;

public enum PostType {

	DRAFT("Rascunho"),
	
	PENDING_REVIEW("Em Revisao"),

	PRIVATE_PASSWORD("Privado com senha"),
	
	PRIVATE("Privado"),
	
	PUBLIC("Publico");

	/** The status. */
	String postType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private PostType(String postType) {
		this.postType = postType;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

}

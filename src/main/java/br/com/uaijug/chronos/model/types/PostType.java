/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum PostType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum PostType {

	/** The draft. */
	DRAFT("Rascunho"),
	
	/** The pending review. */
	PENDING_REVIEW("Em Revisao"),

	/** The private password. */
	PRIVATE_PASSWORD("Privado com senha"),
	
	/** The private. */
	PRIVATE("Privado"),
	
	/** The public. */
	PUBLIC("Publico");

	/** The status. */
	String postType;

	/**
	 * Instantiates a new status.
	 *
	 * @param postType the post type
	 */
	private PostType(String postType) {
		this.postType = postType;
	}

	/**
	 * Gets the post type.
	 *
	 * @return the post type
	 */
	public String getPostType() {
		return postType;
	}

	/**
	 * Sets the post type.
	 *
	 * @param postType the new post type
	 */
	public void setPostType(String postType) {
		this.postType = postType;
	}

}

/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum PostScheduleType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum PostScheduleType {

	/** The immediately. */
	IMMEDIATELY("Imediatamento"),
	
	/** The schedule. */
	SCHEDULE("Agendamento");
	
	/** The status. */
	String postScheduleType;

	/**
	 * Instantiates a new status.
	 *
	 * @param postScheduleType the post schedule type
	 */
	private PostScheduleType(String postScheduleType) {
		this.postScheduleType = postScheduleType;
	}

	/**
	 * Gets the post schedule type.
	 *
	 * @return the post schedule type
	 */
	public String getPostScheduleType() {
		return postScheduleType;
	}

	/**
	 * Sets the post schedule type.
	 *
	 * @param postScheduleType the new post schedule type
	 */
	public void setPostScheduleType(String postScheduleType) {
		this.postScheduleType = postScheduleType;
	}

}

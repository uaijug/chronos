package br.com.uaijug.chronos.model.types;

public enum PostScheduleType {

	IMMEDIATELY("Imediatamento"),
	
	SCHEDULE("Agendamento");
	
	/** The status. */
	String postScheduleType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private PostScheduleType(String postScheduleType) {
		this.postScheduleType = postScheduleType;
	}

	public String getPostScheduleType() {
		return postScheduleType;
	}

	public void setPostScheduleType(String postScheduleType) {
		this.postScheduleType = postScheduleType;
	}

}

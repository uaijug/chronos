package br.com.uaijug.chronos.model.types;

public enum EventType {

	/** The YES. */
	WORKSHOP("Workshop"),

	OPEN_TALKS("Open talks"),

	MINI_COURSES("Mini cursos"),

	COURSES("Cursos"),

	FULL_EVENT("Evento Mesclado");

	/** The status. */
	String eventType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private EventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}

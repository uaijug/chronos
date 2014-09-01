/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum EventType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum EventType {

	/** The YES. */
	WORKSHOP("Workshop"),

	/** The open talks. */
	OPEN_TALKS("Open talks"),

	/** The mini courses. */
	MINI_COURSES("Mini cursos"),

	/** The courses. */
	COURSES("Cursos"),

	/** The full event. */
	FULL_EVENT("Evento Mesclado");

	/** The status. */
	String eventType;

	/**
	 * Instantiates a new status.
	 *
	 * @param eventType the event type
	 */
	private EventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * Gets the event type.
	 *
	 * @return the event type
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * Sets the event type.
	 *
	 * @param eventType the new event type
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}

/*
 * 
 */
package br.com.uaijug.chronos.model.types;

// TODO: Auto-generated Javadoc
/**
 * The Enum ScheduleType.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public enum ScheduleType {

	/** The YES. */
	INTERVALO("Intervalo"),
	/** The NO. */
	ALMOCO("Almoco"),

	/** The credenciamento. */
	CREDENCIAMENTO("Credenciamento"),

	/** The keynote. */
	KEYNOTE("KeyNote"),

	/** The espacoreservado. */
	ESPACORESERVADO("KeyNote"),

	/** The palestra. */
	PALESTRA("Palestra"),

	/** The minicurso. */
	MINICURSO("Minicurso"),

	/** The coffebreak. */
	COFFEBREAK("Coffe Break");

	/** The status. */
	String scheduleType;

	/**
	 * Instantiates a new status.
	 *
	 * @param scheduleType the schedule type
	 */
	private ScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	/**
	 * Gets the schedule type.
	 *
	 * @return the schedule type
	 */
	public String getScheduleType() {
		return scheduleType;
	}

	/**
	 * Sets the schedule type.
	 *
	 * @param scheduleType the new schedule type
	 */
	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

}

package br.com.uaijug.chronos.model.types;

public enum ScheduleType {

	/** The YES. */
	INTERVALO("Intervalo"),
	/** The NO. */
	ALMOCO("Almoco"),

	CREDENCIAMENTO("Credenciamento"),

	KEYNOTE("KeyNote"),

	ESPACORESERVADO("KeyNote"),

	PALESTRA("Palestra"),

	MINICURSO("Minicurso"),

	COFFEBREAK("Coffe Break");

	/** The status. */
	String scheduleType;

	/**
	 * Instantiates a new status.
	 * 
	 * @param status
	 *            the status
	 */
	private ScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

}

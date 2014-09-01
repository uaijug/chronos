/*
 * 
 */
package br.com.uaijug.chronos.event.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uj_schedule database table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@Table(name = "uj_event_schedule", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class EventSchedule extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The date. */
	@Column(name = "date_realization")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date date;

	/** The end time. */
	@Column(name = "end_time")
	private String endTime;

	/** The short description. */
	@Column(name = "short_description")
	private String shortDescription;

	/** The start time. */
	@Column(name = "start_time")
	private String startTime;

	/** The title. */
	private String title;

	/** The subtitle. */
	private String subtitle;

	// bi-directional many-to-one association to Event
	/** The event. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	/** The schedule type. */
	private String scheduleType;

	/**
	 * Instantiates a new event schedule.
	 */
	public EventSchedule() {
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public String getEndTime() {
		return this.endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the short description.
	 *
	 * @return the short description
	 */
	public String getShortDescription() {
		return this.shortDescription;
	}

	/**
	 * Sets the short description.
	 *
	 * @param shortDescription the new short description
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public EventMain getEvent() {
		return this.event;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(EventMain event) {
		this.event = event;
	}

	/**
	 * Gets the subtitle.
	 *
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * Sets the subtitle.
	 *
	 * @param subtitle the new subtitle
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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
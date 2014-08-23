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

/**
 * The persistent class for the uj_schedule database table.
 * 
 */
@Entity
@Table(name = "uj_event_schedule", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class EventSchedule extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "date_realization")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date date;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "short_description")
	private String shortDescription;

	@Column(name = "start_time")
	private String startTime;

	private String title;

	private String subtitle;

	// bi-directional many-to-one association to Event
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	private String scheduleType;

	public EventSchedule() {
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public EventMain getEvent() {
		return this.event;
	}

	public void setEvent(EventMain event) {
		this.event = event;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

}
package br.com.uaijug.chronos.schedule.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.model.AbstractEntity;

/**
 * The persistent class for the uj_studyGroup database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectSchedules", query = "SELECT e FROM Schedule e")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_schedule", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@Cacheable(true)
public class Schedule extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Institution
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	private String scheduleType;

	private String title;

	private String description;

	@Column(name = "end_time")
	private String endTime;

	private String shortDescription;

	@Column(name = "start_time")
	private String startTime;

	private String value;

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date date;

	// bi-directional many-to-one association to Registration
	/*
	 * @OneToMany(mappedBy = "studyGroup") private List<Registration>
	 * registrations;
	 */// criar registro para o grupo de estudos

	public Schedule() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public EventMain getEvent() {
		return event;
	}

	public void setEvent(EventMain event) {
		this.event = event;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
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

import br.com.uaijug.chronos.model.AbstractEntity;
import br.com.uaijug.chronos.project.model.Project;

/**
 * The persistent class for the uj_studyGroup database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectActivitys", query = "SELECT e FROM Activity e")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_activity", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@Cacheable(true)
public class Activity extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Institution
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	// bi-directional many-to-one association to Institution
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activityCategory_id")
	private ActivityCategory activityCategory;

	private String completed;

	private String etc;

	private String approved;

	private String status;

	private String tags;

	private String notes;

	private String description;

	@Column(name = "start_date")
	private String startDate;

	@Column(name = "end_date")
	private String endDate;

	private String value;

	@Temporal(TemporalType.DATE)
	@NotNull
	private Date date;

	// bi-directional many-to-one association to Registration
	/*
	 * @OneToMany(mappedBy = "studyGroup") private List<Registration>
	 * registrations;
	 */// criar registro para o grupo de estudos

	public Activity() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}

	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
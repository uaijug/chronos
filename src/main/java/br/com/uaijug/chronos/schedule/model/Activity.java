/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uj_studyGroup database table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectActivitys", query = "SELECT e FROM Activity e")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_activity", uniqueConstraints = @UniqueConstraint(columnNames = "description"))

public class Activity extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Institution
	/** The project. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	// bi-directional many-to-one association to Institution
	/** The activity category. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activityCategory_id")
	private ActivityCategory activityCategory;

	/** The completed. */
	private String completed;

	/** The etc. */
	private String etc;

	/** The approved. */
	private String approved;

	/** The status. */
	private String status;

	/** The tags. */
	private String tags;

	/** The notes. */
	private String notes;

	/** The description. */
	private String description;

	/** The start date. */
	@Column(name = "start_date")
	private String startDate;

	/** The end date. */
	@Column(name = "end_date")
	private String endDate;

	/** The value. */
	private String value;

	/** The date. */
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date date;

	// bi-directional many-to-one association to Registration
	/*
	 * @OneToMany(mappedBy = "studyGroup") private List<Registration>
	 * registrations;
	 */// criar registro para o grupo de estudos

	/**
	 * Instantiates a new activity.
	 */
	public Activity() {
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
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
	 * Gets the project.
	 *
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * Sets the project.
	 *
	 * @param project the new project
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * Gets the activity category.
	 *
	 * @return the activity category
	 */
	public ActivityCategory getActivityCategory() {
		return activityCategory;
	}

	/**
	 * Sets the activity category.
	 *
	 * @param activityCategory the new activity category
	 */
	public void setActivityCategory(ActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}

	/**
	 * Gets the completed.
	 *
	 * @return the completed
	 */
	public String getCompleted() {
		return completed;
	}

	/**
	 * Sets the completed.
	 *
	 * @param completed the new completed
	 */
	public void setCompleted(String completed) {
		this.completed = completed;
	}

	/**
	 * Gets the etc.
	 *
	 * @return the etc
	 */
	public String getEtc() {
		return etc;
	}

	/**
	 * Sets the etc.
	 *
	 * @param etc the new etc
	 */
	public void setEtc(String etc) {
		this.etc = etc;
	}

	/**
	 * Gets the approved.
	 *
	 * @return the approved
	 */
	public String getApproved() {
		return approved;
	}

	/**
	 * Sets the approved.
	 *
	 * @param approved the new approved
	 */
	public void setApproved(String approved) {
		this.approved = approved;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * Sets the tags.
	 *
	 * @param tags the new tags
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
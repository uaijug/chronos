/*
 * 
 */
package br.com.uaijug.chronos.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the atividades database table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@Table(name = "uj_daily_activity", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class DailyActivity extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The completed. */
	private Integer completed;

	/** The end date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;

	/** The start date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	/** The description. */
	private String description;

	/** The estimated time. */
	@Column(name = "estimated_time")
	private Integer estimatedTime;

	/** The etc. */
	private Integer etc;

	/** The approved. */
	private String approved;

	/** The status. */
	private String status; // [TODO] -> enum Status

	/** The tags. */
	private String tags;

	/** The notes. */
	@Lob()
	private String notes;

	// bi-directional many-to-one association to projeto
	/** The project. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	/** The activity category. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "activity_category_id")
	private DailyActivityCategory activityCategory;

	/**
	 * Instantiates a new daily activity.
	 */
	public DailyActivity() {
	}

	/**
	 * Gets the completed.
	 *
	 * @return the completed
	 */
	public Integer getCompleted() {
		return completed;
	}

	/**
	 * Sets the completed.
	 *
	 * @param completed the new completed
	 */
	public void setCompleted(Integer completed) {
		this.completed = completed;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
	 * Gets the estimated time.
	 *
	 * @return the estimated time
	 */
	public Integer getEstimatedTime() {
		return estimatedTime;
	}

	/**
	 * Sets the estimated time.
	 *
	 * @param estimatedTime the new estimated time
	 */
	public void setEstimatedTime(Integer estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	/**
	 * Gets the etc.
	 *
	 * @return the etc
	 */
	public Integer getEtc() {
		return etc;
	}

	/**
	 * Sets the etc.
	 *
	 * @param etc the new etc
	 */
	public void setEtc(Integer etc) {
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
	public DailyActivityCategory getActivityCategory() {
		return activityCategory;
	}

	/**
	 * Sets the activity category.
	 *
	 * @param activityCategory the new activity category
	 */
	public void setActivityCategory(DailyActivityCategory activityCategory) {
		this.activityCategory = activityCategory;
	}

}
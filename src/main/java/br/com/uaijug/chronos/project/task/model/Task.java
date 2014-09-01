/*
 * 
 */
package br.com.uaijug.chronos.project.task.model;

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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.model.AbstractEntity;
import br.com.uaijug.chronos.project.model.Project;

// TODO: Auto-generated Javadoc
/**
 * The Class Task.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@Table(name = "uj_task", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class Task extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5834094055676588566L;

	/** The title. */
	private String title;

	/** The description. */
	@NotNull
	private String description;

	/** The procedure. */
	@Column(name = "internal_process")
	private String procedure;

	/** The expected result. */
	@Column(name = "expected_result")
	private String expectedResult;

	/** The notify. */
	private String notify;

	/** The status. */
	private String status;

	/** The priority. */
	private String priority;

	/** The end date. */
	@Fetch(FetchMode.JOIN)
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	@Future
	private Date endDate;

	/** The start date. */
	@Fetch(FetchMode.JOIN)
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;

	// bi-directional many-to-one association to projeto
	/** The project. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	private Project project;

	/** The task category. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_category_id")
	private TaskCategory taskCategory;

	/** The member. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	/**
	 * Instantiates a new task.
	 */
	public Task() {
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
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
	 * Gets the procedure.
	 *
	 * @return the procedure
	 */
	public String getProcedure() {
		return procedure;
	}

	/**
	 * Sets the procedure.
	 *
	 * @param procedure the new procedure
	 */
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}

	/**
	 * Gets the expected result.
	 *
	 * @return the expected result
	 */
	public String getExpectedResult() {
		return expectedResult;
	}

	/**
	 * Sets the expected result.
	 *
	 * @param expectedResult the new expected result
	 */
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	/**
	 * Gets the notify.
	 *
	 * @return the notify
	 */
	public String getNotify() {
		return notify;
	}

	/**
	 * Sets the notify.
	 *
	 * @param notify the new notify
	 */
	public void setNotify(String notify) {
		this.notify = notify;
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
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
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
	 * Gets the task category.
	 *
	 * @return the task category
	 */
	public TaskCategory getTaskCategory() {
		return taskCategory;
	}

	/**
	 * Sets the task category.
	 *
	 * @param taskCategory the new task category
	 */
	public void setTaskCategory(TaskCategory taskCategory) {
		this.taskCategory = taskCategory;
	}

	/**
	 * Gets the member.
	 *
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * Sets the member.
	 *
	 * @param member the new member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((expectedResult == null) ? 0 : expectedResult.hashCode());
		result = prime * result + ((notify == null) ? 0 : notify.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result
				+ ((procedure == null) ? 0 : procedure.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((taskCategory == null) ? 0 : taskCategory.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (expectedResult == null) {
			if (other.expectedResult != null)
				return false;
		} else if (!expectedResult.equals(other.expectedResult))
			return false;
		if (notify == null) {
			if (other.notify != null)
				return false;
		} else if (!notify.equals(other.notify))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (procedure == null) {
			if (other.procedure != null)
				return false;
		} else if (!procedure.equals(other.procedure))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (taskCategory == null) {
			if (other.taskCategory != null)
				return false;
		} else if (!taskCategory.equals(other.taskCategory))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskTestCase [title=" + title + ", description=" + description
				+ ", procedure=" + procedure + ", expectedResult="
				+ expectedResult + ", notify=" + notify + ", status=" + status
				+ ", priority=" + priority + ", endDate=" + endDate
				+ ", startDate=" + startDate + ", project=" + project
				+ ", taskCategory=" + taskCategory + "]";
	}

}

/*
 * 
 */
package br.com.uaijug.chronos.project.task.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskGroup.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@Table(name = "uj_task_group", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class TaskGroup extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8354700495230700145L;

	/** The name. */
	private String name;

	/** The task group category. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_group_category_id")
	private TaskGroupCategory taskGroupCategory;

	/** The duration. */
	private int duration;

	/** The current days. */
	@Column(name = "current_days")
	private int currentDays;

	/** The coordinator. */
	private String coordinator;

	/**
	 * Instantiates a new task group.
	 */
	public TaskGroup() {

	}

	/**
	 * Instantiates a new task group.
	 *
	 * @param id the id
	 * @param name the name
	 * @param description the description
	 * @param taskGroupCategory the task group category
	 * @param duration the duration
	 * @param currentDays the current days
	 * @param coordinator the coordinator
	 */
	public TaskGroup(Long id, String name, String description,
			TaskGroupCategory taskGroupCategory, int duration, int currentDays,
			String coordinator) {
		this.name = name;
		this.taskGroupCategory = taskGroupCategory;
		this.duration = duration;
		this.currentDays = currentDays;
		this.coordinator = coordinator;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the task group category.
	 *
	 * @return the task group category
	 */
	public TaskGroupCategory getTaskGroupCategory() {
		return taskGroupCategory;
	}

	/**
	 * Sets the task group category.
	 *
	 * @param taskGroupCategory the new task group category
	 */
	public void setTaskGroupCategory(TaskGroupCategory taskGroupCategory) {
		this.taskGroupCategory = taskGroupCategory;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Gets the current days.
	 *
	 * @return the current days
	 */
	public int getCurrentDays() {
		return currentDays;
	}

	/**
	 * Sets the current days.
	 *
	 * @param currentDays the new current days
	 */
	public void setCurrentDays(int currentDays) {
		this.currentDays = currentDays;
	}

	/**
	 * Gets the coordinator.
	 *
	 * @return the coordinator
	 */
	public String getCoordinator() {
		return coordinator;
	}

	/**
	 * Sets the coordinator.
	 *
	 * @param coordinator the new coordinator
	 */
	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinator == null) ? 0 : coordinator.hashCode());
		result = prime * result + currentDays;
		result = prime * result + duration;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((taskGroupCategory == null) ? 0 : taskGroupCategory
						.hashCode());
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
		TaskGroup other = (TaskGroup) obj;
		if (coordinator == null) {
			if (other.coordinator != null)
				return false;
		} else if (!coordinator.equals(other.coordinator))
			return false;
		if (currentDays != other.currentDays)
			return false;
		if (duration != other.duration)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (taskGroupCategory == null) {
			if (other.taskGroupCategory != null)
				return false;
		} else if (!taskGroupCategory.equals(other.taskGroupCategory))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskGroup [name=" + name + ", taskGroupCategory="
				+ taskGroupCategory + ", duration=" + duration
				+ ", currentDays=" + currentDays + ", coordinator="
				+ coordinator + "]";
	}

}

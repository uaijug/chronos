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

@Entity
@Table(name = "uj_task_group", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class TaskGroup extends AbstractEntity<Long> implements Serializable {

	private static final long serialVersionUID = -8354700495230700145L;

	private String name;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_group_category_id")
	private TaskGroupCategory taskGroupCategory;

	private int duration;

	@Column(name = "current_days")
	private int currentDays;

	private String coordinator;

	public TaskGroup() {

	}

	public TaskGroup(Long id, String name, String description,
			TaskGroupCategory taskGroupCategory, int duration, int currentDays,
			String coordinator) {
		this.name = name;
		this.taskGroupCategory = taskGroupCategory;
		this.duration = duration;
		this.currentDays = currentDays;
		this.coordinator = coordinator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TaskGroupCategory getTaskGroupCategory() {
		return taskGroupCategory;
	}

	public void setTaskGroupCategory(TaskGroupCategory taskGroupCategory) {
		this.taskGroupCategory = taskGroupCategory;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getCurrentDays() {
		return currentDays;
	}

	public void setCurrentDays(int currentDays) {
		this.currentDays = currentDays;
	}

	public String getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}

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

	@Override
	public String toString() {
		return "TaskGroup [name=" + name + ", taskGroupCategory="
				+ taskGroupCategory + ", duration=" + duration
				+ ", currentDays=" + currentDays + ", coordinator="
				+ coordinator + "]";
	}

}

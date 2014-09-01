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

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uj_eventProposal database table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectEventProposals", query = "SELECT e FROM EventProposal e")
@XmlRootElement
@Table(name = "uj_event_proposal", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class EventProposal extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The description. */
	private String description;

	/** The create date. */
	@Column(name = "create_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date createDate;

	/** The schedule. */
	private String schedule;

	/** The macro themes. */
	@Column(name = "macro_themes")
	private String macroThemes;

	/** The target audience. */
	@Column(name = "target_audience")
	private String targetAudience;

	/** The expenses. */
	private String expenses;

	/** The budget. */
	private String budget;

	/** The forms of dissemination. */
	@Column(name = "forms_of_dissemination")
	private String formsOfDissemination;

	// bi-directional many-to-one association to Institution
	/** The event. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	/**
	 * Instantiates a new event proposal.
	 */
	public EventProposal() {
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
	 * Gets the creates the date.
	 *
	 * @return the creates the date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * Sets the creates the date.
	 *
	 * @param createDate the new creates the date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * Gets the schedule.
	 *
	 * @return the schedule
	 */
	public String getSchedule() {
		return schedule;
	}

	/**
	 * Sets the schedule.
	 *
	 * @param schedule the new schedule
	 */
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	/**
	 * Gets the macro themes.
	 *
	 * @return the macro themes
	 */
	public String getMacroThemes() {
		return macroThemes;
	}

	/**
	 * Sets the macro themes.
	 *
	 * @param macroThemes the new macro themes
	 */
	public void setMacroThemes(String macroThemes) {
		this.macroThemes = macroThemes;
	}

	/**
	 * Gets the target audience.
	 *
	 * @return the target audience
	 */
	public String getTargetAudience() {
		return targetAudience;
	}

	/**
	 * Sets the target audience.
	 *
	 * @param targetAudience the new target audience
	 */
	public void setTargetAudience(String targetAudience) {
		this.targetAudience = targetAudience;
	}

	/**
	 * Gets the expenses.
	 *
	 * @return the expenses
	 */
	public String getExpenses() {
		return expenses;
	}

	/**
	 * Sets the expenses.
	 *
	 * @param expenses the new expenses
	 */
	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}

	/**
	 * Gets the budget.
	 *
	 * @return the budget
	 */
	public String getBudget() {
		return budget;
	}

	/**
	 * Sets the budget.
	 *
	 * @param budget the new budget
	 */
	public void setBudget(String budget) {
		this.budget = budget;
	}

	/**
	 * Gets the forms of dissemination.
	 *
	 * @return the forms of dissemination
	 */
	public String getFormsOfDissemination() {
		return formsOfDissemination;
	}

	/**
	 * Sets the forms of dissemination.
	 *
	 * @param formsOfDissemination the new forms of dissemination
	 */
	public void setFormsOfDissemination(String formsOfDissemination) {
		this.formsOfDissemination = formsOfDissemination;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public EventMain getEvent() {
		return event;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(EventMain event) {
		this.event = event;
	}

}
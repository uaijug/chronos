/*
 * 
 */
package br.com.uaijug.chronos.studygroup.model;

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

import br.com.uaijug.chronos.institution.model.Institution;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uj_studyGroup database table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectStudyGroups", query = "SELECT e FROM StudyGroup e")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_study_group", uniqueConstraints = @UniqueConstraint(columnNames = "name"))

public class StudyGroup extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The description. */
	private String description;

	/** The end time. */
	@Column(name = "end_time")
	private String endTime;

	/** The location. */
	private String location;

	/** The map. */
	private String map;

	/** The name. */
	private String name;

	/** The start time. */
	@Column(name = "start_time")
	private String startTime;

	/** The value. */
	private String value;

	/** The date realization. */
	@Column(name = "date_realization")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateRealization;

	// bi-directional many-to-one association to Institution
	/** The institution. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id")
	private Institution institution;

	// bi-directional many-to-one association to Registration
	/*
	 * @OneToMany(mappedBy = "studyGroup") private List<Registration>
	 * registrations;
	 */// criar registro para o grupo de estudos

	/**
	 * Instantiates a new study group.
	 */
	public StudyGroup() {
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
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return this.location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public String getMap() {
		return this.map;
	}

	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public void setMap(String map) {
		this.map = map;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.value;
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
	 * Gets the institution.
	 *
	 * @return the institution
	 */
	public Institution getInstitution() {
		return this.institution;
	}

	/**
	 * Sets the institution.
	 *
	 * @param institution the new institution
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	/*
	 * public List<Registration> getRegistrations() { return this.registrations;
	 * }
	 * 
	 * public void setRegistrations(List<Registration> registrations) {
	 * this.registrations = registrations; }
	 */

	/**
	 * Gets the date realization.
	 *
	 * @return the date realization
	 */
	public Date getDateRealization() {
		return dateRealization;
	}

	/**
	 * Sets the date realization.
	 *
	 * @param dateRealization the new date realization
	 */
	public void setDateRealization(Date dateRealization) {
		this.dateRealization = dateRealization;
	}

}
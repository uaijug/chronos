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

/**
 * The persistent class for the uj_studyGroup database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectStudyGroups", query = "SELECT e FROM StudyGroup e")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_study_group", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Cacheable(true)
public class StudyGroup extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Column(name = "end_time")
	private String endTime;

	private String location;

	private String map;

	private String name;

	@Column(name = "start_time")
	private String startTime;

	private String value;

	@Column(name = "date_realization")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateRealization;

	// bi-directional many-to-one association to Institution
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id")
	private Institution institution;

	// bi-directional many-to-one association to Registration
	/*
	 * @OneToMany(mappedBy = "studyGroup") private List<Registration>
	 * registrations;
	 */// criar registro para o grupo de estudos

	public StudyGroup() {
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

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMap() {
		return this.map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Institution getInstitution() {
		return this.institution;
	}

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

	public Date getDateRealization() {
		return dateRealization;
	}

	public void setDateRealization(Date dateRealization) {
		this.dateRealization = dateRealization;
	}

}
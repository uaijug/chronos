package br.com.uaijug.chronos.event.speaker.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.uaijug.chronos.admin.model.Address;
import br.com.uaijug.chronos.admin.model.Company;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.model.AbstractEntity;

/**
 * The persistent class for the uj_speaker database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectSpeakers", query = "SELECT s FROM Speaker s")
@XmlRootElement
@Table(name = "uj_speaker", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Speaker extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	@Email
	private String email;

	@Lob()
	@Column(name = "mini_curriculum")
	private String miniCurriculum;

	@Lob()
	@Column(name = "more_informations")
	private String moreInformations;

	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String name;

	@NotNull
	@Size(min = 10, max = 12)
	@Digits(fraction = 0, integer = 12)
	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "pic")
	private String picture;

	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date birthDate;

	@Lob()
	private String presentation;

	private String site;

	private String twitter;

	// bi-directional many-to-one association to UjCompany
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	// bi-directional many-to-one association to UjInstitution
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	// bi-directional many-to-one association to Address
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	public Speaker() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMiniCurriculum() {
		return this.miniCurriculum;
	}

	public void setMiniCurriculum(String miniCurriculum) {
		this.miniCurriculum = miniCurriculum;
	}

	public String getMoreInformations() {
		return this.moreInformations;
	}

	public void setMoreInformations(String moreInformations) {
		this.moreInformations = moreInformations;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPresentation() {
		return this.presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTwitter() {
		return this.twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public EventMain getEvent() {
		return this.event;
	}

	public void setEvent(EventMain event) {
		this.event = event;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uj_speaker database table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectSpeakers", query = "SELECT s FROM Speaker s")
@XmlRootElement
@Table(name = "uj_speaker", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class Speaker extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The email. */
	@NotNull
	@NotEmpty
	@Email
	private String email;

	/** The mini curriculum. */
	@Lob()
	@Column(name = "mini_curriculum")
	private String miniCurriculum;

	/** The more informations. */
	@Lob()
	@Column(name = "more_informations")
	private String moreInformations;

	/** The name. */
	@NotNull
	@Size(min = 1, max = 25)
	@Pattern(regexp = "[A-Za-z ]*", message = "must contain only letters and spaces")
	private String name;

	/** The phone number. */
	@NotNull
	@Size(min = 10, max = 12)
	@Digits(fraction = 0, integer = 12)
	@Column(name = "phone_number")
	private String phoneNumber;

	/** The picture. */
	@Column(name = "pic")
	private String picture;

	/** The birth date. */
	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date birthDate;

	/** The presentation. */
	@Lob()
	private String presentation;

	/** The site. */
	private String site;

	/** The twitter. */
	private String twitter;

	// bi-directional many-to-one association to UjCompany
	/** The company. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	// bi-directional many-to-one association to UjInstitution
	/** The event. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	// bi-directional many-to-one association to Address
	/** The address. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	/**
	 * Instantiates a new speaker.
	 */
	public Speaker() {
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the mini curriculum.
	 *
	 * @return the mini curriculum
	 */
	public String getMiniCurriculum() {
		return this.miniCurriculum;
	}

	/**
	 * Sets the mini curriculum.
	 *
	 * @param miniCurriculum the new mini curriculum
	 */
	public void setMiniCurriculum(String miniCurriculum) {
		this.miniCurriculum = miniCurriculum;
	}

	/**
	 * Gets the more informations.
	 *
	 * @return the more informations
	 */
	public String getMoreInformations() {
		return this.moreInformations;
	}

	/**
	 * Sets the more informations.
	 *
	 * @param moreInformations the new more informations
	 */
	public void setMoreInformations(String moreInformations) {
		this.moreInformations = moreInformations;
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
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the picture.
	 *
	 * @return the picture
	 */
	public String getPicture() {
		return this.picture;
	}

	/**
	 * Sets the picture.
	 *
	 * @param picture the new picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * Gets the presentation.
	 *
	 * @return the presentation
	 */
	public String getPresentation() {
		return this.presentation;
	}

	/**
	 * Sets the presentation.
	 *
	 * @param presentation the new presentation
	 */
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	/**
	 * Gets the site.
	 *
	 * @return the site
	 */
	public String getSite() {
		return this.site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site the new site
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Gets the twitter.
	 *
	 * @return the twitter
	 */
	public String getTwitter() {
		return this.twitter;
	}

	/**
	 * Sets the twitter.
	 *
	 * @param twitter the new twitter
	 */
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return this.company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public EventMain getEvent() {
		return this.event;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(EventMain event) {
		this.event = event;
	}

	/**
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
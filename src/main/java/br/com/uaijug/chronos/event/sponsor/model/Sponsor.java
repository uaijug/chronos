/*
 * 
 */
package br.com.uaijug.chronos.event.sponsor.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.admin.model.Address;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class Sponsor.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectSponsors", query = "SELECT p FROM Sponsor p")
@XmlRootElement
@Table(name = "uj_sponsor", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class Sponsor extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The name. */
	private String name;

	/** The cnpj. */
	private String cnpj;

	/** The acronym. */
	private String acronym;

	/** The description. */
	private String description;

	/** The site. */
	private String site;

	/** The email. */
	private String email;

	/** The logo. */
	private String logo;

	/** The opportunity. */
	private String opportunity; // open or closed

	/** The level. */
	private String level;

	/** The responsible. */
	private String responsible;

	/** The responsible phone. */
	@Column(name = "reposible_phone")
	private String responsiblePhone;

	/** The address. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

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
	 * Gets the acronym.
	 *
	 * @return the acronym
	 */
	public String getAcronym() {
		return acronym;
	}

	/**
	 * Sets the acronym.
	 *
	 * @param acronym the new acronym
	 */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
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
	 * Gets the site.
	 *
	 * @return the site
	 */
	public String getSite() {
		return site;
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
	 * Gets the logo.
	 *
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * Sets the logo.
	 *
	 * @param logo the new logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * Gets the cnpj.
	 *
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Sets the cnpj.
	 *
	 * @param cnpj the new cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
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

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
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
	 * Gets the opportunity.
	 *
	 * @return the opportunity
	 */
	public String getOpportunity() {
		return opportunity;
	}

	/**
	 * Sets the opportunity.
	 *
	 * @param opportunity the new opportunity
	 */
	public void setOpportunity(String opportunity) {
		this.opportunity = opportunity;
	}

	/**
	 * Gets the responsible.
	 *
	 * @return the responsible
	 */
	public String getResponsible() {
		return responsible;
	}

	/**
	 * Sets the responsible.
	 *
	 * @param responsible the new responsible
	 */
	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	/**
	 * Gets the responsible phone.
	 *
	 * @return the responsible phone
	 */
	public String getResponsiblePhone() {
		return responsiblePhone;
	}

	/**
	 * Sets the responsible phone.
	 *
	 * @param responsiblePhone the new responsible phone
	 */
	public void setResponsiblePhone(String responsiblePhone) {
		this.responsiblePhone = responsiblePhone;
	}

}

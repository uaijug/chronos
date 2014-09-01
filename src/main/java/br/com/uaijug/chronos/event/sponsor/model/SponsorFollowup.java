/*
 * 
 */
package br.com.uaijug.chronos.event.sponsor.model;

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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.event.model.EventProposal;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class SponsorFollowup.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 */
@Entity
@NamedQuery(name = "SelectSponsorFollowups", query = "SELECT p FROM SponsorFollowup p")
@XmlRootElement
@Table(name = "uj_sponsor_followup", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class SponsorFollowup extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The description. */
	private String description;

	/** The data. */
	private Date data;

	/** The contact type. */
	@Column(name = "contact_type")
	private String contactType;

	/** The attachement. */
	private String attachement;

	/** The event proposal. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_proposal_id")
	private EventProposal eventProposal;

	/** The sponsor. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sponsor_id")
	private Sponsor sponsor;

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
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Gets the attachement.
	 *
	 * @return the attachement
	 */
	public String getAttachement() {
		return attachement;
	}

	/**
	 * Sets the attachement.
	 *
	 * @param attachement the new attachement
	 */
	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

	/**
	 * Gets the sponsor.
	 *
	 * @return the sponsor
	 */
	public Sponsor getSponsor() {
		return sponsor;
	}

	/**
	 * Sets the sponsor.
	 *
	 * @param sponsor the new sponsor
	 */
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	/**
	 * Gets the contact type.
	 *
	 * @return the contact type
	 */
	public String getContactType() {
		return contactType;
	}

	/**
	 * Sets the contact type.
	 *
	 * @param contactType the new contact type
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

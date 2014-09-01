/*
 * 
 */
package br.com.uaijug.chronos.event.sponsor.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class Sponsorship.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br
 */
@Entity
@NamedQuery(name = "SelectSponsorships", query = "SELECT p FROM Sponsorship p")
@XmlRootElement
@Table(name = "uj_sponsorship", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class Sponsorship extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The description. */
	private String description;

	/** The data. */
	private Date data;

	/** The amount. */
	private BigDecimal amount;

	/** The sponsor. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sponsor_id")
	private Sponsor sponsor;

	/** The event. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	/** The level. */
	private String level;

	/** The paiment type. */
	@Column(name = "payment_type")
	private String paimentType;

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

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	 * Gets the paiment type.
	 *
	 * @return the paiment type
	 */
	public String getPaimentType() {
		return paimentType;
	}

	/**
	 * Sets the paiment type.
	 *
	 * @param paimentType the new paiment type
	 */
	public void setPaimentType(String paimentType) {
		this.paimentType = paimentType;
	}

}

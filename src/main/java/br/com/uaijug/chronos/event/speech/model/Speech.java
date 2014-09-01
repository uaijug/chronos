/*
 * 
 */
package br.com.uaijug.chronos.event.speech.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.speaker.model.Speaker;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uj_lecture database table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectSpeechs", query = "SELECT l FROM Speech l")
@XmlRootElement
@Table(name = "uj_speech", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class Speech extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The approve. */
	private String approve;

	/** The description. */
	@Lob()
	private String description;

	/** The nivel. */
	private String nivel;

	/** The pre requisite. */
	@Column(name = "pre_requisite")
	private String preRequisite;

	/** The short description. */
	@Column(name = "short_description")
	private String shortDescription;

	/** The title. */
	private String title;

	// bi-directional many-to-one association to Speaker
	/** The speaker. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speaker_id")
	private Speaker speaker;

	// bi-directional many-to-one association to Event
	/** The event. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	// bi-directional many-to-one association to UjSpeechType
	/** The speech types. */
	@ManyToOne
	@JoinColumn(name = "type_speech_type_id")
	private SpeechType speechTypes;

	/**
	 * Instantiates a new speech.
	 */
	public Speech() {
	}

	/**
	 * Gets the approve.
	 *
	 * @return the approve
	 */
	public String getApprove() {
		return this.approve;
	}

	/**
	 * Sets the approve.
	 *
	 * @param approve the new approve
	 */
	public void setApprove(String approve) {
		this.approve = approve;
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
	 * Gets the nivel.
	 *
	 * @return the nivel
	 */
	public String getNivel() {
		return this.nivel;
	}

	/**
	 * Sets the nivel.
	 *
	 * @param nivel the new nivel
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/**
	 * Gets the pre requisite.
	 *
	 * @return the pre requisite
	 */
	public String getPreRequisite() {
		return this.preRequisite;
	}

	/**
	 * Sets the pre requisite.
	 *
	 * @param preRequisite the new pre requisite
	 */
	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}

	/**
	 * Gets the short description.
	 *
	 * @return the short description
	 */
	public String getShortDescription() {
		return this.shortDescription;
	}

	/**
	 * Sets the short description.
	 *
	 * @param shortDescription the new short description
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the speaker.
	 *
	 * @return the speaker
	 */
	public Speaker getSpeaker() {
		return speaker;
	}

	/**
	 * Sets the speaker.
	 *
	 * @param speaker the new speaker
	 */
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
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
	 * Gets the speech types.
	 *
	 * @return the speech types
	 */
	public SpeechType getSpeechTypes() {
		return speechTypes;
	}

	/**
	 * Sets the speech types.
	 *
	 * @param speechTypes the new speech types
	 */
	public void setSpeechTypes(SpeechType speechTypes) {
		this.speechTypes = speechTypes;
	}

}
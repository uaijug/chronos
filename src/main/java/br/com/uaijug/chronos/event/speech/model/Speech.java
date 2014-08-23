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

/**
 * The persistent class for the uj_lecture database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectSpeechs", query = "SELECT l FROM Speech l")
@XmlRootElement
@Table(name = "uj_speech", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Speech extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String approve;

	@Lob()
	private String description;

	private String nivel;

	@Column(name = "pre_requisite")
	private String preRequisite;

	@Column(name = "short_description")
	private String shortDescription;

	private String title;

	// bi-directional many-to-one association to Speaker
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speaker_id")
	private Speaker speaker;

	// bi-directional many-to-one association to Event
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	// bi-directional many-to-one association to UjSpeechType
	@ManyToOne
	@JoinColumn(name = "type_speech_type_id")
	private SpeechType speechTypes;

	public Speech() {
	}

	public String getApprove() {
		return this.approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getPreRequisite() {
		return this.preRequisite;
	}

	public void setPreRequisite(String preRequisite) {
		this.preRequisite = preRequisite;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public EventMain getEvent() {
		return event;
	}

	public void setEvent(EventMain event) {
		this.event = event;
	}

	public SpeechType getSpeechTypes() {
		return speechTypes;
	}

	public void setSpeechTypes(SpeechType speechTypes) {
		this.speechTypes = speechTypes;
	}

}
package br.com.uaijug.chronos.event.speech.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.model.AbstractEntity;

/**
 * The persistent class for the uj_speech_type database table.
 * 
 */
@Entity
@Table(name = "uj_speech_type", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class SpeechType extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	private int time;

	public SpeechType() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTime() {
		return this.time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
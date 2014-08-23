package br.com.uaijug.chronos.institution.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.model.AbstractEntity;

/**
 * The persistent class for the uj_contact database table.
 * 
 */
@Entity
@Table(name = "uj_notify", uniqueConstraints = @UniqueConstraint(columnNames = "subject"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Notify extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String subject;
	
	private String message;
	
	public Notify() {

	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
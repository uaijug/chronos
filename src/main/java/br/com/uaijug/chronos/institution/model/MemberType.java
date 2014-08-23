package br.com.uaijug.chronos.institution.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.model.AbstractEntity;

/**
 * The persistent class for the uj_member_type database table.
 * 
 */
@Entity
@Table(name = "uj_member_type", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class MemberType extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	public MemberType() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
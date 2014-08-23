package br.com.uaijug.chronos.admin.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.model.AbstractEntity;

@Entity
@NamedQuery(name = "SelectCountrys", query = "SELECT c FROM Country c")
@XmlRootElement
@Table(name = "uj_admin_country", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Country extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 8048954965440525782L;

	private String description;

	public Country() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
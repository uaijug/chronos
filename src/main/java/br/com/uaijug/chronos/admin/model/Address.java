package br.com.uaijug.chronos.admin.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
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

import br.com.uaijug.chronos.model.AbstractEntity;

/**
 * The persistent class for the uj_address database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectAddresses", query = "SELECT a FROM Address a")
@XmlRootElement
@Table(name = "uj_admin_address", uniqueConstraints = @UniqueConstraint(columnNames = "street"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Address extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String street;

	// bi-directional many-to-one association to City
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	public Address() {
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
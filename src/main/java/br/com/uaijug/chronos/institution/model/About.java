package br.com.uaijug.chronos.institution.model;

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
 * The persistent class for the uj_about database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectAbouts", query = "SELECT a FROM About a")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_about", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@Cacheable(true)
public class About extends AbstractEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6667497535531008867L;

	private String title;

	private String description;

	// bi-directional many-to-one association to UjInstitution
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id")
	private Institution institution;

	private String approve;

	public About() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

}
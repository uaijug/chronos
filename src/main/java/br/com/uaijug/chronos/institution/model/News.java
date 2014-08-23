package br.com.uaijug.chronos.institution.model;

import java.io.Serializable;
import java.util.Date;

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

import br.com.uaijug.chronos.admin.model.User;
import br.com.uaijug.chronos.model.AbstractEntity;

/**
 * The persistent class for the uj_news database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectNewss", query = "SELECT a FROM News a")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_news", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@Cacheable(true)
public class News extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;

	private String description;

	private Date date;

	// bi-directional many-to-one association to UjInstitution
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public News() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
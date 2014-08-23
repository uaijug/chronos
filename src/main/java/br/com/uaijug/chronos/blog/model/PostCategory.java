package br.com.uaijug.chronos.blog.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.model.AbstractEntity;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
@Table(name = "uj_blog_post_category", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class PostCategory extends AbstractEntity<Long> implements Serializable {

	@NotNull
	@Size(min = 1, max = 60)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String name;

	@Size(min = 1, max = 40)
	private String slug; // user friendly url

	@NotNull
	@Size(min = 1, max = 120)
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((slug == null) ? 0 : slug.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostCategory other = (PostCategory) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (slug == null) {
			if (other.slug != null)
				return false;
		} else if (!slug.equals(other.slug))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PostCategory [name=" + name + ", slug=" + slug
				+ ", description=" + description + "]";
	}

}
/*
 * 
 */
package br.com.uaijug.chronos.blog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class Post.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@XmlRootElement
@Table(name = "uj_blog_post", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Post extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -9025536294124051705L;

	/** The post category. */
	@ManyToOne
	@JoinColumn(name = "post_category_id")
	private PostCategory postCategory;

	/** The title. */
	@NotNull
	@Size(min = 1, max = 60)
	private String title;

	/** The content. */
	@NotNull
	@Size(max = 1000)
	private String content;

	/** The member. */
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	/** The date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date date;

	/** The post type. */
	private String postType;

	/** The tag. */
	private String tag;

	/** The publish. */
	private String publish;

	/** The publish schedule date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_schedule_date")
	private Date publishScheduleDate;

	/**
	 * Gets the post category.
	 *
	 * @return the post category
	 */
	public PostCategory getPostCategory() {
		return postCategory;
	}

	/**
	 * Sets the post category.
	 *
	 * @param postCategory the new post category
	 */
	public void setPostCategory(PostCategory postCategory) {
		this.postCategory = postCategory;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
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
	 * Gets the content.
	 *
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Sets the content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Gets the member.
	 *
	 * @return the member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * Sets the member.
	 *
	 * @param member the new member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag the new tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Gets the post type.
	 *
	 * @return the post type
	 */
	public String getPostType() {
		return postType;
	}

	/**
	 * Sets the post type.
	 *
	 * @param postType the new post type
	 */
	public void setPostType(String postType) {
		this.postType = postType;
	}

	/**
	 * Gets the publish.
	 *
	 * @return the publish
	 */
	public String getPublish() {
		return publish;
	}

	/**
	 * Sets the publish.
	 *
	 * @param publish the new publish
	 */
	public void setPublish(String publish) {
		this.publish = publish;
	}

	/**
	 * Gets the publish schedule date.
	 *
	 * @return the publish schedule date
	 */
	public Date getPublishScheduleDate() {
		return publishScheduleDate;
	}

	/**
	 * Sets the publish schedule date.
	 *
	 * @param publishScheduleDate the new publish schedule date
	 */
	public void setPublishScheduleDate(Date publishScheduleDate) {
		this.publishScheduleDate = publishScheduleDate;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
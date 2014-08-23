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

@Entity
@XmlRootElement
@Table(name = "uj_blog_post", uniqueConstraints = @UniqueConstraint(columnNames = "title"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Post extends AbstractEntity<Long> implements Serializable {

	private static final long serialVersionUID = -9025536294124051705L;

	@ManyToOne
	@JoinColumn(name = "post_category_id")
	private PostCategory postCategory;

	@NotNull
	@Size(min = 1, max = 60)
	private String title;

	@NotNull
	@Size(max = 1000)
	private String content;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date date;

	private String postType;

	private String tag;

	private String publish;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_schedule_date")
	private Date publishScheduleDate;

	public PostCategory getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(PostCategory postCategory) {
		this.postCategory = postCategory;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public Date getPublishScheduleDate() {
		return publishScheduleDate;
	}

	public void setPublishScheduleDate(Date publishScheduleDate) {
		this.publishScheduleDate = publishScheduleDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
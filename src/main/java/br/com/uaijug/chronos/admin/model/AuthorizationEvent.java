package br.com.uaijug.chronos.admin.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.model.AbstractEntity;

/**
 * The persistent class for the _authorization_events database table.
 * 
 */
@Entity
@Table(name = "uj_admin_authorization_events", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class AuthorizationEvent extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String password;

	private String username;

	// bi-directional many-to-one association to Profile
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_profile_id")
	private Profile profile;

	// bi-directional many-to-one association to Member
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	public AuthorizationEvent() {
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Profile getProfile() {
		return this.profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
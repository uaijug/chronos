/*
 * 
 */
package br.com.uaijug.chronos.admin.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uj_authorization database table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@Table(name = "uj_admin_authorization", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Authorization extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The password. */
	@NotNull
	@NotEmpty
	private String password;

	/** The username. */
	@NotNull
	@NotEmpty
	private String username;

	// bi-directional many-to-one association to UjProfile
	/** The profile. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profile_id")
	private Profile profile;

	// bi-directional many-to-one association to UjMember
	/** The member. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	/**
	 * Instantiates a new authorization.
	 */
	public Authorization() {
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the profile.
	 *
	 * @return the profile
	 */
	public Profile getProfile() {
		return this.profile;
	}

	/**
	 * Sets the profile.
	 *
	 * @param profile the new profile
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * Gets the member.
	 *
	 * @return the member
	 */
	public Member getMember() {
		return this.member;
	}

	/**
	 * Sets the member.
	 *
	 * @param member the new member
	 */
	public void setMember(Member member) {
		this.member = member;
	}

}
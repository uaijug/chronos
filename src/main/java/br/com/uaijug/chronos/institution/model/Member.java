/*
 * 
 */
package br.com.uaijug.chronos.institution.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import br.com.uaijug.chronos.admin.model.Address;
import br.com.uaijug.chronos.model.AbstractEntity;
import br.com.uaijug.chronos.model.types.Gender;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the uj_member database table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectMembers", query = "SELECT m FROM Member m")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_member", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Member extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The born date. */
	private String bornDate;

	/** The cpf. */
	private String cpf;

	/** The rg. */
	private String rg;

	/** The age. */
	private String age; // [TODO] Colocar na view

	/** The gender. */
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Gender gender;

	/** The email. */
	private String email;

	/** The nationality. */
	private String nationality;

	/** The company. */
	private String company;

	/** The comercial phone. */
	private String comercialPhone;

	/** The expertise. */
	private String expertise;

	/** The facebook. */
	private String facebook;

	/** The name. */
	private String name;

	/** The last name. */
	private String lastName;

	/** The phone number. */
	@Column(name = "phone_number")
	private String phoneNumber;

	/** The site. */
	private String site;

	/** The twitter. */
	private String twitter;

	// bi-directional many-to-one association to Institution
	/** The institution. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id")
	private Institution institution;

	// bi-directional many-to-one association to Member
	/** The member type. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_type_id")
	private MemberType memberType;

	// bi-directional many-to-one association to Address
	/** The address. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	/** The address number. */
	private Integer addressNumber;

	/**
	 * Instantiates a new member.
	 */
	public Member() {
	}

	/**
	 * Gets the institution.
	 *
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}

	/**
	 * Sets the institution.
	 *
	 * @param institution the new institution
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	/**
	 * Gets the born date.
	 *
	 * @return the born date
	 */
	public String getBornDate() {
		return this.bornDate;
	}

	/**
	 * Sets the born date.
	 *
	 * @param bornDate the new born date
	 */
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

	/**
	 * Gets the cpf.
	 *
	 * @return the cpf
	 */
	public String getCpf() {
		return this.cpf;
	}

	/**
	 * Sets the cpf.
	 *
	 * @param cpf the new cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public String getCompany() {
		return this.company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Gets the expertise.
	 *
	 * @return the expertise
	 */
	public String getExpertise() {
		return this.expertise;
	}

	/**
	 * Sets the expertise.
	 *
	 * @param expertise the new expertise
	 */
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	/**
	 * Gets the facebook.
	 *
	 * @return the facebook
	 */
	public String getFacebook() {
		return this.facebook;
	}

	/**
	 * Sets the facebook.
	 *
	 * @param facebook the new facebook
	 */
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the site.
	 *
	 * @return the site
	 */
	public String getSite() {
		return this.site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site the new site
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Gets the twitter.
	 *
	 * @return the twitter
	 */
	public String getTwitter() {
		return this.twitter;
	}

	/**
	 * Sets the twitter.
	 *
	 * @param twitter the new twitter
	 */
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	/**
	 * Gets the member type.
	 *
	 * @return the member type
	 */
	public MemberType getMemberType() {
		return memberType;
	}

	/**
	 * Sets the member type.
	 *
	 * @param memberType the new member type
	 */
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets the age.
	 *
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * Sets the age.
	 *
	 * @param age the new age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Gets the comercial phone.
	 *
	 * @return the comercial phone
	 */
	public String getComercialPhone() {
		return comercialPhone;
	}

	/**
	 * Sets the comercial phone.
	 *
	 * @param comercialPhone the new comercial phone
	 */
	public void setComercialPhone(String comercialPhone) {
		this.comercialPhone = comercialPhone;
	}

	/**
	 * Gets the rg.
	 *
	 * @return the rg
	 */
	public String getRg() {
		return rg;
	}

	/**
	 * Sets the rg.
	 *
	 * @param rg the new rg
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}

	/**
	 * Gets the nationality.
	 *
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Sets the nationality.
	 *
	 * @param nationality the new nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Gets the address number.
	 *
	 * @return the address number
	 */
	public Integer getAddressNumber() {
		return addressNumber;
	}

	/**
	 * Sets the address number.
	 *
	 * @param addressNumber the new address number
	 */
	public void setAddressNumber(Integer addressNumber) {
		this.addressNumber = addressNumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Member [bornDate=" + bornDate + ", cpf=" + cpf + ", age=" + age
				+ ", email=" + email
				+ ", company=" + company + ", expertise=" + expertise
				+ ", facebook=" + facebook + ", name=" + name
				+ ", phoneNumber=" + phoneNumber + ", site=" + site
				+ ", twitter=" + twitter + ", address=" + address + "]";
	}

}
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

/**
 * The persistent class for the uj_member database table.
 * 
 */
@Entity
@NamedQuery(name = "SelectMembers", query = "SELECT m FROM Member m")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_member", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Cacheable(true)
public class Member extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String bornDate;

	private String cpf;

	private String rg;

	private String age; // [TODO] Colocar na view

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private Gender gender;

	private String email;

	private String nationality;

	private String company;

	private String comercialPhone;

	private String expertise;

	private String facebook;

	private String name;

	private String lastName;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String site;

	private String twitter;

	// bi-directional many-to-one association to Institution
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id")
	private Institution institution;

	// bi-directional many-to-one association to Member
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_type_id")
	private MemberType memberType;

	// bi-directional many-to-one association to Address
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	private Integer addressNumber;

	public Member() {
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public String getBornDate() {
		return this.bornDate;
	}

	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getExpertise() {
		return this.expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getFacebook() {
		return this.facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTwitter() {
		return this.twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getComercialPhone() {
		return comercialPhone;
	}

	public void setComercialPhone(String comercialPhone) {
		this.comercialPhone = comercialPhone;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(Integer addressNumber) {
		this.addressNumber = addressNumber;
	}

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
package br.com.uaijug.chronos.event.registration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.model.AbstractEntity;

@Entity
@NamedQuery(name = "MemberParticipations", query = "SELECT p FROM MemberParticipation p")
@XmlRootElement
@Table(name = "uj_member_participation", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
@Cacheable(true)
public class MemberParticipation extends AbstractEntity<Long> implements
		Serializable {

	private static final long serialVersionUID = 7333669827940410493L;

	@Column(name = "date_realization")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateRealization;

	private String code;

	@Column(name = "promotional_code")
	private String promotionalCode;

	// bi-directional many-to-one association to UjEvent
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_event")
	private EventMain event;

	// bi-directional many-to-one association to UjMember
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_member")
	private Member member;

	@Column(name = "payment_status")
	private String paymentStatus;

	@Column(name = "amount_paid")
	private int amountPaid;

	private int descont;

	private int moneyChange; // troco

	private String paimentType;

	public Date getDateRealization() {
		return dateRealization;
	}

	public void setDateRealization(Date dateRealization) {
		this.dateRealization = dateRealization;
	}

	public String getPromotionalCode() {
		return promotionalCode;
	}

	public void setPromotionalCode(String promotionalCode) {
		this.promotionalCode = promotionalCode;
	}

	public EventMain getEvent() {
		return event;
	}

	public void setEvent(EventMain event) {
		this.event = event;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getDescont() {
		return descont;
	}

	public void setDescont(int descont) {
		this.descont = descont;
	}

	public int getMoneyChange() {
		return moneyChange;
	}

	public void setMoneyChange(int moneyChange) {
		this.moneyChange = moneyChange;
	}

	public String getPaimentType() {
		return paimentType;
	}

	public void setPaimentType(String paimentType) {
		this.paimentType = paimentType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}

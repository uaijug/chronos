/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class MemberParticipation.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "MemberParticipations", query = "SELECT p FROM MemberParticipation p")
@XmlRootElement
@Table(name = "uj_member_participation", uniqueConstraints = @UniqueConstraint(columnNames = "code"))

public class MemberParticipation extends AbstractEntity<Long> implements
		Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7333669827940410493L;

	/** The date realization. */
	@Column(name = "date_realization")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date dateRealization;

	/** The code. */
	private String code;

	/** The promotional code. */
	@Column(name = "promotional_code")
	private String promotionalCode;

	// bi-directional many-to-one association to UjEvent
	/** The event. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_event")
	private EventMain event;

	// bi-directional many-to-one association to UjMember
	/** The member. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_member")
	private Member member;

	/** The payment status. */
	@Column(name = "payment_status")
	private String paymentStatus;

	/** The amount paid. */
	@Column(name = "amount_paid")
	private int amountPaid;

	/** The descont. */
	private int descont;

	/** The money change. */
	private int moneyChange; // troco

	/** The paiment type. */
	private String paimentType;

	/**
	 * Gets the date realization.
	 *
	 * @return the date realization
	 */
	public Date getDateRealization() {
		return dateRealization;
	}

	/**
	 * Sets the date realization.
	 *
	 * @param dateRealization the new date realization
	 */
	public void setDateRealization(Date dateRealization) {
		this.dateRealization = dateRealization;
	}

	/**
	 * Gets the promotional code.
	 *
	 * @return the promotional code
	 */
	public String getPromotionalCode() {
		return promotionalCode;
	}

	/**
	 * Sets the promotional code.
	 *
	 * @param promotionalCode the new promotional code
	 */
	public void setPromotionalCode(String promotionalCode) {
		this.promotionalCode = promotionalCode;
	}

	/**
	 * Gets the event.
	 *
	 * @return the event
	 */
	public EventMain getEvent() {
		return event;
	}

	/**
	 * Sets the event.
	 *
	 * @param event the new event
	 */
	public void setEvent(EventMain event) {
		this.event = event;
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
	 * Gets the payment status.
	 *
	 * @return the payment status
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * Sets the payment status.
	 *
	 * @param paymentStatus the new payment status
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * Gets the amount paid.
	 *
	 * @return the amount paid
	 */
	public int getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Sets the amount paid.
	 *
	 * @param amountPaid the new amount paid
	 */
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * Gets the descont.
	 *
	 * @return the descont
	 */
	public int getDescont() {
		return descont;
	}

	/**
	 * Sets the descont.
	 *
	 * @param descont the new descont
	 */
	public void setDescont(int descont) {
		this.descont = descont;
	}

	/**
	 * Gets the money change.
	 *
	 * @return the money change
	 */
	public int getMoneyChange() {
		return moneyChange;
	}

	/**
	 * Sets the money change.
	 *
	 * @param moneyChange the new money change
	 */
	public void setMoneyChange(int moneyChange) {
		this.moneyChange = moneyChange;
	}

	/**
	 * Gets the paiment type.
	 *
	 * @return the paiment type
	 */
	public String getPaimentType() {
		return paimentType;
	}

	/**
	 * Sets the paiment type.
	 *
	 * @param paimentType the new paiment type
	 */
	public void setPaimentType(String paimentType) {
		this.paimentType = paimentType;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}

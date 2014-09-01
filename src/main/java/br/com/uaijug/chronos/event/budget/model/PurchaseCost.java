/*
 * 
 */
package br.com.uaijug.chronos.event.budget.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.event.supplier.model.Supplier;
import br.com.uaijug.chronos.institution.model.Member;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class PurchaseCost.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@Table(name = "uj_cashflow_purchase_cost", uniqueConstraints = @UniqueConstraint(columnNames = "quantity"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class PurchaseCost extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4081651607402441579L;

	/** The event. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	/** The supplier. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	/** The material. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	private Material material;

	/** The budget. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_id")
	private Budget budget;

	/** The member. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	/** The unit value. */
	@Column(name = "unit_value")
	private Double unitValue;

	/** The quantity. */
	private String quantity;

	/** The total value. */
	@Column(name = "total_value")
	private Double totalValue;

	/** The payment date. */
	@Column(name = "payment_date")
	private Date paymentDate;

	/** The note. */
	private String note;

	/** The head note. */
	@Column(name = "activity_id")
	private String headNote;

	/** The nro note. */
	private String nroNote;

	/** The attachement. */
	private String attachement;

	/**
	 * Gets the supplier.
	 *
	 * @return the supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * Sets the supplier.
	 *
	 * @param supplier the new supplier
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * Gets the unit value.
	 *
	 * @return the unit value
	 */
	public Double getUnitValue() {
		return unitValue;
	}

	/**
	 * Sets the unit value.
	 *
	 * @param unitValue the new unit value
	 */
	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the total value.
	 *
	 * @return the total value
	 */
	public Double getTotalValue() {
		return totalValue;
	}

	/**
	 * Sets the total value.
	 *
	 * @param totalValue the new total value
	 */
	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	/**
	 * Gets the payment date.
	 *
	 * @return the payment date
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * Sets the payment date.
	 *
	 * @param paymentDate the new payment date
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note the new note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Gets the head note.
	 *
	 * @return the head note
	 */
	public String getHeadNote() {
		return headNote;
	}

	/**
	 * Sets the head note.
	 *
	 * @param headNote the new head note
	 */
	public void setHeadNote(String headNote) {
		this.headNote = headNote;
	}

	/**
	 * Gets the nro note.
	 *
	 * @return the nro note
	 */
	public String getNroNote() {
		return nroNote;
	}

	/**
	 * Sets the nro note.
	 *
	 * @param nroNote the new nro note
	 */
	public void setNroNote(String nroNote) {
		this.nroNote = nroNote;
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
	 * Gets the material.
	 *
	 * @return the material
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Sets the material.
	 *
	 * @param material the new material
	 */
	public void setMaterial(Material material) {
		this.material = material;
	}

	/**
	 * Gets the budget.
	 *
	 * @return the budget
	 */
	public Budget getBudget() {
		return budget;
	}

	/**
	 * Sets the budget.
	 *
	 * @param budget the new budget
	 */
	public void setBudget(Budget budget) {
		this.budget = budget;
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
	 * Gets the attachement.
	 *
	 * @return the attachement
	 */
	public String getAttachement() {
		return attachement;
	}

	/**
	 * Sets the attachement.
	 *
	 * @param attachement the new attachement
	 */
	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

}

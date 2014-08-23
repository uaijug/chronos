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

@Entity
@Table(name = "uj_cashflow_purchase_cost", uniqueConstraints = @UniqueConstraint(columnNames = "quantity"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class PurchaseCost extends AbstractEntity<Long> implements Serializable {

	private static final long serialVersionUID = 4081651607402441579L;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	private Material material;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budget_id")
	private Budget budget;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(name = "unit_value")
	private Double unitValue;

	private String quantity;

	@Column(name = "total_value")
	private Double totalValue;

	@Column(name = "payment_date")
	private Date paymentDate;

	private String note;

	@Column(name = "activity_id")
	private String headNote;

	private String nroNote;

	private String attachement;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Double getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getHeadNote() {
		return headNote;
	}

	public void setHeadNote(String headNote) {
		this.headNote = headNote;
	}

	public String getNroNote() {
		return nroNote;
	}

	public void setNroNote(String nroNote) {
		this.nroNote = nroNote;
	}

	public EventMain getEvent() {
		return event;
	}

	public void setEvent(EventMain event) {
		this.event = event;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

}

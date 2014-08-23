package br.com.uaijug.chronos.event.budget.model;

import java.io.Serializable;

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
import br.com.uaijug.chronos.model.AbstractEntity;

@Entity
@Table(name = "uj_cashflow_budget", uniqueConstraints = @UniqueConstraint(columnNames = "quantity"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Budget extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 6207404142253555906L;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	private Material material;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	private String quantity;

	@Column(name = "date_delivery")
	private String dateDelivery;

	private String invoice;

	@Column(name = "unit_value")
	private Double unitValue;

	@Column(name = "percentage_discount")
	private int percentageDiscount;

	@Column(name = "total_value")
	private Double totalValue;

	@Column(name = "capture_feature")
	private String captureFeature;

	private String observation;

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(String dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public Double getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;
	}

	public int getPercentageDiscount() {
		return percentageDiscount;
	}

	public void setPercentageDiscount(int percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public String getCaptureFeature() {
		return captureFeature;
	}

	public void setCaptureFeature(String captureFeature) {
		this.captureFeature = captureFeature;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}

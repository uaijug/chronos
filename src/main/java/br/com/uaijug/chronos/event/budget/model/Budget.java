/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class Budget.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@Table(name = "uj_cashflow_budget", uniqueConstraints = @UniqueConstraint(columnNames = "quantity"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class Budget extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6207404142253555906L;

	/** The event. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	/** The material. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	private Material material;

	/** The supplier. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	/** The quantity. */
	private String quantity;

	/** The date delivery. */
	@Column(name = "date_delivery")
	private String dateDelivery;

	/** The invoice. */
	private String invoice;

	/** The unit value. */
	@Column(name = "unit_value")
	private Double unitValue;

	/** The percentage discount. */
	@Column(name = "percentage_discount")
	private int percentageDiscount;

	/** The total value. */
	@Column(name = "total_value")
	private Double totalValue;

	/** The capture feature. */
	@Column(name = "capture_feature")
	private String captureFeature;

	/** The observation. */
	private String observation;

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
	 * Gets the date delivery.
	 *
	 * @return the date delivery
	 */
	public String getDateDelivery() {
		return dateDelivery;
	}

	/**
	 * Sets the date delivery.
	 *
	 * @param dateDelivery the new date delivery
	 */
	public void setDateDelivery(String dateDelivery) {
		this.dateDelivery = dateDelivery;
	}

	/**
	 * Gets the invoice.
	 *
	 * @return the invoice
	 */
	public String getInvoice() {
		return invoice;
	}

	/**
	 * Sets the invoice.
	 *
	 * @param invoice the new invoice
	 */
	public void setInvoice(String invoice) {
		this.invoice = invoice;
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
	 * Gets the percentage discount.
	 *
	 * @return the percentage discount
	 */
	public int getPercentageDiscount() {
		return percentageDiscount;
	}

	/**
	 * Sets the percentage discount.
	 *
	 * @param percentageDiscount the new percentage discount
	 */
	public void setPercentageDiscount(int percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
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
	 * Gets the capture feature.
	 *
	 * @return the capture feature
	 */
	public String getCaptureFeature() {
		return captureFeature;
	}

	/**
	 * Sets the capture feature.
	 *
	 * @param captureFeature the new capture feature
	 */
	public void setCaptureFeature(String captureFeature) {
		this.captureFeature = captureFeature;
	}

	/**
	 * Gets the observation.
	 *
	 * @return the observation
	 */
	public String getObservation() {
		return observation;
	}

	/**
	 * Sets the observation.
	 *
	 * @param observation the new observation
	 */
	public void setObservation(String observation) {
		this.observation = observation;
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

}

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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class Material.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectMaterials", query = "SELECT c FROM Material c")
@XmlRootElement
@Table(name = "uj_cashflow_material", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class Material extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3290412196191642670L;

	/** The event. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	/** The description. */
	private String description;

	/** The data. */
	private Date data;

	/** The responsable. */
	private String responsable;

	/** The quantity. */
	private int quantity;

	/** The value. */
	private Double value;

	/** The unit value. */
	private Double unitValue;

	/** The resource type. */
	@Column(name = "resource_type")
	private String resourceType;

	/** The purchase local. */
	private String purchaseLocal;

	/** The unit. */
	private String unit;

	/** The brand. */
	private String brand;

	/** The reference value. */
	@Fetch(FetchMode.JOIN)
	@Column(name = "reference_value")
	private String referenceValue;

	/** The point of purchase. */
	@Fetch(FetchMode.JOIN)
	@Column(name = "point_of_purchase")
	private String pointOfPurchase;

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Date getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * Gets the responsable.
	 *
	 * @return the responsable
	 */
	public String getResponsable() {
		return responsable;
	}

	/**
	 * Sets the responsable.
	 *
	 * @param responsable the new responsable
	 */
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * Gets the resource type.
	 *
	 * @return the resource type
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * Sets the resource type.
	 *
	 * @param resourceType the new resource type
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * Gets the purchase local.
	 *
	 * @return the purchase local
	 */
	public String getPurchaseLocal() {
		return purchaseLocal;
	}

	/**
	 * Sets the purchase local.
	 *
	 * @param purchaseLocal the new purchase local
	 */
	public void setPurchaseLocal(String purchaseLocal) {
		this.purchaseLocal = purchaseLocal;
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
	 * Gets the unit.
	 *
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Sets the unit.
	 *
	 * @param unit the new unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Gets the brand.
	 *
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the brand.
	 *
	 * @param brand the new brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the reference value.
	 *
	 * @return the reference value
	 */
	public String getReferenceValue() {
		return referenceValue;
	}

	/**
	 * Sets the reference value.
	 *
	 * @param referenceValue the new reference value
	 */
	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}

	/**
	 * Gets the point of purchase.
	 *
	 * @return the point of purchase
	 */
	public String getPointOfPurchase() {
		return pointOfPurchase;
	}

	/**
	 * Sets the point of purchase.
	 *
	 * @param pointOfPurchase the new point of purchase
	 */
	public void setPointOfPurchase(String pointOfPurchase) {
		this.pointOfPurchase = pointOfPurchase;
	}

}

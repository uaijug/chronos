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

@Entity
@NamedQuery(name = "SelectMaterials", query = "SELECT c FROM Material c")
@XmlRootElement
@Table(name = "uj_cashflow_material", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Material extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = -3290412196191642670L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	private String description;

	private Date data;

	private String responsable;

	private int quantity;

	private Double value;

	private Double unitValue;

	@Column(name = "resource_type")
	private String resourceType;

	private String purchaseLocal;

	private String unit;

	private String brand;

	@Fetch(FetchMode.JOIN)
	@Column(name = "reference_value")
	private String referenceValue;

	@Fetch(FetchMode.JOIN)
	@Column(name = "point_of_purchase")
	private String pointOfPurchase;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getPurchaseLocal() {
		return purchaseLocal;
	}

	public void setPurchaseLocal(String purchaseLocal) {
		this.purchaseLocal = purchaseLocal;
	}

	public EventMain getEvent() {
		return event;
	}

	public void setEvent(EventMain event) {
		this.event = event;
	}

	public Double getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(Double unitValue) {
		this.unitValue = unitValue;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getReferenceValue() {
		return referenceValue;
	}

	public void setReferenceValue(String referenceValue) {
		this.referenceValue = referenceValue;
	}

	public String getPointOfPurchase() {
		return pointOfPurchase;
	}

	public void setPointOfPurchase(String pointOfPurchase) {
		this.pointOfPurchase = pointOfPurchase;
	}

}

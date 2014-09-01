/*
 * 
 */
package br.com.uaijug.chronos.event.supplier.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.event.budget.model.Material;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class SupplierProvidesMaterials.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectSupplierProvidesMaterials", query = "SELECT c FROM SupplierProvidesMaterials c")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_supplier_provides_materials", uniqueConstraints = @UniqueConstraint(columnNames = "cost"))

public class SupplierProvidesMaterials extends AbstractEntity<Long> implements
		Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4927879787383270418L;

	/** The supplier. */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Supplier.class)
	private Supplier supplier;

	/** The material. */
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Material.class)
	private Material material;

	/** The cost. */
	private Long cost;

	/** The limit date. */
	@Column(name = "limit_date")
	private Date limitDate;

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
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public Long getCost() {
		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(Long cost) {
		this.cost = cost;
	}

	/**
	 * Gets the limit date.
	 *
	 * @return the limit date
	 */
	public Date getLimitDate() {
		return limitDate;
	}

	/**
	 * Sets the limit date.
	 *
	 * @param limitDate the new limit date
	 */
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
}

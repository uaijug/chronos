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

@Entity
@NamedQuery(name = "SelectSupplierProvidesMaterials", query = "SELECT c FROM SupplierProvidesMaterials c")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@XmlRootElement
@Table(name = "uj_supplier_provides_materials", uniqueConstraints = @UniqueConstraint(columnNames = "cost"))
@Cacheable(true)
public class SupplierProvidesMaterials extends AbstractEntity<Long> implements
		Serializable {

	private static final long serialVersionUID = -4927879787383270418L;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Supplier.class)
	private Supplier supplier;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Material.class)
	private Material material;

	private Long cost;

	@Column(name = "limit_date")
	private Date limitDate;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
	}

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
}

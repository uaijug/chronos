package br.com.uaijug.chronos.event.budget.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.event.supplier.model.SupplierProvidesMaterials;
import br.com.uaijug.chronos.model.AbstractEntity;

@Entity
@NamedQuery(name = "SelectBudgetItem", query = "SELECT c FROM BudgetItem c")
@XmlRootElement
@Table(name = "uj_cashflow_budget_item", uniqueConstraints = @UniqueConstraint(columnNames = "catchment"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class BudgetItem extends AbstractEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1816816297839740429L;

	@ManyToOne(fetch = FetchType.EAGER)
	private Budget budget;

	@ManyToOne(fetch = FetchType.EAGER)
	private SupplierProvidesMaterials companyProvidesMaterials;

	private String observation;

	private Integer amount;

	private String catchment; // fonte propria - fonte extrena (patrocionio,

	// apoio, venda de ingresso, acoes na midia, //
	// venda de espacos, doacoes, parceiro.

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public SupplierProvidesMaterials getCompanyProvidesMaterials() {
		return companyProvidesMaterials;
	}

	public void setCompanyProvidesMaterials(
			SupplierProvidesMaterials companyProvidesMaterials) {
		this.companyProvidesMaterials = companyProvidesMaterials;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getCatchment() {
		return catchment;
	}

	public void setCatchment(String catchment) {
		this.catchment = catchment;
	}

}

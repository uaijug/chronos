/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class BudgetItem.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectBudgetItem", query = "SELECT c FROM BudgetItem c")
@XmlRootElement
@Table(name = "uj_cashflow_budget_item", uniqueConstraints = @UniqueConstraint(columnNames = "catchment"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class BudgetItem extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1816816297839740429L;

	/** The budget. */
	@ManyToOne(fetch = FetchType.EAGER)
	private Budget budget;

	/** The company provides materials. */
	@ManyToOne(fetch = FetchType.EAGER)
	private SupplierProvidesMaterials companyProvidesMaterials;

	/** The observation. */
	private String observation;

	/** The amount. */
	private Integer amount;

	/** The catchment. */
	private String catchment; // fonte propria - fonte extrena (patrocionio,

	// apoio, venda de ingresso, acoes na midia, //
	// venda de espacos, doacoes, parceiro.

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
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
	 * Gets the company provides materials.
	 *
	 * @return the company provides materials
	 */
	public SupplierProvidesMaterials getCompanyProvidesMaterials() {
		return companyProvidesMaterials;
	}

	/**
	 * Sets the company provides materials.
	 *
	 * @param companyProvidesMaterials the new company provides materials
	 */
	public void setCompanyProvidesMaterials(
			SupplierProvidesMaterials companyProvidesMaterials) {
		this.companyProvidesMaterials = companyProvidesMaterials;
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
	 * Gets the catchment.
	 *
	 * @return the catchment
	 */
	public String getCatchment() {
		return catchment;
	}

	/**
	 * Sets the catchment.
	 *
	 * @param catchment the new catchment
	 */
	public void setCatchment(String catchment) {
		this.catchment = catchment;
	}

}

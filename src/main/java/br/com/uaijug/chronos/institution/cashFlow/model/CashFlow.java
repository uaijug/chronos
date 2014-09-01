/*
 * 
 */
package br.com.uaijug.chronos.institution.cashFlow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.admin.model.Company;
import br.com.uaijug.chronos.event.model.EventMain;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class CashFlow.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@Table(name = "uj_cash_flow", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class CashFlow extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6239484745760125516L;

	/** The event. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	/** The cash flow category. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cash_flow_category_id")
	private CashFlowCategory cashFlowCategory;

	/** The company. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	/** The description. */
	private String description;

	/** The date payment. */
	private Date datePayment;

	/** The value. */
	private Double value;

	/** The percentage interest. */
	private int percentageInterest; // porcentagem juros

	/** The value total payment. */
	private Double valueTotalPayment;

	/** The cash flow type. */
	private String cashFlowType;

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
	 * Gets the date payment.
	 *
	 * @return the date payment
	 */
	public Date getDatePayment() {
		return datePayment;
	}

	/**
	 * Sets the date payment.
	 *
	 * @param datePayment the new date payment
	 */
	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
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
	 * Gets the percentage interest.
	 *
	 * @return the percentage interest
	 */
	public int getPercentageInterest() {
		return percentageInterest;
	}

	/**
	 * Sets the percentage interest.
	 *
	 * @param percentageInterest the new percentage interest
	 */
	public void setPercentageInterest(int percentageInterest) {
		this.percentageInterest = percentageInterest;
	}

	/**
	 * Gets the value total payment.
	 *
	 * @return the value total payment
	 */
	public Double getValueTotalPayment() {
		return valueTotalPayment;
	}

	/**
	 * Sets the value total payment.
	 *
	 * @param valueTotalPayment the new value total payment
	 */
	public void setValueTotalPayment(Double valueTotalPayment) {
		this.valueTotalPayment = valueTotalPayment;
	}

	/**
	 * Gets the cash flow type.
	 *
	 * @return the cash flow type
	 */
	public String getCashFlowType() {
		return cashFlowType;
	}

	/**
	 * Sets the cash flow type.
	 *
	 * @param cashFlowType the new cash flow type
	 */
	public void setCashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
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
	 * Gets the cash flow category.
	 *
	 * @return the cash flow category
	 */
	public CashFlowCategory getCashFlowCategory() {
		return cashFlowCategory;
	}

	/**
	 * Sets the cash flow category.
	 *
	 * @param cashFlowCategory the new cash flow category
	 */
	public void setCashFlowCategory(CashFlowCategory cashFlowCategory) {
		this.cashFlowCategory = cashFlowCategory;
	}

	/**
	 * Gets the company.
	 *
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Sets the company.
	 *
	 * @param company the new company
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

}

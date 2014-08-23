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

@Entity
@Table(name = "uj_cash_flow", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class CashFlow extends AbstractEntity<Long> implements Serializable {

	private static final long serialVersionUID = 6239484745760125516L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id")
	private EventMain event;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cash_flow_category_id")
	private CashFlowCategory cashFlowCategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	private String description;

	private Date datePayment;

	private Double value;

	private int percentageInterest; // porcentagem juros

	private Double valueTotalPayment;

	private String cashFlowType;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatePayment() {
		return datePayment;
	}

	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public int getPercentageInterest() {
		return percentageInterest;
	}

	public void setPercentageInterest(int percentageInterest) {
		this.percentageInterest = percentageInterest;
	}

	public Double getValueTotalPayment() {
		return valueTotalPayment;
	}

	public void setValueTotalPayment(Double valueTotalPayment) {
		this.valueTotalPayment = valueTotalPayment;
	}

	public String getCashFlowType() {
		return cashFlowType;
	}

	public void setCashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
	}

	public EventMain getEvent() {
		return event;
	}

	public void setEvent(EventMain event) {
		this.event = event;
	}

	public CashFlowCategory getCashFlowCategory() {
		return cashFlowCategory;
	}

	public void setCashFlowCategory(CashFlowCategory cashFlowCategory) {
		this.cashFlowCategory = cashFlowCategory;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}

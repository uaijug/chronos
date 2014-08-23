package br.com.uaijug.chronos.institution.cashFlow.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.com.uaijug.chronos.model.AbstractEntity;

@Entity
@Table(name = "uj_cash_flow_category", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class CashFlowCategory extends AbstractEntity<Long> implements Serializable {

	private static final long serialVersionUID = 6239484745760125516L;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

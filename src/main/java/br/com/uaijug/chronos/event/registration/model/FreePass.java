package br.com.uaijug.chronos.event.registration.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.uaijug.chronos.model.AbstractEntity;

@Entity
@NamedQuery(name = "SelectFreePass", query = "SELECT c FROM FreePass c")
@XmlRootElement
@Table(name = "uj_free_pass", uniqueConstraints = @UniqueConstraint(columnNames = "description"))
@Cacheable(true)
public class FreePass extends AbstractEntity<Long> implements Serializable {
	private static final long serialVersionUID = 8048954965440525782L;

	private String description;

	private Date data;

	private String code;

	@Column(name = "percentage_discount")
	private String percentageDiscount;

	private String keyCode;

	public FreePass() {
	}

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPercentageDiscount() {
		return percentageDiscount;
	}

	public void setPercentageDiscount(String percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
	}

	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

}
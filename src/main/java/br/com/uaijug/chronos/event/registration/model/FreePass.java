/*
 * 
 */
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

// TODO: Auto-generated Javadoc
/**
 * The Class FreePass.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Entity
@NamedQuery(name = "SelectFreePass", query = "SELECT c FROM FreePass c")
@XmlRootElement
@Table(name = "uj_free_pass", uniqueConstraints = @UniqueConstraint(columnNames = "description"))

public class FreePass extends AbstractEntity<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8048954965440525782L;

	/** The description. */
	private String description;

	/** The data. */
	private Date data;

	/** The code. */
	private String code;

	/** The percentage discount. */
	@Column(name = "percentage_discount")
	private String percentageDiscount;

	/** The key code. */
	private String keyCode;

	/**
	 * Instantiates a new free pass.
	 */
	public FreePass() {
	}

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
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the percentage discount.
	 *
	 * @return the percentage discount
	 */
	public String getPercentageDiscount() {
		return percentageDiscount;
	}

	/**
	 * Sets the percentage discount.
	 *
	 * @param percentageDiscount the new percentage discount
	 */
	public void setPercentageDiscount(String percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
	}

	/**
	 * Gets the key code.
	 *
	 * @return the key code
	 */
	public String getKeyCode() {
		return keyCode;
	}

	/**
	 * Sets the key code.
	 *
	 * @param keyCode the new key code
	 */
	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

}
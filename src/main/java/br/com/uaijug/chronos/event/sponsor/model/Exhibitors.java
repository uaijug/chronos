/*
 * 
 */
package br.com.uaijug.chronos.event.sponsor.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.uaijug.chronos.admin.model.Company;
import br.com.uaijug.chronos.event.supplier.model.Supplier;
import br.com.uaijug.chronos.model.AbstractEntity;

// TODO: Auto-generated Javadoc
/**
 * The Class Exhibitors.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Entity
@Table(name = "uj_exhibitors", uniqueConstraints = @UniqueConstraint(columnNames = "localization"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })

public class Exhibitors extends AbstractEntity<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6059762488983472684L;

	/** The exhibitors code. */
	@Column(name = "exhibitors_code")
	private String exhibitorsCode;

	/** The summary. */
	private String summary;

	/** The localization. */
	private String localization;

	/** The localx. */
	private String localx;

	/** The localy. */
	private String localy;

	/** The company. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	/** The supplier. */
	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	/**
	 * Gets the summary.
	 *
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the summary.
	 *
	 * @param summary the new summary
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * Gets the localization.
	 *
	 * @return the localization
	 */
	public String getLocalization() {
		return localization;
	}

	/**
	 * Sets the localization.
	 *
	 * @param localization the new localization
	 */
	public void setLocalization(String localization) {
		this.localization = localization;
	}

	/**
	 * Gets the localx.
	 *
	 * @return the localx
	 */
	public String getLocalx() {
		return localx;
	}

	/**
	 * Sets the localx.
	 *
	 * @param localx the new localx
	 */
	public void setLocalx(String localx) {
		this.localx = localx;
	}

	/**
	 * Gets the localy.
	 *
	 * @return the localy
	 */
	public String getLocaly() {
		return localy;
	}

	/**
	 * Sets the localy.
	 *
	 * @param localy the new localy
	 */
	public void setLocaly(String localy) {
		this.localy = localy;
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

	/**
	 * Gets the exhibitors code.
	 *
	 * @return the exhibitors code
	 */
	public String getExhibitorsCode() {
		return exhibitorsCode;
	}

	/**
	 * Sets the exhibitors code.
	 *
	 * @param exhibitorsCode the new exhibitors code
	 */
	public void setExhibitorsCode(String exhibitorsCode) {
		this.exhibitorsCode = exhibitorsCode;
	}

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

}

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

@Entity
@Table(name = "uj_exhibitors", uniqueConstraints = @UniqueConstraint(columnNames = "localization"))
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
@Cacheable(true)
public class Exhibitors extends AbstractEntity<Long> implements Serializable {

	private static final long serialVersionUID = -6059762488983472684L;

	@Column(name = "exhibitors_code")
	private String exhibitorsCode;

	private String summary;

	private String localization;

	private String localx;

	private String localy;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

	@Fetch(FetchMode.JOIN)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public String getLocalx() {
		return localx;
	}

	public void setLocalx(String localx) {
		this.localx = localx;
	}

	public String getLocaly() {
		return localy;
	}

	public void setLocaly(String localy) {
		this.localy = localy;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getExhibitorsCode() {
		return exhibitorsCode;
	}

	public void setExhibitorsCode(String exhibitorsCode) {
		this.exhibitorsCode = exhibitorsCode;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

}

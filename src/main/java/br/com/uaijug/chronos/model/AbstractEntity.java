/*
 * 
 */
package br.com.uaijug.chronos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * Estipula um contrato base para as entidades persistentes da aplicação.
 * 
 * <p>
 * Esse contrato é utilizado pelo componente responsável pelas operações básicas
 * de persistência: <code>AbstractPersistence</code>.
 * </p>
 * 
 * @author Rogerio Fontes - rogerio@wd2s.com.br - sistaurus
 * @see br.com.psi.intra.service.AbstractPersistenceImpl
 */
@MappedSuperclass
public abstract class AbstractEntity<ID> {

	/**
	 * Gets the id.
	 * 
	 * @return A referência para a chave primária (Primary Key) de cada objeto
	 *         persistido. Caso o objeto ainda não tenha sido persistido, deve
	 *         retornar <code>null</code>.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * Gets the creates the time.
	 * 
	 * @return A referência para a data de criaco (create_date) de cada objeto
	 *         persistido. Caso o objeto ainda não tenha sido persistido, deve
	 *         retornar <code>null</code>.
	 */
	/** The create time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	// @NotNull
	private Date createTime;

	/**
	 * Gets the update time.
	 * 
	 * @return A referência para a data de update (update_date) de cada objeto
	 *         persistido. Caso o objeto ainda não tenha sido persistido, deve
	 *         retornar <code>null</code>.
	 */
	/** The update time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	// @NotNull
	private Date updateTime;

	/** The active. */
	private String active;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

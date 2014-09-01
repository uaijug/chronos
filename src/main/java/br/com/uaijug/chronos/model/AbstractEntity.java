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
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * @param <ID> the generic type
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

	/**
	 * Gets the creates the time.
	 *
	 * @return the creates the time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * Sets the creates the time.
	 *
	 * @param createTime the new creates the time
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * Gets the update time.
	 *
	 * @return the update time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * Sets the update time.
	 *
	 * @param updateTime the new update time
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * Gets the active.
	 *
	 * @return the active
	 */
	public String getActive() {
		return active;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(String active) {
		this.active = active;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

}

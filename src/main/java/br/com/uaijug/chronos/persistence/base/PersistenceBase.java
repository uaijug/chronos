/*
 * 
 */
package br.com.uaijug.chronos.persistence.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.uaijug.chronos.model.AbstractEntity;
import br.com.uaijug.chronos.persistence.Persistence;

// TODO: Auto-generated Javadoc
/**
 * The Class PersistenceBase.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Stateless
@Named
public class PersistenceBase<T extends AbstractEntity<ID>, ID extends Serializable>
		implements Persistence<T, ID> {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	/** The log. */
	@Inject
	private Logger log;
	
	/** The entity class. */
	protected Class<T> clazz;
	
	/**
	 * Persistence base init.
	 */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void PersistenceBaseInit() {
		 clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	// utility database methods
	/**
	 * Find.
	 *
	 * @param id the id
	 * @param type the type
	 * @return the t
	 */
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T find(ID id, Class<T> type) {
		return (T) this.entityManager.find(type, id);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @param type the type
	 */
	public void delete(ID id, Class<T> type) {
		Object ref = this.entityManager.getReference(type, id);
		this.entityManager.remove(ref);
	}

	/* (non-Javadoc)
	 * @see br.com.uaijug.chronos.persistence.Persistence#update(java.lang.Object)
	 */
	public T update(T t) {
		return (T) this.entityManager.merge(t);
	}

	/* (non-Javadoc)
	 * @see br.com.uaijug.chronos.persistence.Persistence#save(java.lang.Object)
	 */
	public Boolean save(T t) {
		this.entityManager.persist(t);
		log.info("Object saved" + t.toString());
		return true;
	}

	/**
	 * Find all.
	 *
	 * @param type the type
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<T> findAll(Class<T> type) {
		return entityManager.createQuery(
				"Select entity FROM " + type.getSimpleName() + " entity")
				.getResultList();
	}

	/**
	 * Find with named query.
	 *
	 * @param namedQueryName the named query name
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<T> findWithNamedQuery(String namedQueryName) {
		return this.entityManager.createNamedQuery(namedQueryName)
				.getResultList();
	}

	// put here other utility method you want to share with your daos

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Sets the entity manager.
	 *
	 * @param entityManager the new entity manager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	/* (non-Javadoc)
	 * @see br.com.uaijug.chronos.persistence.Persistence#findById(java.io.Serializable)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T findById(ID id) {
		return entityManager.find(clazz, id);
	}

	/* (non-Javadoc)
	 * @see br.com.uaijug.chronos.persistence.Persistence#delete(java.lang.Object)
	 */
	@Override
	public Boolean delete(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.uaijug.chronos.persistence.Persistence#exists(java.io.Serializable)
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean exists(ID id) {
		Query query = entityManager.createQuery("SELECT COUNT(c) FROM " + clazz.getName() + " c WHERE c.id = :id");
		query.setParameter("id", id);
		
		return ((Long) query.getSingleResult()).intValue() > 0;
	}

	/* (non-Javadoc)
	 * @see br.com.uaijug.chronos.persistence.Persistence#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<T> findAll() {
		return entityManager.createQuery("from " + clazz.getName())
				.getResultList();
	}

	/* (non-Javadoc)
	 * @see br.com.uaijug.chronos.persistence.Persistence#find(java.io.Serializable, java.lang.Object)
	 */
	@Override
	public T find(ID id, T entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

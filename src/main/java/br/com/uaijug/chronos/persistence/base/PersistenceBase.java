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

@Stateless
@Named
public class PersistenceBase<T extends AbstractEntity<ID>, ID extends Serializable>
		implements Persistence<T, ID> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Inject
	private Logger log;
	
	/** The entity class. */
	protected Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void PersistenceBaseInit() {
		 clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	// utility database methods
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T find(ID id, Class<T> type) {
		return (T) this.entityManager.find(type, id);
	}

	public void delete(ID id, Class<T> type) {
		Object ref = this.entityManager.getReference(type, id);
		this.entityManager.remove(ref);
	}

	public T update(T t) {
		return (T) this.entityManager.merge(t);
	}

	public Boolean save(T t) {
		this.entityManager.persist(t);
		log.info("Object saved" + t.toString());
		return true;
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<T> findAll(Class<T> type) {
		return entityManager.createQuery(
				"Select entity FROM " + type.getSimpleName() + " entity")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<T> findWithNamedQuery(String namedQueryName) {
		return this.entityManager.createNamedQuery(namedQueryName)
				.getResultList();
	}

	// put here other utility method you want to share with your daos

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T findById(ID id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public Boolean delete(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public boolean exists(ID id) {
		Query query = entityManager.createQuery("SELECT COUNT(c) FROM " + clazz.getName() + " c WHERE c.id = :id");
		query.setParameter("id", id);
		
		return ((Long) query.getSingleResult()).intValue() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<T> findAll() {
		return entityManager.createQuery("from " + clazz.getName())
				.getResultList();
	}

	@Override
	public T find(ID id, T entity) {
		// TODO Auto-generated method stub
		return null;
	}

}

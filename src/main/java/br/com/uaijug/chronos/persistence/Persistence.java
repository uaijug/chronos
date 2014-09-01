/*
 * 
 */
package br.com.uaijug.chronos.persistence;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface Persistence.
 * 
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 */
public interface Persistence<T, ID extends Serializable> {
	
	/**
	 * Save.
	 *
	 * @param entity the entity
	 * @return the boolean
	 */
	Boolean save(T entity);
	
	/**
	 * Update.
	 *
	 * @param entity the entity
	 * @return the t
	 */
	T update(T entity);
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the t
	 */
	T findById(ID id);
	
	/**
	 * Find.
	 *
	 * @param id the id
	 * @param entity the entity
	 * @return the t
	 */
	T find(ID id, T entity);
	
	/**
	 * Delete.
	 *
	 * @param entity the entity
	 * @return the boolean
	 */
	Boolean delete(T entity);
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<T> findAll();
	
	/**
	 * Exists.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	boolean exists(ID id);
}

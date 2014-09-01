/*
 * 
 */
package br.com.uaijug.chronos.service;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Interface GenericRegistration.
 *
 * @param <E> the element type
 * @param <ID> the generic type
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public interface GenericRegistration<E, ID extends Serializable> {
	/**
	 * Register.
	 * 
	 * @param entity
	 *            the entity
	 * @return the boolean
	 */
	Boolean register(E entity);

	/**
	 * Delete.
	 * 
	 * @param entity
	 *            the entity
	 * @return the boolean
	 */
	Boolean delete(E entity);
}
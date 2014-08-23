package br.com.uaijug.chronos.service;

import java.io.Serializable;

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
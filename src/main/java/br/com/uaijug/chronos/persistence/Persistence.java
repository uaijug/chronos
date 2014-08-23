package br.com.uaijug.chronos.persistence;

import java.io.Serializable;
import java.util.List;

public interface Persistence<T, ID extends Serializable> {
	Boolean save(T entity);
	T update(T entity);
	T findById(ID id);
	T find(ID id, T entity);
	Boolean delete(T entity);
	List<T> findAll();
	boolean exists(ID id);
}

package com.worldexplorer.springbootweb.repository;

import java.util.Collection;

public interface CommonRepository<T> {

	public T save(T entity);
	public Iterable<T> save(Collection<T> entitys);
	public void delete(T entity);
	public T findById(String id);
	public Iterable<T> findAll();
}

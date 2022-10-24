package com.worldexplorer.springbootdatamongodb.repository;

import java.util.List;

import com.worldexplorer.springbootdatamongodb.domain.Town;

public interface CommonRepository<T> {

	public long count();
	public void save(T entiry);
	public void delete(T entiry);
	public List<T> findAll();
	public T findById(String id);
}

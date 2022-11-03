package com.worldexplorer.springbootdatamysql.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.worldexplorer.springbootdatamysql.entity.Category;
import com.worldexplorer.springbootdatamysql.entity.Product;

/**
 * By default, the hotkeys: CTRL + SHIFT + Y changes to lowercase. 
 * CTRL + SHIFT + X changes to UPPERCASE.
 * 
 * Some simple boilerplate SQL statements already implemented by Spring:
 * 
 * <S extends T> S save(S entity);
 * 
 * <S extends T> Iterable<S> saveAll(Iterable<S> entities);
 * 
 * Optional<T> findById(ID id);
 * 
 * boolean existsById(ID id);
 * 
 * SELECT * FROM table name;
 * Iterable<T> findAll();
 * 
 * 
 * Iterable<T> findAllById(Iterable<ID> ids);
 * 
 * long count();
 * 
 * void deleteById(ID id);
 * 
 * void delete(T entity);
 * 
 * void deleteAllById(Iterable<? extends ID> ids);
 * 
 * void deleteAll(Iterable<? extends T> entities);
 * 
 * void deleteAll();
 * 
 * @author tanku
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

	public List<Product> findByCategory(Category category);
}

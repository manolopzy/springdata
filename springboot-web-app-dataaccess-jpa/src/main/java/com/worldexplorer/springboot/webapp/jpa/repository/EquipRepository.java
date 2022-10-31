package com.worldexplorer.springboot.webapp.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.worldexplorer.springboot.webapp.jpa.domain.Equip;
/**
 * These methods are implemented automatically by spring data defined 
 * in the CrudRepository interface
 * 
 * <S extends T> S save(S entity);
 * <S extends T> Iterable<S> saveAll(Iterable<S> entities);
 * Optional<T> findById(ID id);
 * boolean existsById(ID id);
 * Iterable<T> findAll();
 * Iterable<T> findAllById(Iterable<ID> ids);
 * long count();
 * void deleteById(ID id);
 * void delete(T entity);
 * void deleteAll(Iterable<? extends T> entities);
 * void deleteAll();
 * 
 * @author tanku
 *
 */
public interface EquipRepository extends CrudRepository<Equip, String>{

}

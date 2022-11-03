package com.worldexplorer.springbootdatamysql.service;

import java.util.List;

import com.worldexplorer.springbootdatamysql.entity.Category;
import com.worldexplorer.springbootdatamysql.entity.Product;

public interface ProductService {

	public List<Product> findAll();
	
	public Product create(Product product);
	
	public Product update(Product product);
	
	public Product delete(long id);
	
	public Product findById(long id);
	
	public List<Product> findByCategory(Category category);
	
	public Product updateStock(long id, double quantity);
}

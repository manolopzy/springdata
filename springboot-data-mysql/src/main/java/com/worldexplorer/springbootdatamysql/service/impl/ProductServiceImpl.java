package com.worldexplorer.springbootdatamysql.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.worldexplorer.springbootdatamysql.entity.Category;
import com.worldexplorer.springbootdatamysql.entity.Product;
import com.worldexplorer.springbootdatamysql.repository.ProductRepository;
import com.worldexplorer.springbootdatamysql.service.ProductService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	//injection of the repository with constructor
	//@Autowired
	private final ProductRepository repository;
	
	

	@Override
	public List<Product> findAll() {
		return Streamable.of(repository.findAll()).toList();
	}

	@Override
	public Product create(Product product) {
		product.setStatus("new created");
		product.setCreatedAtDate(new Date());
		return repository.save(product);
	}

	@Override
	public Product update(Product product) {
		Optional<Product> result = repository.findById(product.getId());
		if(result.get() == null) {
			return null;
		}
		Product dbProduct = result.get();
		dbProduct.setName(product.getName());
		dbProduct.setDescription(product.getDescription());
		dbProduct.setPrice(product.getPrice());
		dbProduct.setCategory(product.getCategory());
		return repository.save(dbProduct);
	}

	@Override
	public Product delete(long id) {
		Optional<Product> result = repository.findById(id);
		if(result.get() == null) {
			return null;
		}
		Product dbProduct = result.get();
		dbProduct.setStatus("deleted");
		return repository.save(dbProduct);
	}

	@Override
	public Product findById(long id) {
		Optional<Product> result = repository.findById(id);
		if(result.get() == null) {
			return null;
		}
		return result.get();
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return repository.findByCategory(category);
	}

	@Override
	public Product updateStock(long id, double quantity) {
		Optional<Product> result = repository.findById(id);
		if(result.get() == null) {
			return null;
		}
		Product dbProduct = result.get();
		dbProduct.setStock(dbProduct.getStock() + quantity);
		return repository.save(dbProduct);
	}

}

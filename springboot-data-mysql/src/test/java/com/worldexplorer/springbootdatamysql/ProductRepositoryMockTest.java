package com.worldexplorer.springbootdatamysql;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.worldexplorer.springbootdatamysql.entity.Product;
import com.worldexplorer.springbootdatamysql.repository.ProductRepository;

@DataJpaTest
//using the embedded h2 database
//if using other databases like mysql, it could occur errors depending on the primary key generating strategy
@AutoConfigureTestDatabase
public class ProductRepositoryMockTest {

	
	@Autowired
	private ProductRepository productRepository;
	
 	@Test
	public void testFindByCategory() {
 		
 		productRepository.deleteAll();
 		Product product = ProductFactory.getInstance();
 		product = productRepository.save(product);
		List<Product> products =  productRepository.findByCategory(product.getCategory());
		Assertions.assertThat(products.size()).isEqualTo(2);
	}
}

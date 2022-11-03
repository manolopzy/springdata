package com.worldexplorer.springbootdatamysql;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.worldexplorer.springbootdatamysql.entity.Product;
import com.worldexplorer.springbootdatamysql.repository.ProductRepository;
import com.worldexplorer.springbootdatamysql.service.ProductService;
import com.worldexplorer.springbootdatamysql.service.impl.ProductServiceImpl;
//@DataJpaTest
@SpringBootTest
public class ProductServiceMockTest {
	/**
	 * Ya hemos testeado con {@link ProductRepository}
	 * Aquí no vamos a utilizar un {@link ProductRepository} real, 
	 * sino un {@link @Mock} {@link ProductRepository} instancia, 
	 * pero también hubiera usado {@link @Autowired} con una instancia 
	 * de {@link ProductRepository} real conectado a una base de datos real 
	 * como mysql.
	 * 
	 */
	//@Autowired
	@Mock
	private ProductRepository productRepository;
	
	private ProductService productService;
	private int size = 6;
	
	private long randomProductId;
	//before each test, run the setup procedure
	@BeforeEach
	public void setup() {
		productRepository.deleteAll();
		//initialization of the test through interpreting the annotations
		MockitoAnnotations.openMocks(this);
		
		productService = new ProductServiceImpl(productRepository);
		
		//definimos los comportamientos de la instancia de {@link ProductRepository}
		Product product;
		for (int i = 0; i < size; i++) {
			product = ProductFactory.getInstance();
			//when(T methodCall)
			//thenReturn(T value)
			Mockito.when(productRepository.findById(product.getId())).
			thenReturn(Optional.of(product));
			
			//Define the save behaviour of the repository for each product
			//product = ProductFactory.getRandomProduct();
			Mockito.when(productRepository.save(product)).
			thenReturn(product);
		}
		
		randomProductId = ProductFactory.getRandomProductId();
		
		
	}
	
	@Test
	public void testProductServiceFindById() {
		
		Product product = productService.findById(randomProductId);
		Assertions.assertThat(product).isNotNull();
		Assertions.assertThat(product.getId()).isEqualTo(randomProductId);
		
	}
	
	@Test
	public void testProductServiceStockUpdate() {
		Product product = productService.findById(randomProductId);
		double quantity = product.getStock();
		product = productService.updateStock(randomProductId, 100);
		Assertions.assertThat(product.getStock()).isEqualTo(quantity + 100);
		
	}
	
}

package com.worldexplorer.springbootdatamysql.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.worldexplorer.springbootdatamysql.entity.Category;
import com.worldexplorer.springbootdatamysql.entity.Product;
import com.worldexplorer.springbootdatamysql.service.ProductService;
import com.worldexplorer.springbootdatamysql.validation.WebValidationError;
import com.worldexplorer.springbootdatamysql.validation.WebValidationErrorBuilder;
/**
 * org.springframework.boot:spring-boot-starter-validation
 * 
 * 
 * http://localhost:8080/shop/order/1001/receipts?date=12-05-2017
 * 1001 is a path variable, we can use @PathVariable annotation 
 * to extract it from the URI, date=12-05-2017 is a request parameter, 
 * which can be retrieved using @RequestParam(value = "date")
 * 
 * @RequestMapping(value="/order/{id}/receipts", 
 * method = RequestMethod.GET)
 * public List methodName( @PathVariable("id") int order, 
 * @RequestParam(value = "date", required = false) Date dateOrNull) 
 * { ... }
 * 
 * URL must have the same URI : http://localhost:8080/shop/order/1001/receipts
 * 
 * @author tanku
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	//the default get request
	@GetMapping
	@ResponseBody
	public List<Product> listAll(){
		return productService.findAll();
	}
	//@RequestParam //@PathVariable
	@GetMapping("/category/{id}")
	public ResponseEntity<List<Product>> listByCategory(@PathVariable long id){
		List<Product> products = productService.findByCategory(Category.builder().id(id).build());
		if(products == null || products.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(products);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable(required = true) long id){
		Product product = productService.findById(id);
		if(product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, Errors errors){
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(WebValidationErrorBuilder.fromBindingErrors(errors));
		}
		Product createdProduct = productService.create(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
	}
	
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		Product updatedProduct = productService.update(product);
		if(updatedProduct == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedProduct);
	}
	@GetMapping("/{id}/stock")
	public ResponseEntity<Product> updateStock(@PathVariable long id, @RequestParam(name = "quantity") double quantity){
		Product updatedProduct = productService.updateStock(id, quantity);
		if(updatedProduct == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedProduct);
	}
	
	/**
	 * @ExceptionHandler. 
	 * The Spring MVC automatically declares built-in resolvers
	 * for exceptions and adds the support to this annotation. 
	 * In this case, the @ExceptionHandler is declared inside 
	 * this controller class (or you can use it within a 
	 * @ControllerAdvice interceptor) and any exception will be
	 * redirected to the handleException method. 
	 * 
	 * @ResponseStatus annotation is used when a method has 
	 * a void return type (or null return value). 
	 * This annotation sends back the HTTP
	 * status code specified in the response.
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public WebValidationError handleException(Exception exception) {
		return new WebValidationError(exception.getMessage());
	}
}

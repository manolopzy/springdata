package com.worldexplorer.springbootweb.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.worldexplorer.springbootweb.domain.Equip;
import com.worldexplorer.springbootweb.domain.EquipBuilder;
import com.worldexplorer.springbootweb.repository.CommonRepository;
import com.worldexplorer.springbootweb.validation.WebValidationError;
import com.worldexplorer.springbootweb.validation.WebValidationErrorBuilder;

/**
 * Spring MVC provides the @Controller and @RestController to 
 * represent http request mappings, exception handling, 
 * and more. With these annotations, there is no need 
 * to extend or implement interfaces specific interfaces.
 * 
 * HTTP method-specific shortcut variants of @RequestMapping 
 * are @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, 
 * and @PatchMapping annotations
 * @GetMapping - shortcut for @RequestMapping(method = RequestMethod.GET)
 * @PostMapping - shortcut for @RequestMapping(method = RequestMethod.POST)
 * @PutMapping - shortcut for @RequestMapping(method = RequestMethod.PUT)
 * @DeleteMapping - shortcut for @RequestMapping(method =RequestMethod.DELETE)
 * @PatchMapping - shortcut for @RequestMapping(method = RequestMethod.PATCH)
 * @author tanku
 *
 */

/**
 * @RequestMapping annotation maps http requests to controller methods. 
 * There are several attributes to match URLs, HTTP methods 
 * (GET, PUT, DELETE, etc.), request parameters, headers, and media types. 
 * It can be used at class level or method level for specific endpoint mapping. 
 * In this case, it is marked with "/gamedemo", meaning that 
 * all the methods have this prefix.
 *
 */

/**
 * ResponseEntity<T> class represents a full response, 
 * including HTTP headers, and its body is converted through 
 * HttpMessageConverters and written to the HTTP response. 
 * The ResponseEntity<T> class supports a fluent API, so
 * it is easy to create the response.
 * Fluent api流式接口
 * StringBuilder sb = new StringBuilder().append(24).append("student").append(3.5);
 * Each "append" return a self reference for further construction.
 *
 */
@RestController
@RequestMapping("/gamedemo")
public class EquipController {

	private CommonRepository<Equip> repository;

	@Autowired
	public EquipController(CommonRepository<Equip> repository) {
		this.repository = repository;
	}

	/**
	 * This method corresponds to "root/gamedemo/equips" requests
	 * @GetMapping is a shortcut variant of the @RequestMapping 
	 * annotation, useful for HTTP GET methods. 
	 * @GetMapping is equivalent to 
	 * @RequestMapping(value="/equips", method = {RequestMethod.GET})
	 * @return
	 */
	@GetMapping("/equips")
	public ResponseEntity<Iterable<Equip>> getEquips() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	/**
	 * @PathVariable annotation is useful when 
	 * a URL expression contains parameters, in this case, an id,
	 * "/gamedemo/equip/{id}", where the parameter's name
	 * must match the name of the controller method parameter
	 * @param id
	 * @return
	 */
	@GetMapping("/equip/{id}")
	public ResponseEntity<Equip> getEquipById(@PathVariable String id) {
		return ResponseEntity.ok(repository.findById(id));
	}
	/**
	 * @PatchMapping annotation, like others, is used for mapping 
	 * HTTP {@code PATCH} requests onto specific handler methods, 
	 * which is a shortcut variant of the @RequestMapping 
	 * annotation, in this case, it is equivalent to 
	 * @RequestMapping(value = "/equip/{id}", method = RequestMethod.PATCH)
	 * @param id
	 * @return
	 */
	@PatchMapping("/equip/{id}")
	public ResponseEntity<Equip> setCompleted(@PathVariable String id) {
		Equip result = repository.findById(id);
		result.setCompleted(true);
		repository.save(result);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(result.getId()).toUri();
		return ResponseEntity.ok().header("Location", location.toString()).build();
	}
	
	/**
	 * equivalence of @PostMapping(value = "/equip")
	 * 
	 * @RequestBody annotation sends a request with a body. 
	 * Normally, when you submit a form or a particular content,
	 * this request handler receives a JSON format of Equip class, 
	 * then the HttpMessageConverter deserializes the JSON into 
	 * a Equip instance; this is done automatically thanks 
	 * to Spring Boot and its auto-configuration because 
	 * it registers the MappingJackson2HttpMessageConverter 
	 * by default.
	 * 
	 * @param equip
	 * @param errors
	 * @return
	 */
	
	@RequestMapping(value = "/equip", method = { RequestMethod.POST, RequestMethod.PUT })
	public ResponseEntity<?> createEquip(@Valid @RequestBody Equip equip, Errors errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(WebValidationErrorBuilder.fromBindingErrors(errors));
		}
		Equip result = repository.save(equip);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/equip/{id}")
	public ResponseEntity<Equip> deleteEquip(@PathVariable String id, Errors errors) {
		repository.delete(EquipBuilder.create().withId(id).build());
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/equip")
	public ResponseEntity<Equip> deleteEquip(@RequestBody Equip equip) {
		repository.delete(equip);
		return ResponseEntity.noContent().build();
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

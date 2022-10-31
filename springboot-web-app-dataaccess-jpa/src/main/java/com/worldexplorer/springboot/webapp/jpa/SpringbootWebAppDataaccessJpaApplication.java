package com.worldexplorer.springboot.webapp.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * With spring data, sql or no sql, there is no need to worry about the 
 * implementation of basic CRUD functionalities, We only 
 * need to create an interface extends from a Repository<T,ID>, 
 * CrudRepository<T,ID>, or JpaRepository<T,ID>. The
 * JpaRepository interface provides not only what the 
 * CrudRepository does, but also extends from the 
 * PagingAndSortingRepository interface to offer
 * extra functionality. 
 * 
 * In a simple spring project, you are required to 
 * use the @EnableJpaRepositories to activate the 
 * spring container to manage the life cycle of 
 * the repositories in your app, but in a spring boot 
 * project, it executes its auto configuration as long as 
 * it finds the spring data jpa jar in the project. 
 * 
 */
/**
 * Podemos sobrescribir las propiedades proveidas por defecto por 
 * Spring boot, cuando usamos Spring data jpa, una de ellas es la 
 * capacidad de crear DDL (data definition language), el cual está 
 * cerrado por defecto, lo podemos activar para hacer la ingeniería 
 * inversa con nuestros modelo de dominio (domain model), es decir, 
 * generar las tablas y construir cualquier relaciones definidas 
 * en tus clases.
 * 
 * 
 * @author tanku
 *
 */
@SpringBootApplication
public class SpringbootWebAppDataaccessJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebAppDataaccessJpaApplication.class, args);
	}

}

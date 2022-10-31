package com.worldexplorer.springboot.webapp.dataaccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * 1 If we don’t specify any data source (in the JavaConfig, XML or
 * application.properties) the Spring Boot auto-configuration will choose the H2
 * embedded database by default
 * 
 * 
 * 2 Spring Framework allows you to initialize your
 * database by creating or altering any table, or 
 * inserting/updating data when your application starts. 
 * To initialize a Spring app (not Spring Boot), it is
 * necessary to add configuration (XML or JavaConfig); 
 * but this app is Spring Boot. If Spring Boot finds 
 * the schema.sql and/or data.sql files in the resource 
 * directory, it executes them automatically.
 *
 */

@SpringBootApplication
public class SpringbootWebAppDataAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebAppDataAccessApplication.class, args);
	}

}

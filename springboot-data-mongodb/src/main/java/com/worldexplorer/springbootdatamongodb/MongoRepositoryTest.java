package com.worldexplorer.springbootdatamongodb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.worldexplorer.springbootdatamongodb.domain.Arithmetic;
import com.worldexplorer.springbootdatamongodb.repository.ArithmeticRepository;
@Configuration
@EnableMongoRepositories
public class MongoRepositoryTest {

	public static final String DB_NAME = "test";

	@Bean
	public MongoTemplate mongoTemplate(MongoClient mongoClient) {
		return new MongoTemplate(mongoClient, DB_NAME);
	}

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create();
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoRepositoryTest.class);
		ArithmeticRepository repository = ctx.getBean(ArithmeticRepository.class);
		Arithmetic arithmetic = new Arithmetic();
		arithmetic = repository.save(arithmetic);
		System.out.print(arithmetic.toString());
		
	}
}

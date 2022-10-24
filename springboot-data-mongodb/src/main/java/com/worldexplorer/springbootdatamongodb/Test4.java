package com.worldexplorer.springbootdatamongodb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.worldexplorer.springbootdatamongodb.domain.Producer;
import com.worldexplorer.springbootdatamongodb.domain.Vehicle;
import com.worldexplorer.springbootdatamongodb.repository.MongoTemplateVehicleRepository;

@Configuration
public class Test4 {

	public static final String DB_NAME = "test";

	@Bean
	public MongoTemplate mongoTemplate(MongoClient mongoClient) {
		return new MongoTemplate(mongoClient, DB_NAME);
	}

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create();
	}

	@Bean
	public MongoTemplateVehicleRepository vehicleRepository(MongoTemplate mongo) {
		return new MongoTemplateVehicleRepository(mongo);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Test4.class);
		MongoTemplateVehicleRepository repository = ctx.getBean(MongoTemplateVehicleRepository.class);

		System.out.println("Number of Vehicles: " + repository.count());

		List<Producer> producers = new ArrayList<>();
		Producer producer = new Producer("worldexplorer", "We love exploring the world");
		producer = new Producer("explorer", "We love exploring the world");
		producers.add(producer);
		producers.add(producer);

		// repository.save(new Vehicle("TEM0001", "RED", 4, 4));
		// repository.save(new Vehicle("TEM0002", "RED", 4, 4));
		// repository.save(new Vehicle("TEM0003", "RED", 4, 4, producers));
		// repository.save(new Vehicle("TEM0004", "RED", 4, 4, producers));

		System.out.println("Number of Vehicles: " + repository.count());
		// Vehicle vehicle = repository.findById("TEM0001");
		Vehicle vehicle = repository.findById("TEM0003");
		System.out.println("vehicle = " + vehicle);
		producers = vehicle.getProducers();
		if(producers != null) {
			producers.forEach(System.out::println);
		}
		List<Vehicle> vehicleList = repository.findAll();
		System.out.println("Number of Vehicles: " + vehicleList.size());
		vehicleList.forEach(System.out::println);
		System.out.println("Number of Vehicles: " + repository.count());

	}
}

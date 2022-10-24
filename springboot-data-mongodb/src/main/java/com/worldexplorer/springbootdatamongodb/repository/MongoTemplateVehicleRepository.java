package com.worldexplorer.springbootdatamongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.worldexplorer.springbootdatamongodb.domain.Vehicle;

public class MongoTemplateVehicleRepository implements CommonRepository<Vehicle>{

	private final MongoTemplate mongoTemplate;
	private static final String collection = "vehicles";
	private final String database = "test";
	public MongoTemplateVehicleRepository(MongoTemplate mongo) {
		this.mongoTemplate = mongo;
	}
	@Override
	public long count() {
		return mongoTemplate.count(new Query(), collection);
	}

	@Override
	public void save(Vehicle entity) {
		mongoTemplate.save(entity, collection);
	}

	@Override
	public void delete(Vehicle entity) {
		mongoTemplate.remove(entity, collection);
	}

	@Override
	public List<Vehicle> findAll() {
		return mongoTemplate.findAll(Vehicle.class, collection);
	}

	@Override
	public Vehicle findById(String vehicleNo) {
		//Document query = new Document("vehicleNo", vehicleNo);
		//new Query(where("vehicleNo").is(vehicleNo))
		//return mongoTemplate.getCollection(collection).find(query);
		return mongoTemplate.findOne(new Query(Criteria.where("vehicleNo").is(vehicleNo)), Vehicle.class, collection);
	}

}

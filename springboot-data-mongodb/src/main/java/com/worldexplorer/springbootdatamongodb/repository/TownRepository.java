package com.worldexplorer.springbootdatamongodb.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import com.worldexplorer.springbootdatamongodb.domain.Town;
/**
 * We are going to use spring mongodb to connect the server
 * 
 * @author tanku
 *
 */
public class TownRepository implements CommonRepository<Town> {

	/**
	 * A client-side representation of a MongoDB cluster. Instances can represent
	 * either a standalone MongoDB instance, a replica set,or a sharded cluster.
	 * Instance of this class are responsible for maintaining an up-to-date state of
	 * the cluster,and possibly cache resources related to this, including
	 * background threads for monitoring, and connection pools.
	 * 這代表的是一個MongoDB集群的客戶端訪問對象，包含了背後負責監控的 綫程，連接池等。
	 */
	private final MongoClient mongo;
	private final String database;
	private final String collection;

	public TownRepository(MongoClient mongo, String database, String collection) {
		this.mongo = mongo;
		this.database = database;
		this.collection = collection;
	}

	@Override
	public long count() {
		return getCollection().countDocuments();
	}

	@Override
	public void save(Town entity) {
		//A basic implementation of BSON object that is MongoDB specific
		if(entity.get_id() == null || entity.get_id() == "") {
			Document dbTown = transform(entity);
			System.out.println(dbTown.toJson());
			getCollection().insertOne(dbTown);
		}
		else {
			
			Document query = new Document("_id", entity.get_id());
			Document update = new Document();
			
			UpdateResult fromDB = getCollection().updateOne(query, update);
			
		}
		
	}

	@Override
	public void delete(Town entity) {
		Document query = new Document("_id", entity.get_id());
		getCollection().deleteOne(query);
	}

	@Override
	public List<Town> findAll() {
		//Why can not get the number of documents from the cursor?
		FindIterable<Document> cursor = getCollection().find();
		List<Town> towns = new ArrayList<>((int)count());
		for (Document dbObject : cursor) {
			towns.add(transform(dbObject));
		}
		return towns;
	}

	@Override
	public Town findById(String _id) {
		Document query = new Document("_id", _id);
		FindIterable<Document> iterable = getCollection().find(query);
		return transform(iterable.first());
	}

	private MongoCollection<org.bson.Document> getCollection() {
		return mongo.getDatabase(database).getCollection(collection);
	}

	
	private Town transform(Document document) {
		return new Town().toPOJO(document);
	}

	private Document transform(Town town) {
		return town.toDBObject();
	}

}

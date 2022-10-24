package com.worldexplorer.springbootdatamongodb.repository;

import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.worldexplorer.springbootdatamongodb.domain.Player;

/**
 * In this repository, we will use java mongdb driver to connect the mongodb
 * server, but actually, Spring data uses the MongoDB Java Driver core as 
 * the base stone
 * Both {@link org.bson.Document} and {@linkcom.mongodb.BasicObject} are 
 * implementations of {@link org.bson.BSON} "binary javascript object notation", 
 * which are containers for key-value pairs.
 * 
 * But it seems that the newest version of {@link com.mongodb.MongoClient} only 
 * support {@link org.bson.Document} collection, the {@linkcom.mongodb.BasicObject} 
 * collection is deprecated.
 * 
 * However, {@link org.bson.Document} and {@linkcom.mongodb.BasicObject} both 
 * are map containers, so you can pass the data stored in a document to 
 * a basicObject and vice verse. 
 * @author tanku
 *
 */
public class PlayerRepository implements CommonRepository<Player> {
	/**
	 * A MongoDB client with internal connection pooling.
	 */
	private static final MongoClient mongo = MongoClients.create();
	private static final String collection = "players";
	private final String database;

	public PlayerRepository(String database) {
		this.database = database;
	}

	@Override
	public long count() {
		return getCollection().countDocuments();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(Player entiry) {
		getCollection().insertOne(new Document(entiry.toDBObject().toMap()));
		//mongo.getDB(database).getCollection(collection).save(entiry.toDBObject())
	}

	@Override
	public void delete(Player entiry) {
		BasicDBObject query = new BasicDBObject("_id", entiry.get_id());
		getCollection().deleteOne(query);
	}

	@Override
	public List<Player> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player findById(String id) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	private MongoCollection<Document> getCollection() {
		//mongo.getDB(database).getCollection(collection).save(null);
		return mongo.getDatabase(database).getCollection(collection);
	}

}

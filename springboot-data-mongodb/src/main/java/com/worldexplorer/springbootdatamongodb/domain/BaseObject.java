package com.worldexplorer.springbootdatamongodb.domain;

import com.mongodb.DBObject;
/**
 * DBObject is a key-value map representation interface that can be saved to the mongo database.
 * 
 * @author tanku
 *
 * @param <T>
 */
public interface BaseObject<T> {

	public DBObject toDBObject();
	
	public T toPOJO(DBObject dbObject);
}

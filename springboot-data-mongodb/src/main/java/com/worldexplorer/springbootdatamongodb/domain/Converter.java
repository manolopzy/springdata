package com.worldexplorer.springbootdatamongodb.domain;

import org.bson.Document;

public interface Converter<T> {

	public Document toDBObject();
	
	public T toPOJO(Document document);
}

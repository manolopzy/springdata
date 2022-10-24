package com.worldexplorer.springbootdatamongodb;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * Create documents using {@link com.mongodb.BasicDBObject} or 
 * {@link org.bson.Document}
 * You can nest your document to whatever depth you need
 * @author tanku
 *
 */
public class Test2 {

	public static void main(String[] args) {
		DBObject emptyDoc = new BasicDBObject();
		System.out.println("emptyDoc = " + emptyDoc);

		// Creates a simple document with a given key and value.
		DBObject simpleDoc = new BasicDBObject("name", "Manolo");
		System.out.println("simpleDoc = " + simpleDoc);

		// Creates a nested document with arrays.
		DBObject document = 
				new BasicDBObject("firstName", "Eustaquio").
				append("lastName", "Abo").
				append("age", 36).
				append("email", "eustaquio@gmail.com").
				append("address", 
						new BasicDBObject("street", "San Mopean").
						append("city", "Elche").
						append("country", "Spain")).
				append("hobbies", Arrays.asList("Eating", "Sleeping", "Reading"));
		System.out.println("document = " + document);
	}
}

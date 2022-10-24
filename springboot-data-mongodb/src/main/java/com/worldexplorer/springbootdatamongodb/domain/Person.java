package com.worldexplorer.springbootdatamongodb.domain;

import org.bson.Document;

public class Person implements Converter<Person>{

	private String name;
	
	private String description;

	public Person(String name, String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public Document toDBObject() {
		Document dbObject = new Document();
		dbObject.append("name", name);
		dbObject.append("description", description);
		return dbObject;
	}

	@Override
	public Person toPOJO(Document document){
		this.name = (String)document.get("name");
		this.description = (String)document.get("description");
		return this;
	}
}

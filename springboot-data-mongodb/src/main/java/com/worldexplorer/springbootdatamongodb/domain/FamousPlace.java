package com.worldexplorer.springbootdatamongodb.domain;

import org.bson.Document;

public class FamousPlace implements Converter<FamousPlace>{

	private String name = "Parque palmeras ";
	
	private String address = "Passeig de l'Estació, s/n, 03202 Elx, Alicante";

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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public Document toDBObject() {
		Document dbObject = new Document();
		dbObject.append("name", name);
		dbObject.append("address", address);
		return dbObject;
	}

	@Override
	public FamousPlace toPOJO(Document document){
		this.name = (String)document.get("name");
		this.address = (String)document.get("address");
		return this;
	}
	
	
}

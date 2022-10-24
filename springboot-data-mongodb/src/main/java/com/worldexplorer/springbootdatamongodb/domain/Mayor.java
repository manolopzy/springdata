package com.worldexplorer.springbootdatamongodb.domain;

import org.bson.Document;

public class Mayor implements Converter<Mayor>{
	
	private String name;

	private String party;
	public Mayor() {}
	public Mayor(String name, String party) {
		this.name = name;
		this.party = party;
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
	 * @return the party
	 */
	public String getParty() {
		return party;
	}

	/**
	 * @param party the party to set
	 */
	public void setParty(String party) {
		this.party = party;
	}

	@Override
	public Document toDBObject() {
		Document dbObject = new Document();
		dbObject.append("name", name);
		dbObject.append("party", party);
		return dbObject;
	}

	@Override
	public Mayor toPOJO(Document document){
		this.name = (String)document.get("name");
		this.party = (String)document.get("party");
		return this;
	}
	
	
}

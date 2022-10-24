package com.worldexplorer.springbootdatamongodb.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;


/**
 * The mapping from a json document to java object
 * 
 * db.towns.insertOne(
 * {
 * name: "New York", 
 * population: 22200000, 
 * lastCensus: ISODate("2016-07-01"), 
 * famousFor: [ "the MOMA", "food", "Derek Jeter" ],
 * mayor : { name : "Bill de Blasio", party : "D" } 
 * }
 * )
 * 
 * @author tanku
 *
 */
public class Town implements Converter<Town>{

	private String _id = null;

	private String name;

	private int population;

	
	private List<String> famousFor;

	private List<FamousPlace> famousPlaces;
	
	private Mayor mayor;

	private Map<String, Person> famousPersons;

	private Map<String, String> otherStuff;
	
	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
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
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * @return the famousFor
	 */
	public List<String> getFamousFor() {
		return famousFor;
	}

	/**
	 * @param famousFor the famousFor to set
	 */
	public void setFamousFor(List<String> famousFor) {
		this.famousFor = famousFor;
	}

	/**
	 * @return the mayor
	 */
	public Mayor getMayor() {
		return mayor;
	}

	/**
	 * @param mayor the mayor to set
	 */
	public void setMayor(Mayor mayor) {
		this.mayor = mayor;
	}

	/**
	 * @return the famousPlaces
	 */
	public List<FamousPlace> getFamousPlaces() {
		return famousPlaces;
	}

	/**
	 * @param famousPlaces the famousPlaces to set
	 */
	public void setFamousPlaces(List<FamousPlace> famousPlaces) {
		this.famousPlaces = famousPlaces;
	}

	/**
	 * @return the famousPersons
	 */
	public Map<String, Person> getFamousPersons() {
		return famousPersons;
	}

	/**
	 * @param famousPersons the famousPersons to set
	 */
	public void setFamousPersons(Map<String, Person> famousPersons) {
		this.famousPersons = famousPersons;
	}

	/**
	 * @return the otherStuff
	 */
	public Map<String, String> getOtherStuff() {
		return otherStuff;
	}

	/**
	 * @param otherStuff the otherStuff to set
	 */
	public void setOtherStuff(Map<String, String> otherStuff) {
		this.otherStuff = otherStuff;
	}

	@Override
	public Document toDBObject() {
		Document dbObject  = _id == null ? new Document() : new Document("_id", _id);
		dbObject.append("name", name);
		dbObject.append("population", population);
		//a json array ["a", "b", "c"]
		//put a list of string
		if(famousFor != null && famousFor.size() > 0) {
			dbObject.append("famousFor", famousFor);
		}
		//put a list of simple java objects
		if(famousPlaces != null && famousPlaces.size() > 0) {
			List<Document> places = new ArrayList<>(famousPlaces.size());
			for (FamousPlace place : famousPlaces) {
				places.add(place.toDBObject());
			}
			dbObject.append("famousPlaces", places);
		}
		if(famousPersons != null && famousPersons.size() > 0) {
			Map<String, Document> persons = new HashMap<>();
			for (Map.Entry<String, Person> personEntry : famousPersons.entrySet()) {
				persons.put(personEntry.getKey(), personEntry.getValue().toDBObject());
			}
			dbObject.append("famousPersons", persons);
		}
		
		dbObject.append("otherStuff", otherStuff);
		
		//put a simple java object
		if(mayor != null) {
			dbObject.append("mayor", mayor.toDBObject());
		}
		return dbObject;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Town toPOJO(Document dbObject) {
		this._id = ((org.bson.types.ObjectId)dbObject.get("_id")).toHexString();
		this.name = (String)dbObject.get("name");
		this.population = (int)dbObject.get("population");
		if(dbObject.get("famousFor") != null) {
			this.famousFor = (List<String>)dbObject.get("famousFor");
		}
		if(dbObject.get("mayor") != null) {
			Document document = (Document)dbObject.get("mayor");
			Mayor mayor = new Mayor();
			this.mayor = mayor.toPOJO(document);
			
		}
		if(dbObject.get("otherStuff") != null) {
			System.out.println(dbObject.get("otherStuff"));
			otherStuff = (Map<String, String>)dbObject.get("otherStuff");
		}
		
		//this.mayor = (Mayor)dbObject.get("mayor");
		System.out.println(dbObject.get("mayor"));
		return this;
	}

}

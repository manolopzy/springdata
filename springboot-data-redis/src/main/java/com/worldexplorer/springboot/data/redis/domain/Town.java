package com.worldexplorer.springboot.data.redis.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



/**
 * @author tanku
 *
 */
public class Town implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private int population;

	
	private List<String> famousFor;

	private List<FamousPlace> famousPlaces;
	
	private Mayor mayor;

	private Map<String, Person> famousPersons;

	private Map<String, String> otherStuff;
	
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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


}

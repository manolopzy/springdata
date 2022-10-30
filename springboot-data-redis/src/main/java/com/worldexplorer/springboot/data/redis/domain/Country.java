package com.worldexplorer.springboot.data.redis.domain;

import java.util.List;

import lombok.Data;

@Data
public class Country {

	

	private String id;

	private String name;

	private int population;
	
	private List<City> cities;
	public Country(String id, String name, int population, List<City> cities) {
		this.id = id;
		this.name = name;
		this.population = population;
		this.cities = cities;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}
	/**
	 * @return the cities
	 */
	public List<City> getCities() {
		return cities;
	}
	
	@Override
	public String toString() {

		return "country : id = " + id + ";" + "name = " + name + ";" + "population = " + population + ";" + "cities = " + cities.toString() + ";";
	}
}

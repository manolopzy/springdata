package com.worldexplorer.springboot.data.redis.domain;

import lombok.Data;

@Data
public class City {
	
	public City(String name, String description) {
		this.name = name;
		this.description = description;
	}

	private String name = "Alicante";
	
	private String description = 
			"Alicante is a port city on Spain’s southeastern Costa Blanca, and the capital of the Alicante province. Its old town, Barrio de la Santa Cruz, has narrow streets, colored houses and a nightlife scene. From here, an elevator or a steep climb leads to medieval Castillo de Santa Bárbara, set on a hilltop with sweeping views of the Mediterranean coast.";

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

	
}

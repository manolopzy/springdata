package com.worldexplorer.springboot.data.redis.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class FamousCity implements Serializable{
	
	/**
	 * for redis serilazation and deserialization
	 */
	private static final long serialVersionUID = -3680034378046396110L;

	public FamousCity(int score, String name, String description) {
		this.name = name;
		this.description = description;
		this.score = score;
	}

	@Id
	private String id;
	private int score;
	private String name = "Alicante";
	
	private String description = 
			"Alicante is a port city on Spain’s southeastern Costa Blanca, and the capital of the Alicante province. Its old town, Barrio de la Santa Cruz, has narrow streets, colored houses and a nightlife scene. From here, an elevator or a steep climb leads to medieval Castillo de Santa Bárbara, set on a hilltop with sweeping views of the Mediterranean coast.";

	
}

package com.worldexplorer.springboot.data.redis.domain;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
@XmlRootElement
@Data
public class Book implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 3331664519185816462L;

	public Book(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Book(String name, String description, Author author) {
		this.name = name;
		this.description = description;
		this.author = author;
	}
	@XmlAttribute
	private String name = "living in Alicante";
	@XmlAttribute
	private String description = 
			"Alicante is a port city on Spain’s southeastern Costa Blanca, and the capital of the Alicante province. Its old town, Barrio de la Santa Cruz, has narrow streets, colored houses and a nightlife scene. From here, an elevator or a steep climb leads to medieval Castillo de Santa Bárbara, set on a hilltop with sweeping views of the Mediterranean coast.";
	@XmlElement
	private Author author;
	
}

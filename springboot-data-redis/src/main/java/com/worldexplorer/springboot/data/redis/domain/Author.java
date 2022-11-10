package com.worldexplorer.springboot.data.redis.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Author implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 144090217516851767L;

	public Author(String name, String description) {
		this.name = name;
		this.description = description;
	}

	private String name = "Lucia";
	
	private String description = 
			"Lucia is an amazing writer full of imagination";

	
}

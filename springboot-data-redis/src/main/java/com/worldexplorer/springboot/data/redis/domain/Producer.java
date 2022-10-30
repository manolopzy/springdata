package com.worldexplorer.springboot.data.redis.domain;

import java.io.Serializable;

public class Producer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name = "worldexplorer";

	private String description = "we are world explorers";

	public Producer() {}
	public Producer(String name, String description) {
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
}

package com.worldexplorer.springboot.data.redis.domain;

import java.io.Serializable;

public class FamousPlace implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

}

package com.worldexplorer.springboot.data.redis.domain;

import java.io.Serializable;

public class Mayor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
}

package com.worldexplorer.springboot.data.redis.domain;

public class Prop{

	


	private int id;

	private int propId;

	private String name;
	public Prop(int id, int propId, String name) {
		this.id = id;
		this.propId = propId;
		this.name = name;
	}
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
	 * @return the propId
	 */
	public int getPropId() {
		return propId;
	}
	/**
	 * @param propId the propId to set
	 */
	public void setPropId(int propId) {
		this.propId = propId;
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
	
	
	
}

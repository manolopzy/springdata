package com.worldexplorer.springbootdatamongodb.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Equip  implements BaseObject<Equip> {

	private int id;
	
	private int equipId;
	
	private String name;
	
	private int level;

	public Equip(int id, int equipId, String name, int level) {
		this.id = id;
		this.equipId = equipId;
		this.name = name;
		this.level = level;
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
	 * @return the equipId
	 */
	public int getEquipId() {
		return equipId;
	}

	/**
	 * @param equipId the equipId to set
	 */
	public void setEquipId(int equipId) {
		this.equipId = equipId;
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
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public BasicDBObject toDBObject() {
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.append("id", id);
		dbObject.append("equipId", equipId);
		dbObject.append("name", name);
		dbObject.append("level", level);
		return dbObject;
	}

	@Override
	public Equip toPOJO(DBObject dbObject) {
		
		return new Equip((int)dbObject.get("id"), 
				(int)dbObject.get("equipId"),
				(String)dbObject.get("name"),
				(int)dbObject.get("level"));
	}
	
	
}

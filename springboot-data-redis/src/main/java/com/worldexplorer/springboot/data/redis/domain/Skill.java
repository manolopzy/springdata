package com.worldexplorer.springboot.data.redis.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author tanku
 *
 */
public class Skill implements RedisBaseObject{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2722384335724286695L;

	private long id;
	
	private int skillId;
	
	private long playerId;
	
	private int level;
	
	

	

	public Skill(long id, int skillId, long playerId, int level) {
		this.id = id;
		this.skillId = skillId;
		this.playerId = playerId;
		this.level = level;
	}

	public Skill() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the skillId
	 */
	public int getSkillId() {
		return skillId;
	}

	/**
	 * @param skillId the skillId to set
	 */
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	

	/**
	 * @return the playerId
	 */
	public long getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
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

	@SuppressWarnings("unchecked")
	@Override
	public Skill deserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			System.out.println("read bytes = " + bytes.length);
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Skill)(ois.readObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] serialize() {
		ObjectOutputStream out = null;
		ByteArrayOutputStream bytesOut = null;
		try {
			bytesOut = new ByteArrayOutputStream();
			// the object can be read or reconstructed using {@link ObjectInputStream}
			out = new ObjectOutputStream(bytesOut);
			out.writeObject(this);
			out.flush();
			// or simply using the following
			// oos.writeObject(object);
			byte[] bytes = bytesOut.toByteArray();
			System.out.println("write bytes = " + bytes.length);
			bytesOut.close();
			out.close();
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {

		return "skill: id = " + id + ";" + "skillId = " + skillId + ";" + "playerId = " + playerId + ";" + "level = " + level;
	}
}

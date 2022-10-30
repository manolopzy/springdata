package com.worldexplorer.springboot.data.redis.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Equip implements RedisBaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private int equipId;

	private String name;

	private int level;

	public Equip() {
	}

	public Equip(long id, int equipId, String name, int level) {
		this.id = id;
		this.equipId = equipId;
		this.name = name;
		this.level = level;
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

	@SuppressWarnings("unchecked")
	@Override
	public Equip deserialize(byte[] bytes) {
		ByteArrayInputStream bytesInput = null;
		try {
			System.out.println("read bytes = " + bytes.length);
			bytesInput = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = 
					new ObjectInputStream(bytesInput);
			id = ois.readLong();
			equipId = ois.readInt();
			name = (String) ois.readObject();
			level = ois.readInt();
			ois.close();
			return this;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * customized object serializing
	 */
	@Override
	public byte[] serialize() {
		ObjectOutputStream out = null;
		ByteArrayOutputStream bytesOut = null;
		try {
			bytesOut = new ByteArrayOutputStream();
			// the object can be read or reconstructed using {@link ObjectInputStream}
			out = new ObjectOutputStream(bytesOut);
			out.writeLong(id);
			out.writeInt(equipId);
			out.writeObject(name);
			out.writeInt(level);
			//This will write any buffered output bytes and flush
		    // through to the underlying stream.
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

		return "equip: id = " + id + ";" + "equipId = " + equipId + ";" + "name = " + name + ";" + "level = " + level;
	}
}

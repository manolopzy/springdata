package com.worldexplorer.springbootdatamongodb.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Player implements BaseObject<Player> {

	private String _id = null;

	private String name;

	private long exp;

	private int level;

	private List<String> favoriteBooks;

	//key country value dessert name
	private Map<String, String> favoriteDesserts;
	
	private Map<String, Equip> equips;

	
	public Player(String _id, String name, long exp, int level) {
		this._id = _id;
		this.name = name;
		this.exp = exp;
		this.level = level;
	}

	public Player(String _id, String name, long exp, int level, List<String> favoriteBooks, Map<String, String> favoriteDesserts) {
		this._id = _id;
		this.name = name;
		this.exp = exp;
		this.level = level;
		this.favoriteBooks = favoriteBooks;
		this.favoriteDesserts = favoriteDesserts;
	}

	public Player() {
		
	}

	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
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
	 * @return the exp
	 */
	public long getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(long exp) {
		this.exp = exp;
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

	/**
	 * @return the equips
	 */
	public Map<String, Equip> getEquips() {
		return equips;
	}

	/**
	 * @param equips the equips to set
	 */
	public void setEquips(Map<String, Equip> equips) {
		this.equips = equips;
	}

	/**
	 * @return the favoriteBooks
	 */
	public List<String> getFavoriteBooks() {
		return favoriteBooks;
	}

	/**
	 * @param favoriteBooks the favoriteBooks to set
	 */
	public void setFavoriteBooks(List<String> favoriteBooks) {
		this.favoriteBooks = favoriteBooks;
	}

	/**
	 * @return the favoriteDesserts
	 */
	public Map<String, String> getFavoriteDesserts() {
		return favoriteDesserts;
	}

	/**
	 * @param favoriteDesserts the favoriteDesserts to set
	 */
	public void setFavoriteDesserts(Map<String, String> favoriteDesserts) {
		this.favoriteDesserts = favoriteDesserts;
	}

	@Override
	public BasicDBObject toDBObject() {
		BasicDBObject dbObject = _id == null ? new BasicDBObject() : new BasicDBObject("_id", _id);
		dbObject.append("name", name);
		dbObject.append("exp", exp);
		dbObject.append("level", level);
		dbObject.append("favoriteBooks", favoriteBooks);
		dbObject.append("favoriteDesserts", favoriteDesserts);
		
		if(equips != null && equips.size() > 0) {
			Map<String, BasicDBObject> dbEquips = new HashMap<>();
			for (Map.Entry<String, Equip> personEntry : equips.entrySet()) {
				dbEquips.put(personEntry.getKey(), personEntry.getValue().toDBObject());
			}
			dbObject.append("equips", dbEquips);
		}
		
		return dbObject;
	}

	@Override
	public Player toPOJO(DBObject dbObject) {
		Map<String, Equip> equips = new HashMap<>();
		dbObject.get("equips");
		return new Player(((ObjectId) dbObject.get("_id")).toHexString(), (String) dbObject.get("name"),
				(long) dbObject.get("exp"), (int) dbObject.get("level"), (List<String>)dbObject.get("favoriteBooks"), (Map<String, String>)dbObject.get("favoriteDesserts"));
	}

}

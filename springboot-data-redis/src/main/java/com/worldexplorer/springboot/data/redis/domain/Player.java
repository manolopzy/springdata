package com.worldexplorer.springboot.data.redis.domain;

import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.RedisHash;
/**
 * 
 * {@link @RedisHash} + its corresponding repository
 * 
 * with {@link @RedisHash} annotation, spring data redis will 
 * create a hash map
 * @author tanku
 *
 */
/**
 * After 
 * hgetall player:-679812760
 1) "_class"
 2) "com.worldexplorer.springboot.data.redis.domain.Player"
 3) "exp"
 4) "122"
 5) "favoriteBooks.[0]"
 6) "I love you"
 7) "favoriteBooks.[1]"
 8) "Nowhere"
 9) "favoriteDesserts.[Elche]"
10) "Helados"
11) "favoriteDesserts.[Iceland]"
12) "Helados"
13) "id"
14) "-679812760"
15) "level"
16) "1"
17) "name"
18) "Maria"
19) "props.[-552943281].id"
20) "-552943281"
21) "props.[-552943281].name"
22) "name1291709982"
23) "props.[-552943281].propId"
24) "-1272041179"
25) "props.[1437227686].id"
26) "1437227686"
27) "props.[1437227686].name"
28) "name-766472455"
29) "props.[1437227686].propId"
30) "481476332"
 * @author tanku
 *
 */
@RedisHash("player")
public class Player{

	
	private int id;

	private String name;

	private long exp;

	private int level;

	private List<String> favoriteBooks;

	//key country value dessert name
	private Map<String, String> favoriteDesserts;
	
	private Map<Integer, Prop> props;

	
	public Player(int id, String name, long exp, int level) {
		this.id = id;
		this.name = name;
		this.exp = exp;
		this.level = level;
	}

	public Player(int id, String name, long exp, int level, List<String> favoriteBooks, Map<String, String> favoriteDesserts) {
		this.id = id;
		this.name = name;
		this.exp = exp;
		this.level = level;
		this.favoriteBooks = favoriteBooks;
		this.favoriteDesserts = favoriteDesserts;
	}

	public Player() {
		
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
	 * @return the props
	 */
	public Map<Integer, Prop> getProps() {
		return props;
	}

	/**
	 * @param props the props to set
	 */
	public void setProps(Map<Integer, Prop> props) {
		this.props = props;
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
	public String toString() {

		return "player: id = " + id + ";" + "name = " + name + ";" + "exp = " + exp + ";" + "level = " + level;
	}
}

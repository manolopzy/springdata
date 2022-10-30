package com.worldexplorer.springboot.data.redis.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.worldexplorer.springboot.data.redis.domain.RedisBaseObject;
import com.worldexplorer.springboot.data.redis.helper.SerializerUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPooled;

/**
 * classes and interfaces in the older version of Java are known as Legacy
 * classes. like {@link JedisPool}
 * 
 * 
 * @author tanku
 *
 */
public class JedisManager {
	// Connection pool configuration class, we
	// can set up the pool's max active
	// connections etc
	// It extends from GenericObjectPoolConfig<Jedis>
	private static JedisPoolConfig jedisPoolConfig;
	// The pool of connections to Redis servers
	private static JedisPool jedisPool;
	// The pool of connections to Redis servers
	private static JedisPooled jedisPooled = new JedisPooled();
	// The maximum active connections
	private static int maxActive = 100;
	// The maximum idle connections
	private static int maxIdle = 20;
	// The Redis server's IP
	private static String hostIp = "localhost";
	// The server's port
	private static int port = 6379;

	public static void initialization() {
		if (jedisPoolConfig == null) {
			jedisPoolConfig = new JedisPoolConfig();
			jedisPoolConfig.setMaxTotal(maxActive);
			jedisPoolConfig.setMaxIdle(maxIdle);
		}
		jedisPool = new JedisPool(jedisPoolConfig, hostIp, port);

	}

	public static Jedis getJedis() {
		return jedisPool.getResource();
	}

	/*
	 * 
	 * }
	 */
	/**
	 * Set the string value as value of the key. The string can't be longer than
	 * 1073741824 bytes (1 GB).
	 * 
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(jedis);
		}
		// or use jedisPooled
		// jedisPooled.set(key, value);
	}

	public static <T extends RedisBaseObject> void save(String key, T obj) {
		jedisPooled.hset(key.getBytes(), String.valueOf(obj.getId()).getBytes(), obj.serialize());
	}

	public static byte[] get(String key, long id) {
		return jedisPooled.hget(key.getBytes(), (id + "").getBytes());
	}

	public static void returnJedis(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}

	public static <T extends Serializable> void saveList(String key, List<T> objs) {
		if (objs == null) {
			return;
		}
		byte[][] byteDataList = new byte[objs.size()][];

		for (int i = 0; i < objs.size(); i++) {
			byte[] bytes = SerializerUtils.objectToBytes(objs.get(i));
			byteDataList[i] = bytes;
		}
		// insert a list of object bytes to redis list's head
		// if the list does not exist, create one and perform the operations
		jedisPooled.lpush(key.getBytes(), byteDataList);
		/**
		 * only perform the operations if the list exists
		 */
		// jedisPooled.lpushx(key.getBytes(), byteDataList);
	}

	public static <T extends Serializable> List<T> getList(String key) {

		// -1 means the last one
		List<byte[]> byteDataList = jedisPooled.lrange(key.getBytes(), 0, -1);
		List<T> objs = new ArrayList<>(byteDataList.size());

		for (int i = 0; i < byteDataList.size(); i++) {
			@SuppressWarnings("unchecked")
			T obj = (T) SerializerUtils.bytesToObject(byteDataList.get(i));
			objs.add(obj);
		}
		return objs;
	}

	public static <T extends Serializable> void saveListToJson(String key, List<T> objs) {
		if(objs == null) {
			return;
		}
		String[] jsonStrings = new String[objs.size()];
		for (int i = 0; i < objs.size(); i++) {
			jsonStrings[i] = SerializerUtils.objectToJson(objs.get(i));
			System.out.println("write json to redis : " + jsonStrings[i]);
		}
		jedisPooled.lpush(key, jsonStrings);
	}

	public static <T extends Serializable> List<T> getListFromJson(String key, Class<T> cls) {
		List<String> jsonList = jedisPooled.lrange(key, 0, -1);
		if(jsonList == null || jsonList.size() == 0) {
			return null;
		}
		List<T> objs = new ArrayList<>(jsonList.size());

		for (int i = 0; i < jsonList.size(); i++) {
			System.out.println("convert json to object : " + jsonList.get(i));
			T obj = SerializerUtils.jsonToObject(jsonList.get(i), cls);
			
			objs.add(obj);
		}
		return objs;
	}
}

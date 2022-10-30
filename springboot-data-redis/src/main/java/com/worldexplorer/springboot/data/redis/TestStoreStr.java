package com.worldexplorer.springboot.data.redis;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import redis.clients.jedis.Jedis;

/**
 * "sudo service redis-server start"
 * "redis-cli"
 * 
 * Connect to my local redis server using Jedis java driver Open windows cmd,
 * enter wsl to launch the windows subsystem linux then use 
 * "sudo service redis-server start" to start the server; 
 * and use "redis-cli" to enter the
 * client to interact with the server
 * 
 * Save a key-value pair then retrieve it from the database and other operations
 * 
 * The first important thing is to understand how to map data structures from
 * java programming language to Redis data structures.
 * 
 * En este caso, vamos a guardar cadenas en redis, una cadena puede 
 * ser de tipo textual, como por ejemplo, xml, json, html, texto, 
 * hasta valores enteros, reales. Tambien puede almacenar datos binarios 
 * como video, imagen, audio en forma de byte array; pero en cualquir caso, 
 * el tamano no puede superar o exceder 512mb.
 * 
 * la equivalencia de la estructura java de las cadenas guardada en redis:
 * ConcurrentMap<key, string value> strs
 * 
 * Todas las siguientes operaciones sobre las cadenas son atomicas:
 * set get mset mget incr incrby incrfloatby incrbyfloat etc.
 * @author tanku
 *
 */
public class TestStoreStr {

	private static final Jedis jedis = new Jedis();

	public static void main(String[] args) {

		//
		final String idGenerator = "ID_GENERATOR";
		// ---store Strings to redis test
		saveStr("msg1", "Hello, Redis server");
		saveStr("msg2", "How are you!");
		saveStr("msg3", "I love you.");
		System.out.println(jedis.get("msg1"));
		System.out.println(jedis.get("msg2"));
		System.out.println(jedis.get("msg3"));
		
		jedis.mset("msg1", "Hi", "msg2", "How do you do", "msg3", "where are you");
		System.out.println(jedis.mget("msg1", "msg2", "msg3").toString());
		jedis.set(idGenerator, "100");
		jedis.incr(idGenerator);
		jedis.incrBy(idGenerator, 12345);
		System.out.println(jedis.get(idGenerator));
	}

	public static void saveStr(String key, String value) {
		DatabaseInMemory.strs.put(key, value);
		/**
		 * corresponding to the redis client command: set key value
		 */
		jedis.set(key, value);
	}

	/**
	 * the corresponding java data structure
	 * 
	 * @author tanku
	 *
	 */
	private static final class DatabaseInMemory {

		//ConcurrentMap<key, value>
		protected static final ConcurrentMap<String, String> strs = new ConcurrentHashMap<>();
		
	}
}

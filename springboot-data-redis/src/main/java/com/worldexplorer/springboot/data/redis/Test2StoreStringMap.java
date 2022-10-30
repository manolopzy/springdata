package com.worldexplorer.springboot.data.redis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import redis.clients.jedis.Jedis;

public class Test2StoreStringMap {
	
	private static final Jedis jedis = new Jedis();

	public static void main(String[] args) {
		int[] userIds = new int[] { 123423, 21312312, 32432423, 213123, 324324 };
		Random random = new Random();
		// ----------Store a java hashmap of string to redis---------
		saveHashMap("userId" + random.nextInt(userIds.length), "equipId" + new Date().getTime(), "Greate equip 1");
		saveHashMap("userId" + random.nextInt(userIds.length), "equipId" + new Date().getTime(), "Greate equip 2");
		saveHashMap("userId" + random.nextInt(userIds.length), "equipId" + new Date().getTime(), "Greate equip 3");
		System.out.println(jedis.hgetAll("userId" + random.nextInt(userIds.length)));
	}

	private static void saveHashMap(String name, String key, String value) {
		if (DatabaseInMemory.stringMaps.get(name) != null) {
			DatabaseInMemory.stringMaps.get(name).put(key, value);
		} else {
			Map<String, String> map = new HashMap<>();
			map.put(key, value);
			DatabaseInMemory.stringMaps.put(key, map);
		}
		jedis.hset(name, key, value);
	}

	/**
	 * the corresponding java data structure
	 * 
	 * @author tanku 
	 *
	 */
	private static final class DatabaseInMemory {
		/**
		 * for example, in games, each user can have many friends, equips, props, emails
		 * Map<userId, Map<equipId, equip>>
		 */
		protected static final ConcurrentMap<String, Map<String, String>> stringMaps = 
				new ConcurrentHashMap<>();
		
	}
}

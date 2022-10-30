package com.worldexplorer.springboot.data.redis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.InitBinder;

import com.worldexplorer.springboot.data.redis.domain.RedisBaseObject;
import com.worldexplorer.springboot.data.redis.domain.Equip;

import redis.clients.jedis.Jedis;


public class Test3StoreObjectMap{
	
	private static final Jedis jedis = new Jedis();

	private static final String EQUIP_ID_GENERATOR_KEY = "EQUIP_ID";
	private static AtomicInteger equipIdGenerator;
	private static void initialization() {
		String lastEquipId = jedis.get(EQUIP_ID_GENERATOR_KEY);
		if(lastEquipId != null && lastEquipId != "") {
			equipIdGenerator = new AtomicInteger(Integer.valueOf(lastEquipId));
		}
		else {
			equipIdGenerator = new AtomicInteger(1);
		}
	}
	
	public static void main(String[] args) {
		
		initialization();
		
		int[] userIds = new int[] { 123423, 21312312, 32432423, 213123, 324324 };
		int[] equipIds = new int[] { 1234423, 213312312, 322432423, 21233123, 33424324 };
		
		Random random = new Random();
		// ----------Store a java hashmap of string to redis---------
		saveHashMap("userId" + random.nextInt(userIds.length), "equipId" + new Date().getTime(), new Equip(0, 0, "a", 0));
		saveHashMap("userId" + random.nextInt(userIds.length), "equipId" + new Date().getTime(), new Equip(0, 0, "a", 0));
		saveHashMap("userId" + random.nextInt(userIds.length), "equipId" + new Date().getTime(), new Equip(0, 0, "a", 0));
		System.out.println(jedis.hgetAll("userId" + random.nextInt(userIds.length)));
	}

	private static <T extends RedisBaseObject> void saveHashMap(String collectionKey, String key, T obj) {
		jedis.hset(collectionKey.getBytes(), key.getBytes(), obj.serialize());
	}

	/**
	 * the corresponding java data structure
	 * 
	 * @author tanku 
	 *
	 */
	private static final class DatabaseInMemory<T> {
		/**
		 * for example, in games, each user can have many friends, equips, props, emails
		 * Map<userId, Map<equipId, equip>>
		 */
		protected final ConcurrentMap<String, Map<String, T>> stringMaps = 
				new ConcurrentHashMap<>();
		
	}
}

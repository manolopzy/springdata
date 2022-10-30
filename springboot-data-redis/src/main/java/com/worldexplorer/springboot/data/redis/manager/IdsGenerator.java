package com.worldexplorer.springboot.data.redis.manager;

import redis.clients.jedis.Jedis;

/**
 * Store id generators in redis
 * @author tanku
 *
 */
public class IdsGenerator {
	
	//public static AtomicInteger userId;
	public static final int START_USER_ID = 100;
	public static final String USER_ID_STRING = "USER_ID_GENERATOR";

	public static final int START_EQUIP_ID = 100;
	public static final String EQUIP_ID_STRING = "EQUIP_ID_GENERATOR";

	public static final int START_SKILL_ID = 100;
	public static final String SKILL_ID_STRING = "SKILL_ID_GENERATOR";

	public static void initialization() {
		Jedis jedis = JedisManager.getJedis();
		
		if(!jedis.exists(USER_ID_STRING)){
			jedis.set(USER_ID_STRING, "" + START_USER_ID);
		}
		if(!jedis.exists(EQUIP_ID_STRING)){
			jedis.set(EQUIP_ID_STRING, "" + START_EQUIP_ID);
		}
		if(!jedis.exists(SKILL_ID_STRING)){
			jedis.set(SKILL_ID_STRING, "" + START_SKILL_ID);
		}
		/*String id = jedis.get(USER_ID_STRING);
		if (id == null) {
			userId = new AtomicInteger(START_USER_ID);
			jedis.set(USER_ID_STRING, "" + userId.get());
			jedis.set(USER_ID_STRING, "0");
		} else {
			userId = new AtomicInteger(Integer.valueOf(id));
		}*/
		
		//System.out.println(jedis.get(USER_ID_STRING));
		JedisManager.returnJedis(jedis);
	}

	public static long getAndIncrementUserId() {
		Jedis jedis = JedisManager.getJedis();
		long id = Long.valueOf(jedis.incr(USER_ID_STRING));
		JedisManager.returnJedis(jedis);
		/*
		 * int id = userId.getAndDecrement(); JEDIS.set(USER_ID_STRING, "" + id);
		 * JEDIS.incr(USER_ID_STRING);
		 */
		return id;
	}
	
	public static long getAndIncrementEquipId() {
		Jedis jedis = JedisManager.getJedis();
		long id = Long.valueOf(jedis.incr(EQUIP_ID_STRING));
		JedisManager.returnJedis(jedis);
		return id;
	}
	
	public static long getAndIncrementSkillId() {
		Jedis jedis = JedisManager.getJedis();
		long id = Long.valueOf(jedis.incr(SKILL_ID_STRING));
		JedisManager.returnJedis(jedis);
		return id;
	}
}

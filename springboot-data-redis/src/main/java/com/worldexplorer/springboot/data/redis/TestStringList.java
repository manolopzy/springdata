package com.worldexplorer.springboot.data.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class TestStringList {
	
	private static final Jedis jedis = new Jedis();
	
	public static void main(String[] args) {
		
		//rpush(key, String... strings)
		jedis.rpush("stories", "The forever death", "The dead body", "Dance with the death", "Dying in one's madness");
		//lrange(key, start, stop) -1 the last element -2 the second last element in the list
		System.out.println("stories: " + jedis.lrange("stories",0,-1));
		jedis.hset("sr_3", "authors", "Gary Mak, Danial Rubio, Josh Long, Marten Deinum");
		jedis.hset("sr_3", "published", "2014");
		jedis.hset("sr_4", "authors", "Josh Long, Marten Deinum");
		jedis.hset("sr_4", "published", "2017");
		System.out.println("Spring Recipes 3rd: " + jedis.hgetAll("sr_3"));
		System.out.println("Spring Recipes 4th: " + jedis.hgetAll("sr_4"));
	}
}

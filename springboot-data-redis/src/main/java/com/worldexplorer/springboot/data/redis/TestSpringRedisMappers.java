package com.worldexplorer.springboot.data.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;

import com.worldexplorer.springboot.data.redis.configuration.RedisStandaloneConfig;
import com.worldexplorer.springboot.data.redis.domain.City;
import com.worldexplorer.springboot.data.redis.domain.Country;


public class TestSpringRedisMappers {
	
	static String COUNTRIES_HASH_KEY_PREFIX = "COUNTRIES";
	static Random random = new Random();
	public static void main(String[] args) {
		
		System.out.println("Let's get started!");
		// specify the configuration class to the container
		// we can pass multiple configuration class to spring
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(RedisStandaloneConfig.class);
		
		@SuppressWarnings("unchecked")
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)context.getBean("redisTemplate");
		List<City> cities = new ArrayList<>();
		cities.add(new City("new york", "one of the biggest city in us"));
		cities.add(new City("chicago", "one of the biggest city in us"));
		Country country = new Country("us", "united states", 21232323, cities);
		
		//{@class=com.worldexplorer.springboot.data.redis.domain.Country, id=us, name=united states, population=21232323, cities=[java.util.ArrayList, [{@class=com.worldexplorer.springboot.data.redis.domain.City, name=new york, description=one of the biggest city in us}, {@class=com.worldexplorer.springboot.data.redis.domain.City, name=chicago, description=one of the biggest city in us}]]}
		//this map the domain object to json style key-value pairs
		HashMapper<Object, String, Object> toJson = new Jackson2HashMapper(false);
		
		System.out.println("obj to json = " + toJson.toHash(country).toString());
		
		//
		HashOperations<String, byte[], byte[]> hashOperations = redisTemplate.opsForHash();
		HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();
		
		
		//HashOperations<name of the hash, key, value>
		//corresponding to java data structure:
		//Map<key, value> name of the hash
		//map the object to a hash table, the field names will be keys, their values be values in Redis
		//this has the same effect as {@link RedisHash}
		//then write the hash map to Redis
		String key = COUNTRIES_HASH_KEY_PREFIX + country.getId();
		Map<byte[], byte[]> hs = mapper.toHash(country);
		hashOperations.putAll(key, hs);
		System.out.println("write country to redis = " + hs.size());
		//read all key-value pairs in the hash map with a specified key
		hs = hashOperations.entries(key);
		System.out.println("read country to redis = " + hs.size());
		country = (Country)mapper.fromHash(hs);
		System.out.println("retrieved country from redis = " + country.toString());
		
		
	}
}

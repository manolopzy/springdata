package com.worldexplorer.springboot.data.redis;

import java.util.Random;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import com.worldexplorer.springboot.data.redis.configuration.RedisStandaloneConfig;
import com.worldexplorer.springboot.data.redis.domain.FamousCity;

public class TestSortedSet {

	static final String SORTED_SET_KEY = "famouscities";
	static Random random = new Random();
	public static void main(String[] args) {
		
		System.out.println("Let's get started!");
		// specify the configuration class to the container
		// we can pass multiple configuration class to spring
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(RedisStandaloneConfig.class);
		
		@SuppressWarnings("unchecked")
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)context.getBean("redisTemplate");
		
		FamousCity famousCity = getFamousCity();
		ZSetOperations<String, Object> operations = redisTemplate.opsForZSet();
		operations.removeRange(SORTED_SET_KEY, 0, -1);
		boolean success = operations.add(SORTED_SET_KEY, famousCity, famousCity.getScore());
		System.out.println("success = " + success);
		famousCity = getFamousCity();
		success = operations.add(SORTED_SET_KEY, famousCity, famousCity.getScore());
		System.out.println("success = " + success);
		Set<Object> set = operations.range(SORTED_SET_KEY, 0, -1);
		
		for (Object object : set) {
			System.out.println("id = " + ((FamousCity)object).getId());
		}
		
		

	}
	private static FamousCity getFamousCity() {
		int score = random.nextInt();
		return new FamousCity(score, "spain city" + random.nextInt(), "north spain city" + random.nextInt());
	}
}

package com.worldexplorer.springboot.data.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.worldexplorer.springboot.data.redis.configuration.RedisClusterConfig;
import com.worldexplorer.springboot.data.redis.configuration.RedisClusterConfigurationProperties;

public class TestSpringRedisCluster {

	public static void main(String[] args) {

		System.out.println("Let's get started!");
		// specify the configuration class to the container
		// we can pass multiple configuration class to spring
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(RedisClusterConfig.class);

		@SuppressWarnings("unchecked")
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) context.getBean("redisTemplate");

		RedisConnectionFactory connectionFactory = context.getBean(RedisConnectionFactory.class);
		RedisClusterConnection clusterConnection = connectionFactory.getClusterConnection();
		// key value
		ClusterOperations<String, Object> clusterOperations = redisTemplate.opsForCluster();

	}
}

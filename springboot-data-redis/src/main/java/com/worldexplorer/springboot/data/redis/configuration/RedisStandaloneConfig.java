package com.worldexplorer.springboot.data.redis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisStandaloneConfig {
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		//if there is only one redis server (standalone)
		//used for setting up {@link RedisConnection} via {@link RedisConnectionFactory}
		RedisStandaloneConfiguration configuration = 
				new RedisStandaloneConfiguration();
		JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory(configuration);
	    return jedisConFactory;
	}

	/**
	 * the template is thread safe, can be reused across multiple instances
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
	
	/**
	 * Jedis For dealing with high-availability Redis, Spring Data Redis has support
	 * for Redis Sentinel
	 */
//	@Bean
//	public RedisConnectionFactory jedisConnectionFactory2() {
//	  RedisSentinelConfiguration sentinelConfig = 
//			  new RedisSentinelConfiguration()
//	  .master("master")
//	  .sentinel("127.0.0.1", 26379)
//	  .sentinel("127.0.0.1", 26380);
//	  return new JedisConnectionFactory(sentinelConfig);
//	}
}

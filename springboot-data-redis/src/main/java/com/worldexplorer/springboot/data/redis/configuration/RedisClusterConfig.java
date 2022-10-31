package com.worldexplorer.springboot.data.redis.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisClusterConfig {

	/**
     * Type safe representation of application.properties
     */
//    @Autowired
//    private RedisClusterConfigurationProperties clusterProperties;
	List<String> clusterNodes = Arrays.asList("127.0.0.1:23679", "127.0.0.1:23681", "127.0.0.1:23680");
	
	@Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory(
            new RedisClusterConfiguration(clusterNodes));
    }
	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
	    RedisTemplate<?, ?> template = new RedisTemplate<>();
	    template.setConnectionFactory(redisConnectionFactory());
	    return template;
	}
}

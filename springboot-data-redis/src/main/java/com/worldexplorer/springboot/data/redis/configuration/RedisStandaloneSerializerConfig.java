package com.worldexplorer.springboot.data.redis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.OxmSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
/**
 * In redis, the data is stored in the format of binary, it could represent
 * data of json, string, xml, or other formats depending on what kind of serializer we use
 * to convert java objects to that binary data
 * 
 * java objects -> specific serializer -> bytes array -> redis database
 * java objects <- specific serializer <- bytes array <- redis database
 * 
 * java objects -> json serializer -> json bytes array -> redis database
 * java objects <- json serializer <- json bytes array <- redis database
 * 
 * java objects -> jdk serializer -> bytes array -> redis database
 * java objects <- jdk serializer <- bytes array <- redis database
 * 
 * java objects -> xml serializer -> bytes array -> redis database
 * java objects <- xml serializer <- bytes array <- redis database
 * @author tanku
 *
 */
@Configuration
public class RedisStandaloneSerializerConfig {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		// if there is only one redis server (standalone)
		// used for setting up {@link RedisConnection} via {@link
		// RedisConnectionFactory}
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(configuration);
		return jedisConFactory;
	}

	/**
	 * the template is thread safe, can be reused across multiple instances
	 * 
	 * this template works with the default serializer
	 * {@link JdkSerializationRedisSerializer} for key, value, hash key, hash value
	 * 
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> jdkRedisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	/**
	 * the template is thread safe, can be reused across multiple instances
	 * 
	 * this template works with the default serializer {@link StringRedisSerializer}
	 * for key, value, hash key, hash value
	 * 
	 * @return
	 */
	@Bean
	public RedisTemplate<String, String> stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, String> stringTemplate = new RedisTemplate<>();
		stringTemplate.setConnectionFactory(redisConnectionFactory);
		stringTemplate.setDefaultSerializer(jsonRedisSerializer());
		return stringTemplate;
	}

	/**
	 * the template is thread safe, can be reused across multiple instances
	 * 
	 * this template works with the default serializer {@link StringRedisSerializer}
	 * for key, value, hash key, hash value
	 * String serializer for all keys
	 * json serializer for all values
	 * 
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> jsonRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> jsonRedisTemplate = new RedisTemplate<>();
		jsonRedisTemplate.setConnectionFactory(redisConnectionFactory);
		jsonRedisTemplate.setKeySerializer(stringRedisSerializer());
		jsonRedisTemplate.setValueSerializer(jsonRedisSerializer());
		jsonRedisTemplate.setHashKeySerializer(stringRedisSerializer());
		jsonRedisTemplate.setValueSerializer(jsonRedisSerializer());
		return jsonRedisTemplate;
	}
	/**
	 * String serializer for all keys
	 * xml serializer for all values
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> xmlRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> jsonRedisTemplate = new RedisTemplate<>();
		jsonRedisTemplate.setConnectionFactory(redisConnectionFactory);
		jsonRedisTemplate.setKeySerializer(stringRedisSerializer());
		jsonRedisTemplate.setValueSerializer(xmlRedisSerializer());
		jsonRedisTemplate.setHashKeySerializer(stringRedisSerializer());
		jsonRedisTemplate.setValueSerializer(xmlRedisSerializer());
		return jsonRedisTemplate;
	}

	/**
	 * UTF_8
	 * @return
	 */
	private StringRedisSerializer stringRedisSerializer() {
		return new StringRedisSerializer();
	}
	
	private OxmSerializer xmlRedisSerializer() {
		
		//Jaxb2Marshaller jm = new Jaxb2Marshaller();
		//return new OxmSerializer(jm, jm);
		XStreamMarshaller xsm = new XStreamMarshaller();
		return new OxmSerializer(xsm, xsm);
	}
	/**
	 * // Charset DEFAULT_CHARSET = StandardCharsets.UTF_8
	 * @return
	 */
	private Jackson2JsonRedisSerializer<Object> jsonRedisSerializer() {
		ObjectMapper objectMapper = new ObjectMapper();
		// ANY includes private and public fields
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		// specify the input type, or what types can be serialized, here, the class must
		// be no final
		objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
		// Serialize and deserialize objects from and to json bytes
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
				objectMapper, Object.class);
		return jackson2JsonRedisSerializer;
	}

}

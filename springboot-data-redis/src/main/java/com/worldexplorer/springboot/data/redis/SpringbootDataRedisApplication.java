package com.worldexplorer.springboot.data.redis;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.worldexplorer.springboot.data.redis.configuration.RedisStandaloneConfig;

import lombok.Data;

@Configuration
public class SpringbootDataRedisApplication {

//	@Bean
//	@Primary
//	RedisTemplate<String, Object> redisTemplate2(RedisConnectionFactory rcf) {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//		template.setConnectionFactory(rcf);
//		template.setKeySerializer(new StringRedisSerializer());
//		template.setValueSerializer(new JsonRedisSerializer());
//
//		return template;
//	}

	static class JsonRedisSerializer implements RedisSerializer<Object> {

		private final ObjectMapper om;

		public JsonRedisSerializer() {
			this.om = new ObjectMapper().enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
		}

		@Override
		public byte[] serialize(Object t) throws SerializationException {
			try {
				return om.writeValueAsBytes(t);
			} catch (JsonProcessingException e) {
				throw new SerializationException(e.getMessage(), e);
			}
		}

		@Override
		public Object deserialize(byte[] bytes) throws SerializationException {
			
			if(bytes == null){
				return null;
			}
			
			try {
				return om.readValue(bytes, Object.class);
			} catch (Exception e) {
				throw new SerializationException(e.getMessage(), e);
			}
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(RedisStandaloneConfig.class, SpringbootDataRedisApplication.class);
		RedisOperations<String, Object> redis = context.getBean(RedisOperations.class);
		
		EntityA a = new EntityA();
		System.out.println(a);
		redis.opsForValue().set("foo", a);
		
		EntityA aa = (EntityA)redis.opsForValue().get("foo");
		System.out.println(aa);
		
	}

	@Data
	private static class EntityA implements Serializable{
		
		long longProperty = System.currentTimeMillis();
		String stringProperty = "foo";
		EntityB entityB = new EntityB(3423);
	}

	@Data
	public static class EntityB implements Serializable{

		int intProperty = 42;

		//for redis serialization
		public EntityB() {}
		public EntityB(int i) {
			this.intProperty = i;
		}

		
	}
}
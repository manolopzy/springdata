package com.worldexplorer.springboot.data.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.worldexplorer.springboot.data.redis.configuration.RedisStandaloneSerializerConfig;
import com.worldexplorer.springboot.data.redis.domain.Author;
import com.worldexplorer.springboot.data.redis.domain.Book;
/**
 * In redis, the data is stored in the format of binary, it could represent
 * data of json, string, xml, or other type, we just need to specify the corresponding 
 * serializer to convert java objects into whatever type we want
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
 * @author tanku
 *
 */
public class TestSerializers {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(RedisStandaloneSerializerConfig.class);
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>)context.getBean("stringRedisTemplate");
		
		RedisTemplate<String, Object> jdkRedisTemplate = (RedisTemplate<String, Object>)context.getBean("jdkRedisTemplate");
		
		RedisTemplate<String, Object> jsonRedisTemplate = (RedisTemplate<String, Object>)context.getBean("jsonRedisTemplate");
		
		RedisTemplate<String, Object> xmlRedisTemplate = (RedisTemplate<String, Object>)context.getBean("xmlRedisTemplate");
		
		System.out.println(stringRedisTemplate);
		System.out.println(jdkRedisTemplate);
		System.out.println(jsonRedisTemplate);
		System.out.println(xmlRedisTemplate);
		
		Book book1 = 
				new Book("string serializer In Search of Lost Time", "Swann's Way, the first part of A la recherche de temps perdu, Marcel Proust's seven-part cycle, was published in 1913. In it, Proust introduces the themes that run through the entire work. ", new Author("Marcel Proust", "an interesting one"));
		Book book2 = 
				new Book("jdk serializer Ulysses by James Joyce", "Ulysses chronicles the passage of Leopold Bloom through Dublin during an ordinary day, June 16, 1904. The title parallels and alludes to Odysseus (Latinised into Ulysses), the hero of Homer's Odyss...", new Author("James Joyce", "an inspiring one"));
		Book book3 = 
				new Book("json serializer Don Quixote by Miguel de Cervantes", "Alonso Quixano, a retired country gentleman in his fifties, lives in an unnamed section of La Mancha with his niece and a housekeeper. He has become obsessed with books of chivalry, and believes th...", new Author("Miguel de Cervantes", "an amazing one"));
		Book book4 = 
				new Book("xml serializer One Hundred Years of Solitude by Gabriel Garcia Marquez", "One of the 20th century's enduring works, One Hundred Years of Solitude is a widely beloved and acclaimed novel known throughout the world, and the ultimate achievement in a Nobel Prize–winning car...", new Author("Gabriel Garcia Marquez", "an inspiring one"));
		
		stringRedisTemplate.opsForValue().set(book1.getName(), book1.getDescription());
		jdkRedisTemplate.opsForValue().set(book2.getName(), book2);
		jsonRedisTemplate.opsForValue().set(book3.getName(), book3);
		xmlRedisTemplate.opsForValue().set(book4.getName(), book4);
		
		
	}
}

package com.worldexplorer.springboot.data.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.worldexplorer.springboot.data.redis.configuration.RedisStandaloneConfig;
import com.worldexplorer.springboot.data.redis.domain.Player;
import com.worldexplorer.springboot.data.redis.domain.Prop;
import com.worldexplorer.springboot.data.redis.repository.PlayerRepository;

/**
 * Configuration Properties spring.redis.sentinel.master: name of the master
 * node.
 * 
 * spring.redis.sentinel.nodes: Comma delimited list of host:port pairs.
 * 
 * spring.redis.sentinel.username: The username to apply when authenticating
 * with Redis Sentinel (requires Redis 6)
 * 
 * spring.redis.sentinel.password: The password to apply when authenticating
 * with Redis Sentinel
 * 
 * @author tanku
 *
 */
/**
 * {@link @EnableRedisRepositories} annotation is necessary for spring 
 * to activate redis repositories
 * @author tanku
 *
 */
@Configuration
//@EnableRedisRepositories(basePackages = {"", ""})
@EnableRedisRepositories(basePackages = "com.worldexplorer.springboot.data.redis.repository")
@ComponentScan(basePackages = "com.worldexplorer.springboot.data.redis.repository")
public class TestRedisRepository {

	static Random random = new Random();
	public static void main(String[] args) {
		System.out.println("Let's get started!");
		// specify the configuration class to the container
		// we can pass multiple configuration class to spring
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(RedisStandaloneConfig.class, TestRedisRepository.class);
		//context.getBean("redisTemplate");
		PlayerRepository repository = context.getBean(PlayerRepository.class);

		// Player player = new Player(null, "Veronica", 122, 1);
		int playerId = random.nextInt();
		System.out.println("player id = " + playerId);
		Player player = new Player(playerId, "Maria", 122, 1);

		List<String> favoriteBooks = new ArrayList<>();
		favoriteBooks.add("I love you");
		favoriteBooks.add("Nowhere");
		// key place value dessert name
		Map<String, String> favoriteDesserts = new HashMap<>();
		favoriteDesserts.put("Elche", "Helados");
		favoriteDesserts.put("Iceland", "Helados");
		player.setFavoriteBooks(favoriteBooks);

		player.setFavoriteDesserts(favoriteDesserts);

		Map<Integer, Prop> props = new HashMap<>();
		Prop prop = new Prop(random.nextInt(), random.nextInt(), "name" + random.nextInt());
		props.put(prop.getId(), prop);
		prop = new Prop(random.nextInt(), random.nextInt(), "name" + random.nextInt());
		props.put(prop.getId(), prop);
		player.setProps(props);

		repository.save(player);
		Optional<Player> result = repository.findById(playerId);
		
		if(result.get() != null) {
			System.out.println(result.toString());
			player = result.get();
			props = player.getProps();
			System.out.println(props.toString());
		}
	}
}

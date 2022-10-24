package com.worldexplorer.springbootdatamongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import com.worldexplorer.springbootdatamongodb.domain.Equip;
import com.worldexplorer.springbootdatamongodb.domain.Player;
import com.worldexplorer.springbootdatamongodb.repository.PlayerRepository;
/**
 * In this test, we use {@link com.mongodb.BasicDBObject} class which 
 * belongs to java mongodb driver, to map java objects to mongodb 
 * documents.
 * 
 * @author tanku
 *
 */
@Configuration
public class Test3 {
	
	public static final String DB_NAME = "test";

	/*
	 * @Bean public com.mongodb.client.MongoClient mongo() { return
	 * MongoClients.create(); }
	 */

	@Bean
	public PlayerRepository playerRepository() {
		return new PlayerRepository(DB_NAME);
	}
	
	public static void main(String[] args) {
		
		
		ApplicationContext ctx =
				new AnnotationConfigApplicationContext(Test3.class);
		PlayerRepository repository = ctx.getBean(PlayerRepository.class);
		
		
		//Player player = new Player(null, "Veronica", 122, 1);
		Player player = new Player(null, "Maria", 122, 1);
		
		List<String> favoriteBooks = new ArrayList<>();
		favoriteBooks.add("I love you");
		favoriteBooks.add("Nowhere");
		//key place value dessert name
		Map<String, String> favoriteDesserts = new HashMap<>();
		favoriteDesserts.put("Elche", "Helados");
		favoriteDesserts.put("Iceland", "Helados");
		player.setFavoriteBooks(favoriteBooks);
		
		player.setFavoriteDesserts(favoriteDesserts);
		
		Random random = new Random();
		Map<String, Equip> equips = new HashMap<>();
		Equip equip = new Equip(random.nextInt(), random.nextInt(), "name" + random.nextInt(), random.nextInt() );
		equips.put("" + equip.getId(), equip);
		equip = new Equip(random.nextInt(), random.nextInt(), "name" + random.nextInt(), random.nextInt() );
		equips.put("" + equip.getId(), equip);
		player.setEquips(equips);
		
		repository.save(player);
		
		((AbstractApplicationContext) ctx).close();
	}
}

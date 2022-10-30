package com.worldexplorer.springboot.data.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.worldexplorer.springboot.data.redis.configuration.RedisStandaloneConfig;
import com.worldexplorer.springboot.data.redis.domain.FamousPlace;
import com.worldexplorer.springboot.data.redis.domain.Mayor;
import com.worldexplorer.springboot.data.redis.domain.Person;
import com.worldexplorer.springboot.data.redis.domain.Town;

/**
 * The entities work with RedisTemplate must be serializable (implement Serializable interface)
 * @author tanku
 *
 */
public class TestRedisTemplate {
	
	
	static String TOWNS_HASH_KEY = "famous_towns";
	static Random random = new Random();
	public static void main(String[] args) {
		
		System.out.println("Let's get started!");
		// specify the configuration class to the container
		// we can pass multiple configuration class to spring
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(RedisStandaloneConfig.class);
		
		@SuppressWarnings("unchecked")
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)context.getBean("redisTemplate");
		
		int townId = random.nextInt();
		Town town = new Town();
		town.setId(townId);
		town.setName("Malaga");
		town.setPopulation(577405);
		List<String> famousFor = new ArrayList<>(4);
		famousFor.add("el parque de malaga");
		famousFor.add("plaza de toros de La Malagueta");
		famousFor.add("El palacio de Altamira");
		town.setFamousFor(famousFor);
		
		List<FamousPlace> famousPlaces = new ArrayList<>(4);
		famousPlaces.add(new FamousPlace());
		famousPlaces.add(new FamousPlace());
		town.setFamousPlaces(famousPlaces);
		
		Map<String, Person> famousPersons = new HashMap<>();
		famousPersons.put("PABLO PICASSO", new Person("PABLO PICASSO", "Figura excepcional como artista y como hombre, Picasso fue protagonista y creador inimitable de las diversas corrientes que revolucionaron las artes plásticas del siglo XX, desde el cubismo hasta la escultura neofigurativa, del grabado o el aguafuerte a la cerámica artesanal o a la escenografía para ballets. Su obra inmensa en número, en variedad y en talento se extiende a lo largo de más de setenta y cinco años de actividad creadora, que el pintor compaginó sabiamente con el amor, la política, la amistad y un exultante y contagioso goce de la vida. Famoso desde la juventud, admirado y solicitado por los célebres y poderosos, fue esencialmente un malageño sencillo, saludable y generoso, dotado de una formidable capacidad de trabajo, enamorado de los barrios bohemios de París, del sol del Mediterráneo, de los toros, de la gente sencilla y de las mujeres hermosas, afición que cultivó sin desmayo."));
		famousPersons.put("ANTONIO MOLINA", new Person("ANTONIO MOLINA", "Intérprete de canción española. De origen humilde, a los catorce años se trasladó a Madrid para probar fortuna. El éxito le llegó a principios de la década de los cincuenta, con canciones como Soy minero, Caballito bandolero, La estudiantina, Adiós mi España y Yo quiero ser matador. "));
		town.setFamousPersons(famousPersons);
		
		Map<String, String> otherStuff = new HashMap<>();
		otherStuff.put("hello", "world");
		otherStuff.put("hola", "mundo");
		town.setOtherStuff(otherStuff);
		
		Mayor mayor = new Mayor("Andrea", "Partido popular");
		town.setMayor(mayor);

		redisTemplate.opsForHash().put(TOWNS_HASH_KEY, town.getId(), town);
		
		redisTemplate.opsForCluster();
		redisTemplate.opsForList();
		redisTemplate.opsForSet();
		redisTemplate.opsForZSet();
		redisTemplate.opsForHash();
		redisTemplate.opsForStream();
		redisTemplate.opsForValue();
		redisTemplate.opsForHyperLogLog();
		redisTemplate.opsForGeo();
		Object result = redisTemplate.opsForHash().get(TOWNS_HASH_KEY, town.getId());
		if(result != null) {
			System.out.println("result = " + result.toString());
			town = (Town)result;
			famousPersons = town.getFamousPersons();
			System.out.println("famousPersons = " + famousPersons.toString());
		}
		//if we want to use the underlying raw connection to talk with Redis
//		redisTemplate.execute(new RedisCallback<Object>() {
//			@Override
//			public Object doInRedis(RedisConnection connection) throws DataAccessException {
//				//((StringRedisTemplate)connection).opsForList().
//				return null;
//			}
//		});
		
//		List<Town> towns = repository.findAll();
//		for (Town resultTown : towns) {
//			System.out.println("id = " + resultTown.get_id());
//			System.out.println("name = " + resultTown.getName());
//		}
	}
}

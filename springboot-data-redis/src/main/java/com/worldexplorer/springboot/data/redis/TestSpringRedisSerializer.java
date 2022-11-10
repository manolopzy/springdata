package com.worldexplorer.springboot.data.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.worldexplorer.springboot.data.redis.configuration.RedisStandaloneConfig;
import com.worldexplorer.springboot.data.redis.domain.FamousPlace;
import com.worldexplorer.springboot.data.redis.domain.Mayor;
import com.worldexplorer.springboot.data.redis.domain.Person;
import com.worldexplorer.springboot.data.redis.domain.Town;
/**
 * 127.0.0.1:6379> keys *
1) "\xac\xed\x00\x05t\x00\x0cfamous_towns"
2) "arithmetic_gamestats"
3) "arithmetic_gamestats:-5461129489864155017"
127.0.0.1:6379> hgetall "\xac\xed\x00\x05t\x00\x0cfamous_towns"
1) "\xac\xed\x00\x05sr\x00\x11java.lang.Integer\x12\xe2\xa0\xa4\xf7\x81\x878\x02\x00\x01I\x00\x05valuexr\x00\x10java.lang.Number\x86\xac\x95\x1d\x0b\x94\xe0\x8b\x02\x00\x00xp\xfe\x82\xad\xeb"
2) "\xac\xed\x00\x05sr\x003com.worldexplorer.springboot.data.redis.domain.Town\x00\x00\x00\x00\x00\x00\x00\x01\x02\x00\bI\x00\x02idI\x00\npopulationL\x00\tfamousFort\x00\x10Ljava/util/List;L\x00\rfamousPersonst\x00\x0fLjava/util/Map;L\x00\x0cfamousPlacesq\x00~\x00\x01L\x00\x05mayort\x006Lcom/worldexplorer/springboot/data/redis/domain/Mayor;L\x00\x04namet\x00\x12Ljava/lang/String;L\x00\notherStuffq\x00~\x00\x02xp\xfe\x82\xad\xeb\x00\b\xcf}sr\x00\x13java.util.ArrayListx\x81\xd2\x1d\x99\xc7a\x9d\x03\x00\x01I\x00\x04sizexp\x00\x00\x00\x03w\x04\x00\x00\x00\x03t\x00\x13el parque de malagat\x00\x1eplaza de toros de La Malaguetat\x00\x16El palacio de Altamiraxsr\x00\x11java.util.HashMap\x05\a\xda\xc1\xc3\x16`\xd1\x03\x00\x02F\x00\nloadFactorI\x00\tthresholdxp?@\x00\x00\x00\x00\x00\x0cw\b\x00\x00\x00\x10\x00\x00\x00\x02t\x00\x0eANTONIO MOLINAsr\x005com.worldexplorer.springboot.data.redis.domain.Person\x00\x00\x00\x00\x00\x00\x00\x01\x02\x00\x02L\x00\x0bdescriptionq\x00~\x00\x04L\x00\x04nameq\x00~\x00\x04xpt\x01&Int\xc3\xa9rprete de canci\xc3\xb3n espa\xc3\xb1ola. De origen humilde, a los catorce a\xc3\xb1os se traslad\xc3\xb3 a Madrid para probar fortuna. El \xc3\xa9xito le lleg\xc3\xb3 a principios de la d\xc3\xa9cada de los cincuenta, con canciones como Soy minero, Caballito bandolero, La estudiantina, Adi\xc3\xb3s mi Espa\xc3\xb1a y Yo quiero ser matador. q\x00~\x00\rt\x00\rPABLO PICASSOsq\x00~\x00\x0et\x03\x99Figura excepcional como artista y como hombre, Picasso fue protagonista y creador inimitable de las diversas corrientes que revolucionaron las artes pl\xc3\xa1sticas del siglo XX, desde el cubismo hasta la escultura neofigurativa, del grabado o el aguafuerte a la cer\xc3\xa1mica artesanal o a la escenograf\xc3\xada para ballets. Su obra inmensa en n\xc3\xbamero, en variedad y en talento se extiende a lo largo de m\xc3\xa1s de setenta y cinco a\xc3\xb1os de actividad creadora, que el pintor compagin\xc3\xb3 sabiamente con el amor, la pol\xc3\xadtica, la amistad y un exultante y contagioso goce de la vida. Famoso desde la juventud, admirado y solicitado por los c\xc3\xa9lebres y poderosos, fue esencialmente un malage\xc3\xb1o sencillo, saludable y generoso, dotado de una formidable capacidad de trabajo, enamorado de los barrios bohemios de Par\xc3\xads, del sol del Mediterr\xc3\xa1neo, de los toros, de la gente sencilla y de las mujeres hermosas, afici\xc3\xb3n que cultiv\xc3\xb3 sin desmayo.q\x00~\x00\x11xsq\x00~\x00\x06\x00\x00\x00\x02w\x04\x00\x00\x00\x02sr\x00:com.worldexplorer.springboot.data.redis.domain.FamousPlace\x00\x00\x00\x00\x00\x00\x00\x01\x02\x00\x02L\x00\aaddressq\x00~\x00\x04L\x00\x04nameq\x00~\x00\x04xpt\x00/Passeig de l'Estaci\xc3\xb3, s/n, 03202 Elx, Alicantet\x00\x10Parque palmeras sq\x00~\x00\x15q\x00~\x00\x17q\x00~\x00\x18xsr\x004com.worldexplorer.springboot.data.redis.domain.Mayor\x00\x00\x00\x00\x00\x00\x00\x01\x02\x00\x02L\x00\x04nameq\x00~\x00\x04L\x00\x05partyq\x00~\x00\x04xpt\x00\x06Andreat\x00\x0fPartido populart\x00\x06Malagasq\x00~\x00\x0b?@\x00\x00\x00\x00\x00\x0cw\b\x00\x00\x00\x10\x00\x00\x00\x02t\x00\x05hellot\x00\x05worldt\x00\x04holat\x00\x05mundox"
 * @author tanku
 *
 */
@Configuration
public class TestSpringRedisSerializer {
	
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
	
	static String TOWNS_HASH_KEY = "famous_towns";
	static Random random = new Random();
	public static void main(String[] args) {
		
		System.out.println("Let's get started!");
		// specify the configuration class to the container
		// we can pass multiple configuration class to spring
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(TestSpringRedisSerializer.class);
		
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

package com.worldexplorer.springbootdatamongodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.worldexplorer.springbootdatamongodb.domain.FamousPlace;
import com.worldexplorer.springbootdatamongodb.domain.Mayor;
import com.worldexplorer.springbootdatamongodb.domain.Person;
import com.worldexplorer.springbootdatamongodb.domain.Town;
import com.worldexplorer.springbootdatamongodb.repository.TownRepository;
/**
 * In this test, we are going to save a nested java object {@link Town} 
 * to mongodb.
 * {@link Town} contains fields of string, int, lists, maps
 * We use {@link org.bson.Document} class to represent a document in 
 * the document oriented mongo database, which contains a map to 
 * store data in the form of "key-vale" pairs. The value of a pair 
 * could be string, boolean, int, .., {@link List<String>}, 
 * map of string {@link Map<String, String>}, {@link org.bson.Document}.
 * 
 *  
 * @author tanku
 *
 */
public class Test1 {

	/*
	 * private static final String HOST = "localhost"; 
	 * private static final String PORT = "27017";
	 */
    
	public static void main(String[] args) {
		
		/**
		 * {@link MongoClients} is a factory for {@link MongoClient} instances.  
		 * Use of this class is now the recommended way to connect to 
		 * MongoDB via the Java driver.
		 */
		MongoClient mongo = MongoClients.create();
		
		TownRepository repository = new TownRepository(mongo, "test", "towns");
		System.out.println("Number of towns: " + repository.count());
		
		Town town = new Town();
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
		
		repository.save(town);
		
		System.out.println("Number of towns: " + repository.count());
		
		List<Town> towns = repository.findAll();
		for (Town resultTown : towns) {
			System.out.println("id = " + resultTown.get_id());
			System.out.println("name = " + resultTown.getName());
		}
	}
}

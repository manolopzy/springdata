package com.worldexplorer.springboot.data.redis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.worldexplorer.springboot.data.redis.domain.Vehicle;
import com.worldexplorer.springboot.data.redis.manager.IdsGenerator;
import com.worldexplorer.springboot.data.redis.manager.JedisManager;

public class TestStoreObjectList {
	static Random random = new Random();
	
	public static void main(String[] args) {
    	initialization();
		
    	List<Vehicle> vehicles = new ArrayList<>();
    	vehicles.add(new Vehicle());
    	vehicles.add(new Vehicle());
    	JedisManager.saveList("vehicleList", vehicles);
    	vehicles = JedisManager.getList("vehicleList");
    	for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle.toString());
		}
    	DatabaseInMemory.lpush("vehicleList", vehicles);
    	
	}

	private static void initialization() {
		JedisManager.initialization();
    	IdsGenerator.initialization();
	}
	
	/**
	 * the corresponding java data structure
	 * 
	 * @author tanku
	 *
	 */
	private static final class DatabaseInMemory {

		//ConcurrentMap<key, value>
		protected static final ConcurrentMap<String, CopyOnWriteArrayList<Object>> lists = new ConcurrentHashMap<>();

		public static <T extends Serializable> void lpush(String key, List<T> objs) {
			if(lists.containsKey(key)) {
				lists.get("key").addAll(objs);
			}
			else {
				CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<Object>();
				lists.put(key, list);
				list.addAll(objs);
			}
		}
		
	}
}

package com.worldexplorer.springboot.data.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.worldexplorer.springboot.data.redis.domain.Vehicle;
import com.worldexplorer.springboot.data.redis.manager.IdsGenerator;
import com.worldexplorer.springboot.data.redis.manager.JedisManager;

public class TestStoreJson {
static Random random = new Random();
	static String key = "vehiclesJson";
	public static void main(String[] args) {
    	initialization();
		
    	List<Vehicle> vehicles = new ArrayList<>();
    	vehicles.add(new Vehicle());
    	vehicles.add(new Vehicle());
    	JedisManager.saveListToJson(key, vehicles);
    	vehicles = JedisManager.getListFromJson(key, Vehicle.class);
    	for (Vehicle vehicle : vehicles) {
			System.out.println(vehicle.toString());
		}
    	
    	
    	
	}

	private static void initialization() {
		JedisManager.initialization();
    	IdsGenerator.initialization();
	}
}

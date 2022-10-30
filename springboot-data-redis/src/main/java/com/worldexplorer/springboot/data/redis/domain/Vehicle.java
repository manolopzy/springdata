package com.worldexplorer.springboot.data.redis.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.PersistenceCreator;

public class Vehicle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String vehicleNo = "number one";
	private String color = "blue";
	private int wheel = 6;
	private int seat = 28;
	
	private List<String> functions;

	private Map<String, String> otherStuff;
	
	private List<Producer> producers;
	public Vehicle() {
		functions = new ArrayList<>();
		otherStuff = new HashMap<>();
		producers = new ArrayList<>();
		functions.add("fly");
		functions.add("swim");
		functions.add("diving");
		otherStuff.put(vehicleNo, color);
		otherStuff.put(vehicleNo, color);
		producers.add(new Producer());
		producers.add(new Producer());
	}
	public Vehicle(String vehicleNo, String color, int wheel, int seat) {
		this.vehicleNo = vehicleNo;
		this.color = color;
		this.wheel = wheel;
		this.seat = seat;
	}
	@PersistenceCreator
	public Vehicle(String vehicleNo, String color, int wheel, int seat, List<Producer> producers) {
		this.vehicleNo = vehicleNo;
		this.color = color;
		this.wheel = wheel;
		this.seat = seat;
		this.producers = producers;
	}
	/**
	 * @return the vehicleNo
	 */
	public String getVehicleNo() {
		return vehicleNo;
	}
	/**
	 * @param vehicleNo the vehicleNo to set
	 */
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the wheel
	 */
	public int getWheel() {
		return wheel;
	}
	/**
	 * @param wheel the wheel to set
	 */
	public void setWheel(int wheel) {
		this.wheel = wheel;
	}
	/**
	 * @return the seat
	 */
	public int getSeat() {
		return seat;
	}
	/**
	 * @param seat the seat to set
	 */
	public void setSeat(int seat) {
		this.seat = seat;
	}
	/**
	 * @return the producers
	 */
	public List<Producer> getProducers() {
		return producers;
	}
	/**
	 * @param producers the producers to set
	 */
	public void setProducers(List<Producer> producers) {
		this.producers = producers;
	}
	/**
	 * @return the functions
	 */
	public List<String> getFunctions() {
		return functions;
	}
	/**
	 * @param functions the functions to set
	 */
	public void setFunctions(List<String> functions) {
		this.functions = functions;
	}
	/**
	 * @return the otherStuff
	 */
	public Map<String, String> getOtherStuff() {
		return otherStuff;
	}
	/**
	 * @param otherStuff the otherStuff to set
	 */
	public void setOtherStuff(Map<String, String> otherStuff) {
		this.otherStuff = otherStuff;
	}
	
	@Override
	public String toString() {
		
		return "vehicle : " + 
		"vehicleNo = " + vehicleNo + "," + 
				"color = " + color + "," + 
						"wheel = " + wheel + "," + 
								"seat = " + seat + "," + 
										"functions = " + functions.toString() + "," +
												"otherStuff = " + otherStuff.toString() + "," +
														"producers = " + producers.toString();
	}
}

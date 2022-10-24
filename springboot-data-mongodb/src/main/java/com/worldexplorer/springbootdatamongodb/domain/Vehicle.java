package com.worldexplorer.springbootdatamongodb.domain;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.PersistenceCreator;

public class Vehicle {

	private String vehicleNo;
	private String color;
	private int wheel;
	private int seat;
	
	private List<String> functions;

	private Map<String, String> otherStuff;
	
	private List<Producer> producers;
	
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
	
	
}

package com.routing.servlet;

public class RoutingEntries {
	
	private Integer id;
	private String filename;
	private String bussys;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRestName() {
		return filename;
	}
	public void setRestName(String restName) {
		this.filename = restName;
	}
	public String getCityName() {
		return bussys;
	}
	public void setCityName(String cityName) {
		this.bussys = cityName;
	} 
	
	

}

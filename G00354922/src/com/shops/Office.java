package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Office {
	//	Variable declaration
	private int id;
	private String location;
	
	//	Getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}

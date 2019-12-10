package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product extends Store{
	//	Variable declaration
	private int sid;
	private String name;
	private String founded;
	private int prodId;
	private String prodName;
	private double price;
	
	//	Getter and Setter generation
	public int getSid() {
		return sid;
	}
	
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFounded() {
		return founded;
	}
	
	public void setFounded(String founded) {
		this.founded = founded;
	}
	
	public int getProdId() {
		return prodId;
	}
	
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	
	public String getProdName() {
		return prodName;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}	
}
package com.shops;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product extends Store{
//	Variable declaration
	private int sid;
	private int prodId;
	private String prodName;
	private double price;
	
//	Getters and Setters
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
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
package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class OfficeController {
	//	Variable declaration
	MongoDAO mongo;
	ArrayList<Office> offices;
	
	//	Constructor
	public OfficeController() {
		super();
		
		try {
			mongo = new MongoDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//	Load offices method
	public void loadOffice() {
		System.out.println("Loading office...");
		try {
			offices = mongo.loadOffices();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	//	Get offices method
	public ArrayList<Office> getOffice() {
		return offices;
	}
	
	//	Add offices method
	public String addOffice(Office o) {
		System.out.println("In addOffice()");
		try {
			mongo.addOffice(o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
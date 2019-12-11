package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class OfficeController {
	//	variable declaration
	MongoDAO mongo;
	ArrayList<Office> offices;
	
	//	constructor
	public OfficeController() {
		super();
		
		try {
			mongo = new MongoDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadOffice() {
		System.out.println("Loading office...");
		try {
			offices = mongo.loadOffices();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public ArrayList<Office> getOffice() {
		return offices;
	}

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

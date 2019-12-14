package com.shops;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

//	For more recent sql connector version 8.0+
//import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

@ManagedBean
@SessionScoped
public class StoreProductController {
	//	Variable declaration
	DAO dao;
	ArrayList<StoreProduct> storeProducts;
	
	//	Constructor
	public StoreProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//	Load Store Products method
	public void loadStoreProducts(int i) {
		System.out.println("In loadProducts()");
		try {
			storeProducts = dao.loadStoreProducts(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//	Add Store Product method
	public String addStoreProduct(StoreProduct sp) {
		System.out.println(sp.getSid() + " " + sp.getName() + " " + sp.getFounded() + " " + sp.getProdId() + " " + sp.getProdName() + " " + sp.getPrice());
		
				try {
					dao.addStoreProduct(sp);
					return "index";
				} catch (SQLIntegrityConstraintViolationException e) {
						FacesMessage message = 
							new FacesMessage("Error: Product ID already exists");
							FacesContext.getCurrentInstance().addMessage(null, message);
				} catch (CommunicationsException e) {
						FacesMessage message = 
							new FacesMessage("Error: Can't communicate with Database");
							FacesContext.getCurrentInstance().addMessage(null, message);
				}catch (Exception e) {
						FacesMessage message = 
							new FacesMessage("Error: " + e.getMessage());
							FacesContext.getCurrentInstance().addMessage(null, message);
					e.printStackTrace();
				}
				return null;
	}
	
	//	Get store products method
	public ArrayList<StoreProduct> getStoreProducts() {
		return storeProducts;
	}
	
}
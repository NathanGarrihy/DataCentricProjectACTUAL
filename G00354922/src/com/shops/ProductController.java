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
public class ProductController {
	//	Variables
	DAO dao;
	ArrayList<Product> products;
	
	//	Constructor
	public ProductController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//	Load products method
	public void loadProducts() {
		System.out.println("In loadProducts()");
		try {
			products = dao.loadProducts();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//	Add products method
	public String addProducts(Product p) {
		System.out.println(p.getSid() + " " + p.getName() + " " + p.getFounded() + " " + p.getProdId() + " " + p.getProdName() + " " + p.getPrice());
		
				try {
					dao.addProduct(p);
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
	
	//	Get products method
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	//	Delete products method
	public void deleteProduct(Product p) {
		try {
			dao.deleteProduct(p.getProdId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
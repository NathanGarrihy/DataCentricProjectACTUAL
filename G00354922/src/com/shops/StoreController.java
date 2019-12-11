package com.shops;

import java.sql.SQLException;
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
public class StoreController {
	DAO dao;
	ArrayList<Store> stores;
	
	public StoreController() {
		super();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadStores() {
		System.out.println("In loadStore()");
		try {
			stores = dao.loadStores();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addStore(Store s) {
		System.out.println(s.getId() + " " + s.getName() + " " + s.getFounded());
		
				try {
					dao.addStore(s);
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

	public ArrayList<Store> getStores() {
		return stores;
	}
	
	public void deleteStore(Store s) {
		try {
			dao.deleteStore(s.getId());
		} catch (SQLIntegrityConstraintViolationException e) {
			FacesMessage msg = new FacesMessage(s.getName() + " cannot be deleted from the database. Reason: It contains products.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
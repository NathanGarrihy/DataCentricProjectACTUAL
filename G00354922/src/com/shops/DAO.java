package com.shops;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {

private DataSource mysqlDS;

// 	Constructor
public DAO() throws Exception {
	Context context = new InitialContext();
	String jndiName = "java:comp/env/shops";
	mysqlDS = (DataSource) context.lookup(jndiName);
}

//	Load stores method
public ArrayList<Store> loadStores() throws Exception {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		conn = mysqlDS.getConnection();
		
		String sql = "select * from store";
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery(sql);
		
		ArrayList<Store> stores = new ArrayList<Store>();
		
		//	Processing of result set
		while(rs.next()) {
			Store s = new Store();
			s.setId(rs.getInt("id"));
			s.setName(rs.getString("name"));
			s.setFounded(rs.getString("founded"));
			stores.add(s);
		}
		return stores;
}

//	Load products method
public ArrayList<Product> loadProducts() throws Exception {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	conn = mysqlDS.getConnection();
	
	String sql = "select * from product";
	
	stmt = conn.createStatement();
	
	rs = stmt.executeQuery(sql);
	
	ArrayList<Product> products = new ArrayList<Product>();
	
	//	Processing of result set
	while(rs.next()) {
		Product p = new Product();
		
		p.setProdId(rs.getInt("pid"));
		p.setSid(rs.getInt("sid"));
		p.setProdName(rs.getString("prodName"));
		p.setPrice(rs.getDouble("price"));
		products.add(p);
	}
	return products;
}

//	Load StoreProducts method
public ArrayList<StoreProduct> loadStoreProducts(int i) throws Exception {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	conn = mysqlDS.getConnection();
	
	String sql = "select p.pid, p.prodName, p.price, s.name, s.founded, s.id from product p inner join store s on p.sid = s.id where p.sid like " + i;
	
	stmt = conn.createStatement();
	
	rs = stmt.executeQuery(sql);
	
	ArrayList<StoreProduct> storeProducts = new ArrayList<StoreProduct>();
	
	//	Processing of result set
	while(rs.next()) {
		StoreProduct sp = new StoreProduct();
		
		sp.setName(rs.getString("name"));
		sp.setFounded(rs.getString("founded"));
		sp.setProdId(rs.getInt("pid"));
		sp.setId(rs.getInt("id"));
		sp.setProdName(rs.getString("prodName"));
		sp.setPrice(rs.getDouble("price"));
		storeProducts.add(sp);
	}
	return storeProducts;
}

//	Add Store method
public void addStore(Store store) throws Exception {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	
	conn = mysqlDS.getConnection();
	
	String sql = "insert into store values (?, ?, ?)";
	
	stmt = conn.prepareStatement(sql);
	
	stmt.setInt(1, store.getId());
	stmt.setString(2,  store.getName());
	stmt.setString(3, store.getFounded());
	stmt.execute();	
}

//	Add product method
public void addProduct(Product product) throws Exception {

Connection conn = null;
PreparedStatement stmt = null;

conn = mysqlDS.getConnection();

String sql = "insert into product values (?, ?, ?, ?)";

stmt = conn.prepareStatement(sql);


stmt.setInt(1, product.getProdId());
stmt.setInt(2, product.getSid());
stmt.setString(3, product.getProdName());
stmt.setDouble(4, product.getPrice());
stmt.execute();
}

//	Add StoreProduct Method
public void addStoreProduct(StoreProduct storeProduct) throws Exception {

Connection conn = null;
PreparedStatement stmt = null;

conn = mysqlDS.getConnection();

String sql = "insert into product values (?, ?, ?, ?, ?, ?)";

stmt = conn.prepareStatement(sql);

stmt.setInt(1, storeProduct.getId());
stmt.setString(2,  storeProduct.getName());
stmt.setString(3, storeProduct.getFounded());
stmt.setInt(4, storeProduct.getProdId());
stmt.setString(5, storeProduct.getProdName());
stmt.setDouble(6, storeProduct.getPrice());
stmt.execute();
}

//	Delete store method
public void deleteStore(int i) throws SQLException {
	Connection conn = null;
	PreparedStatement stmt = null;
	
	conn = mysqlDS.getConnection();

	String sql = "delete from store where id = ?";

	stmt = conn.prepareStatement(sql);
	stmt.setInt(1, i);
	stmt.execute();
}

//	Delete product method
public void deleteProduct(int i) throws SQLException {
	Connection conn = null;
	PreparedStatement stmt = null;
	
	conn = mysqlDS.getConnection();
	
	String sql = "delete from product where pid = ?";
	
	stmt = conn.prepareStatement(sql);
	stmt.setInt(1, i);
	stmt.execute();
}
}
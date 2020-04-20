/**
 * This program performs data analysis 
 * and stores the result in the database.
 * 
 * @author Yulia Thonippara (A20411313)
 * ITMD 411 Spring 2020 Lab 4
 */

import java.sql.Connection;
import java.sql.DriverManager; 
import java.sql.SQLException;
  
public class DbConnect {
	// Code database URL
	static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/411labs?autoReconnect=true&useSSL=false";
	// Database credentials
	static final String USER = "db411", PASS = "411";
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
}



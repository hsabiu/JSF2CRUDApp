package com.peopleware.jsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * A class representing a connection to MySQL database
 */
public class DBManager {

	public DBManager() {

	}

	/*
	 * A utility method to connect to MySQL database
	 */
	public Connection getConnection() {

		try {
			// Get the MySQL driver and create a connection by providing
			// the connection url, user name and password
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/peopleware", "root", "root");

			// Return the connection
			return conn;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

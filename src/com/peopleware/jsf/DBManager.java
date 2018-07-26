package com.peopleware.jsf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

	public DBManager() {
		// TODO Auto-generated constructor stub
	}

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/peopleware", "root", "invincible2006");
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

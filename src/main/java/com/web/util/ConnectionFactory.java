package com.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(
				System.getenv("PostgresURL"),
				System.getenv("Postgres Username"),
				System.getenv("Postgres Password")
				);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		 
		 return conn;
	}
}

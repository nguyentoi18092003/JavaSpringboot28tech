package com.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBCUtil {
	static final String DB_URL="jdbc:mysql://localhost:3306/estatebasic";
	static final String USER="root";
	static final String PASS="toi@1809";
	
	public static Connection getConnection() {
		Connection conn=null;
		try {
			conn =DriverManager.getConnection(DB_URL,USER,PASS);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

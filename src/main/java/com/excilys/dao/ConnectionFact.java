package com.excilys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFact {

	String driverClassName = "com.mysql.jdbc.Driver";
	String connectionUrl = "jdbc:mysql://localhost/miniprojet";
	String dbUser = "root";
	String dbPwd = "root";

	private static ConnectionFact connectionFactory = null;

	private ConnectionFact() {
		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
		return conn;
	}

	public static ConnectionFact getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFact();
		}
		return connectionFactory;
	}
}

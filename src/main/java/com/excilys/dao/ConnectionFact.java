package com.excilys.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFact {

	private String driverClassName = "com.mysql.jdbc.Driver";
	private String connectionUrl = "jdbc:mysql://localhost/miniprojet";
	private String dbUser = "root";
	private String dbPwd = "root";

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

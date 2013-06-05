package com.excilys.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public enum DsFact {

	INSTANCE;

	private static final String ENV_JDBC = "java:/comp/env/jdbc/miniprojet";

	private DataSource ds;
	private ThreadLocal<Connection> con;

	private DsFact() {
		con = new ThreadLocal<Connection>();
		try {
			InitialContext context = new InitialContext();
			ds = (DataSource) context.lookup(ENV_JDBC);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnectionThread() throws SQLException {
		Connection c = con.get();
		if (c == null)
			con.set(c = ds.getConnection());
		return c;
	}

	public void closeConnection() {
		try {
			con.get().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.remove();
	}

}

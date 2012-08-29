package com.agnie.common.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCUtil {

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		ResourceBundle resource = ResourceBundle.getBundle("testdbserver");
		String server = resource.getString("server.host");
		String port = resource.getString("server.port");
		String database = resource.getString("database");
		String username = resource.getString("username");
		String password = resource.getString("password");

		conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, username, password);
		return conn;
	}

}

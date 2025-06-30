/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
// ResourceBundle is no longer needed for H2 in-memory DB
// import java.util.ResourceBundle;

public class JDBCUtil {

	// H2 In-memory database URL.
	// MODE=MYSQL for MySQL compatibility.
	// DB_CLOSE_DELAY=-1 to keep the DB alive for the duration of the JVM.
	// DATABASE_TO_UPPER=FALSE to preserve identifier case.
	private static final String H2_JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;MODE=MYSQL;DATABASE_TO_UPPER=FALSE";
	private static final String H2_USER = "sa";
	private static final String H2_PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		try {
			// Load the H2 driver
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("H2 JDBC Driver not found", e);
		}

		// ResourceBundle resource = ResourceBundle.getBundle("testdbserver"); // No longer needed
		// String server = resource.getString("server.host"); // No longer needed
		// String port = resource.getString("server.port"); // No longer needed
		// String database = resource.getString("database"); // No longer needed
		// String username = resource.getString("username"); // No longer needed
		// String password = resource.getString("password"); // No longer needed

		// conn = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, username, password); // Old MySQL connection

		Connection conn = DriverManager.getConnection(H2_JDBC_URL, H2_USER, H2_PASSWORD);
		return conn;
	}

}

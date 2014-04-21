/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
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

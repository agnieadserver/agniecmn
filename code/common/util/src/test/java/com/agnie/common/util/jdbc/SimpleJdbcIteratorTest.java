package com.agnie.common.util.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.agnie.common.util.tablefile.SimpleJDBCTableIterator;

public class SimpleJdbcIteratorTest {

	@BeforeClass
	public static void init() {
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer create = new StringBuffer();
			create.append("CREATE  TABLE SIMPLE_ITERATOR_TEST ( ");
			create.append("`id` INT NOT NULL AUTO_INCREMENT , ");
			create.append("`company` VARCHAR(250) NULL , ");
			create.append("`location` VARCHAR(250) NULL , ");
			create.append("PRIMARY KEY (`id`) ) ");
			create.append("ENGINE = InnoDB ");
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(create.toString());
			} catch (SQLException e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			} finally {
				if (stmt != null) {
					stmt.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			}
		}
	}

	@Test
	public void JDBCTest() {
		initJDBCTest();
		SimpleJDBCTableIterator<SimpleCompany> itr;
		List<SimpleCompany> actual = new ArrayList<SimpleCompany>();
		try {
			itr = new SimpleJDBCTableIterator<SimpleCompany>(SimpleCompany.class, getConnection(), "SELECT * FROM SIMPLE_ITERATOR_TEST");
			int count = 0;
			while (itr.hasNext()) {
				SimpleCompany com = itr.next();
				count++;
				Assert.assertNotNull(com);
				actual.add(com);
			}
			Assert.assertEquals(3, count);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		Assert.assertNotNull(actual);
		List<SimpleCompany> expected = new ArrayList<SimpleCompany>();
		SimpleCompany com = new SimpleCompany();
		com.setId(1);
		com.setCompanyName("Tata Motors");
		com.setLocation("Bhosari");
		expected.add(com);
		com = new SimpleCompany();
		com.setId(2);
		com.setCompanyName("BMW");
		com.setLocation("Chakan");
		expected.add(com);
		com = new SimpleCompany();
		com.setId(3);
		com.setCompanyName("Volvo");
		com.setLocation("Benglore");
		expected.add(com);
		Assert.assertEquals(expected, actual);
	}

	private void initJDBCTest() {
		Connection conn = null;
		try {
			conn = getConnection();
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("INSERT INTO SIMPLE_ITERATOR_TEST ( `company`, `location`) VALUES  ( 'Tata Motors', 'Bhosari' )");
				stmt.addBatch("INSERT INTO SIMPLE_ITERATOR_TEST ( `company`, `location`) VALUES  ( 'BMW', 'Chakan' )");
				stmt.addBatch("INSERT INTO SIMPLE_ITERATOR_TEST ( `company`, `location`) VALUES  ( 'Volvo', 'Benglore' )");
				stmt.executeBatch();
			} catch (SQLException e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			} finally {
				if (stmt != null) {
					stmt.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			}
		}
	}

	@AfterClass
	public static void shutdown() {
		Connection conn = null;
		try {
			conn = getConnection();
			StringBuffer create = new StringBuffer();
			create.append("drop table SIMPLE_ITERATOR_TEST");
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(create.toString());
			} catch (SQLException e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			} finally {
				if (stmt != null) {
					stmt.close();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} finally {
			try {
				if (!conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Assert.assertTrue(false);
			}
		}
	}

	private static Connection getConnection() throws SQLException {
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

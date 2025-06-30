/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			conn = JDBCUtil.getConnection();
			StringBuffer create = new StringBuffer();
			create.append("CREATE  TABLE SIMPLE_ITERATOR_TEST ( ");
			create.append("`id` INT NOT NULL AUTO_INCREMENT , "); // Reverted to AUTO_INCREMENT
			create.append("`company` VARCHAR(250) NULL , ");
			create.append("`location` VARCHAR(250) NULL , ");
			create.append("PRIMARY KEY (`id`) ) "); // Re-added explicit PRIMARY KEY
			// create.append("ENGINE = InnoDB "); // Removed MySQL specific engine
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
			// Reverted to backticked name in SELECT
			itr = new SimpleJDBCTableIterator<SimpleCompany>(SimpleCompany.class, JDBCUtil.getConnection(), "SELECT * FROM `SIMPLE_ITERATOR_TEST`");
			int count = 0;
			while (itr.hasNext()) {
				SimpleCompany com = itr.next();
				count++;
				Assert.assertNotNull(com);
				actual.add(com);
			}
			itr.releaseResources();
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
			conn = JDBCUtil.getConnection();
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				// Reverted to backticked names
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
			conn = JDBCUtil.getConnection();
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

}

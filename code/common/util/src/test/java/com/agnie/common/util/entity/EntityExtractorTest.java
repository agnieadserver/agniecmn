/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.agnie.common.util.jdbc.JDBCUtil;
import com.agnie.common.util.jdbc.SimpleCompany;

public class EntityExtractorTest {

	@BeforeClass
	public static void init() {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
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
			initJDBCTest();
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
	public void simpleEntityExtratorTest() {
		try {
			EntityExtractor<SimpleCompany> entExt = new EntityExtractor<SimpleCompany>(SimpleCompany.class, JDBCUtil.getConnection());

			SimpleCompany actual = entExt.getEntity("SELECT * FROM SIMPLE_ITERATOR_TEST WHERE id=1");
			SimpleCompany expected = new SimpleCompany();
			expected.setId(1);
			expected.setCompanyName("Tata Motors");
			expected.setLocation("Bhosari");
			Assert.assertEquals(expected, actual);

		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void simpleListEntityExtractorTest() {
		try {
			EntityExtractor<SimpleCompany> entExt = new EntityExtractor<SimpleCompany>(SimpleCompany.class, JDBCUtil.getConnection());

			List<SimpleCompany> actual = entExt.getList("SELECT * FROM SIMPLE_ITERATOR_TEST WHERE id IN(1,2,3)");
			Assert.assertNotNull(actual);
			Assert.assertEquals(3, actual.size());
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

		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void simpleMutlipleRecordTest() {
		try {
			EntityExtractor<SimpleCompany> entExt = new EntityExtractor<SimpleCompany>(SimpleCompany.class, JDBCUtil.getConnection());

			SimpleCompany actual = entExt.getEntity("SELECT * FROM SIMPLE_ITERATOR_TEST WHERE company='Volvo'");
			Assert.assertTrue(false);
		} catch (MultipleRecordsExcpetion e) {
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	private static void initJDBCTest() {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("INSERT INTO SIMPLE_ITERATOR_TEST ( `id`, `company`, `location`) VALUES  ( 1, 'Tata Motors', 'Bhosari' )");
				stmt.addBatch("INSERT INTO SIMPLE_ITERATOR_TEST ( `id`, `company`, `location`) VALUES  ( 2, 'BMW', 'Chakan' )");
				stmt.addBatch("INSERT INTO SIMPLE_ITERATOR_TEST ( `id`, `company`, `location`) VALUES  ( 3, 'Volvo', 'Benglore' )");
				stmt.addBatch("INSERT INTO SIMPLE_ITERATOR_TEST ( `id`, `company`, `location`) VALUES  ( 4, 'Volvo', 'Benglore' )");
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

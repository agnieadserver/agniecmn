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

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.agnie.common.util.tablefile.JDBCTableIterator;
import com.agnie.common.util.tablefile.MultiColBean;
import com.agnie.common.util.tablefile.MultiColWrapperBean;
import com.agnie.common.util.tablefile.SampleBean;

public class JdbcIteratorTest {

	@BeforeClass
	public static void init() {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			StringBuffer create = new StringBuffer();
			create.append("CREATE  TABLE ITERATOR_TEST ( ");
			create.append("`id` INT NOT NULL AUTO_INCREMENT , ");
			create.append("`WrapperColName` VARCHAR(250) NULL , ");
			create.append("`SingleColumn` VARCHAR(250) NULL , ");
			create.append("`Fname` VARCHAR(45) NULL , ");
			create.append("`Lname` VARCHAR(45) NULL , ");
			create.append("`Age` FLOAT NULL , ");
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
		JDBCTableIterator<MultiColWrapperBean> itr;
		MultiColWrapperBean actual = null;
		try {
			itr = new JDBCTableIterator<MultiColWrapperBean>(MultiColWrapperBean.class, JDBCUtil.getConnection(), "SELECT * FROM ITERATOR_TEST");
			int count = 0;
			while (itr.hasNext()) {
				actual = itr.next();
				count++;
			}
			Assert.assertEquals(1, count);
			Assert.assertTrue(true);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (SQLException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		Assert.assertNotNull(actual);
		MultiColWrapperBean expected = new MultiColWrapperBean();
		expected.setWrapperColName("WrapperSomething");
		MultiColBean mBean = new MultiColBean();
		mBean.setSingleColumn("Something");
		SampleBean bean = new SampleBean();
		bean.setFname("Pranoti");
		bean.setLname("Patil");
		bean.setAge(30);
		mBean.addSampleBeans(bean);
		bean = new SampleBean();
		bean.setFname("Pandurang");
		bean.setLname("Patil");
		bean.setAge(30);
		mBean.addSampleBeans(bean);
		expected.addMultiColBeans(mBean);

		mBean = new MultiColBean();
		mBean.setSingleColumn("2nd Something");
		bean = new SampleBean();
		bean.setFname("Pranoti1");
		bean.setLname("Patil1");
		bean.setAge(31);
		mBean.addSampleBeans(bean);
		bean = new SampleBean();
		bean.setFname("Pandurang1");
		bean.setLname("Patil1");
		bean.setAge(31);
		mBean.addSampleBeans(bean);
		bean = new SampleBean();
		bean.setFname("Pandurang2");
		bean.setLname("Patil2");
		bean.setAge(31);
		mBean.addSampleBeans(bean);
		expected.addMultiColBeans(mBean);
		Assert.assertEquals(expected, actual);
	}

	private void initJDBCTest() {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.addBatch("INSERT INTO ITERATOR_TEST ( `WrapperColName`, `SingleColumn`, `Fname`, `Lname`, `Age`) VALUES  ( 'WrapperSomething',  'Something', 'Pranoti',  'Patil', 30 )");
				stmt.addBatch("INSERT INTO ITERATOR_TEST ( `WrapperColName`, `SingleColumn`, `Fname`, `Lname`, `Age`) VALUES  ( 'WrapperSomething',  'Something', 'Pandurang',  'Patil', 30 )");
				stmt.addBatch("INSERT INTO ITERATOR_TEST ( `WrapperColName`, `SingleColumn`, `Fname`, `Lname`, `Age`) VALUES  ( 'WrapperSomething',  '2nd Something', 'Pranoti1',  'Patil1', 31 )");
				stmt.addBatch("INSERT INTO ITERATOR_TEST ( `WrapperColName`, `SingleColumn`, `Fname`, `Lname`, `Age`) VALUES  ( 'WrapperSomething',  '2nd Something', 'Pandurang1',  'Patil1', 31 )");
				stmt.addBatch("INSERT INTO ITERATOR_TEST ( `WrapperColName`, `SingleColumn`, `Fname`, `Lname`, `Age`) VALUES  ( 'WrapperSomething',  '2nd Something', 'Pandurang2',  'Patil2', 31 )");
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
			create.append("drop table ITERATOR_TEST");
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

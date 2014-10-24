/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.agnie.common.util.tablefile.ExcelSheetIterator;
import com.agnie.common.util.tablefile.ExcelWorkbook;

public class MultiBeanTest {

	@Test
	public void multiBeanTest() {
		try {
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			InputStream is = cl.getResourceAsStream("MultiBeanTest.xlsx");
			ExcelWorkbook<Company> workbook = new ExcelWorkbook<Company>(is, Company.class);

			List<Company> companiesExpected = new ArrayList<Company>();
			Company comp = new Company();
			comp.setId(1);
			comp.setCompanyName("ABC");
			Employee emp = new Employee();
			emp.setName("Sagar Shinde");
			emp.setAge(34);
			emp.addContact("8934728989");
			comp.addEmployee(emp);
			emp = new Employee();
			emp.setName("Ravi Sharma");
			emp.setAge(28);
			emp.addContact("7890334564");
			emp.addContact("3480980900");
			emp.addContact("3480980900");
			comp.addEmployee(emp);
			emp = new Employee();
			emp.setName("Pravin Thakur");
			emp.setAge(34);
			emp.addContact("8790898980");
			emp.addContact("7868033808");
			comp.addEmployee(emp);
			companiesExpected.add(comp);
			
			comp = new Company();
			comp.setId(2);
			comp.setCompanyName("XYZ");
			emp = new Employee();
			emp.setName("Shyam Shetty");
			emp.setAge(46);
			emp.addContact("8954355344");
			comp.addEmployee(emp);
			companiesExpected.add(comp);
			
			comp = new Company();
			comp.setId(3);
			comp.setCompanyName("YMC");
			emp = new Employee();
			emp.setName("Ram Wani");
			emp.setAge(23);
			emp.addContact("7844345342");
			comp.addEmployee(emp);
			companiesExpected.add(comp);
			List<Company> companiesActual = new ArrayList<Company>();
			while (workbook.hasNext()) {
				ExcelSheetIterator<Company> itr = workbook.next();

				while (itr.hasNext()) {
					Company beanActual = (Company) itr.next();
					System.out.println(beanActual);
					companiesActual.add(beanActual);
				}
			}
			
			Assert.assertEquals(companiesExpected, companiesActual);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			Assert.assertTrue(false);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}
}

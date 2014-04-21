/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.tablefile;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelWorkbook<T> {
	private Workbook	workbook;
	private Class<T>	cls;
	private int			noOfSheets;
	private int			currentSheetIndex;

	public ExcelWorkbook(InputStream stream, Class<T> cls) throws IOException, InvalidFormatException {
		this.workbook = WorkbookFactory.create(stream);
		this.cls = cls;
		noOfSheets = workbook.getNumberOfSheets();
	}

	public boolean hasNext() {
		return currentSheetIndex < noOfSheets;
	}

	public ExcelSheetIterator<T> next() {
		try {
			ExcelSheetIterator<T> iter = new ExcelSheetIterator<T>(workbook.getSheetAt(currentSheetIndex), cls);
			currentSheetIndex++;
			return iter;
		} catch (IOException e) {
			// TODO: add logger
			e.printStackTrace();
		}
		return null;
	}

}

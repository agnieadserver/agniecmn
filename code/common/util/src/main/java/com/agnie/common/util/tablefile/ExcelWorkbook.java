package com.agnie.common.util.tablefile;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelWorkbook<T> {
	private HSSFWorkbook	workbook;
	private Class<T>		cls;
	private int				noOfSheets;
	private int				currentSheetIndex;

	public ExcelWorkbook(InputStream stream, Class<T> cls) throws IOException {
		this.workbook = new HSSFWorkbook(stream);
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

package com.agnie.common.util.tablefile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * ExcelSheetIterator will be Excel sheet to Bean Generic iterator which takes InputStream and iterate through each
 * record. When you iterate it, it will return entity bean for every record with values populated as per following rule.
 * <p>
 * Entity you want to get populate should attach TableHeader annotation to the property setters. TableHeader takes one
 * parameter i.e "name".
 * <ul>
 * <li>If you don't specify name parameter, it will try to search for the header with name exactly matching to the bean
 * property with first character in capital case. If you have different header name in Excel sheet you can map it to the
 * property with this name parameter.
 * </ul>
 * 
 */
public class ExcelSheetIterator<T> extends AbstractTableFileIterator<T> {

	protected static final Log	logger	= LogFactory.getLog(ExcelSheetIterator.class);
	private HSSFSheet			sheet;
	private Iterator<Row>		rowItr;

	ExcelSheetIterator(HSSFSheet sheet, Class<T> cls) throws IOException {
		this(sheet, cls, false);
	}

	ExcelSheetIterator(HSSFSheet sheet, Class<T> cls, boolean throwValidationErrors) throws IOException {
		super(cls, throwValidationErrors);
		this.sheet = sheet;
		this.rowItr = sheet.rowIterator();

		init();
	}

	/**
	 * This method will read Excel file and convert tokens into list of tokens
	 * 
	 * @throws IOException
	 */
	protected void readTokens() throws IOException {
		nextTokens = new ArrayList<String>();

		if (rowItr.hasNext()) {
			Row row = rowItr.next();
			Iterator<Cell> cellItr = row.cellIterator();

			while (cellItr.hasNext()) {
				Cell cell = cellItr.next();
				nextTokens.add(cell.toString());
			}
		} else {
			nextTokens = null;
		}
		rowcount++;
	}

	public String getSheetName() {
		return sheet.getSheetName();
	}

}

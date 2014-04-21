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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

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
	protected static final Log		logger				= LogFactory.getLog(ExcelSheetIterator.class);
	private Sheet					sheet;
	private Iterator<Row>			rowItr;
	private Map<Integer, String>	indexMappedHeaders	= new HashMap<Integer, String>();

	ExcelSheetIterator(Sheet sheet, Class<T> cls) throws IOException {
		this(sheet, cls, false);
	}

	ExcelSheetIterator(Sheet sheet, Class<T> cls, boolean throwValidationErrors) throws IOException {
		super(cls, throwValidationErrors);
		this.sheet = sheet;
		if (this.sheet != null) {
			this.rowItr = sheet.rowIterator();
			if (rowItr.hasNext()) {
				Row row = rowItr.next();
				Iterator<Cell> cellItr = row.cellIterator();

				while (cellItr.hasNext()) {
					Cell cell = cellItr.next();
					indexMappedHeaders.put(cell.getColumnIndex(), cell.toString());
				}
			}
		}
	}

	/**
	 * This method will read Excel file and convert tokens into list of tokens mapped with column header
	 * 
	 * @throws IOException
	 */
	protected Map<String, String> readTokens() throws IOException {
		if (this.sheet != null) {
			if (rowItr.hasNext()) {
				Map<String, String> colMapedTokens = new HashMap<String, String>();
				Row row = rowItr.next();
				for (Cell cell : row) {
					String token = "";
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						Double doubleVal = cell.getNumericCellValue();
						// TODO: Need to add different use cases to test number formatter is working as per requirement
						// here
						token = new java.text.DecimalFormat("0.0").format(doubleVal);
						break;

					case Cell.CELL_TYPE_STRING:
						token = cell.getStringCellValue();
						break;
					default:
						// TODO: Need to through custom exception
						logger.error("Excel sheet cell Type => " + cell.getCellType() + " is not supported");
						break;
					}
					if (token != null && !"".equals(token)) {
						colMapedTokens.put(indexMappedHeaders.get(cell.getColumnIndex()), token);
					}
				}
				rowcount++;
				return colMapedTokens;
			} else {
				return null;
			}
		}
		return null;
	}

	public String getSheetName() {
		return sheet.getSheetName();
	}

}

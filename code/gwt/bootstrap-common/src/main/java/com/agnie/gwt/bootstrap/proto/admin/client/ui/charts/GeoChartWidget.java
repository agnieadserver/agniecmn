/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui.charts;

import com.google.gwt.core.client.JsArrayMixed;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataSource;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;

/**
 * 
 * @author Pandurang Patil 30-Nov-2015
 *
 */
public class GeoChartWidget extends GeoChart {
	private DataTable		dataTable		= DataTable.create();
	private GeoChartOptions	options			= GeoChartOptions.create();
	private int				currentRecords	= 0;

	@Override
	public void draw(DataSource data) {
		super.draw(data);
	}

	public void addColumn(ColumnType type) {
		dataTable.addColumn(type);
	}

	public void setOptions(GeoChartOptions options) {
		this.options = options;
	}

	public void clearRows() {
		dataTable.removeRows(0, currentRecords);
		currentRecords = 0;
	}

	public void addRow(String key, double value) {
		JsArrayMixed arr = (JsArrayMixed) JsArrayMixed.createArray();
		arr.push(key);
		arr.push(value);
		dataTable.addRow(arr);
		currentRecords++;
	}

	public void draw() {
		super.draw(dataTable, options);
	}
}

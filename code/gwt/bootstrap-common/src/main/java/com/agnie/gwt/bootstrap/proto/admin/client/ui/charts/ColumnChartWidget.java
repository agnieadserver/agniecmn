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
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.ColumnChart;
import com.googlecode.gwt.charts.client.corechart.ColumnChartOptions;
import com.googlecode.gwt.charts.client.corechart.ColumnChartSeries;

/**
 * @author Pandurang Patil 30-Nov-2015
 *
 */
public class ColumnChartWidget extends ColumnChart {
	private DataTable			dataTable		= DataTable.create();
	private int					currentRecords	= 0;
	private int					currentYcolumns	= 0;
	private ColumnChartOptions	options			= ColumnChartOptions.create();

	public void setTitle(String title) {
		options.setTitle(title);
	}

	public void addColumn(ColumnType type, String label, boolean yaxis) {
		dataTable.addColumn(type, label);
		if (yaxis) {
			ColumnChartSeries series = ColumnChartSeries.create();
			series.setTargetAxisIndex(currentYcolumns);
			series.setVisibleInLegend(true);
			options.setSeries(currentYcolumns++, series);
		}
	}

	public void resetEverything() {
		dataTable = DataTable.create();
		currentRecords = 0;
		currentYcolumns = 0;
		options = ColumnChartOptions.create();
	}

	public void setOptions(ColumnChartOptions options) {
		this.options = options;
	}

	public void addRow(String key, double... values) {
		JsArrayMixed arr = (JsArrayMixed) JsArrayMixed.createArray();
		arr.push(key);
		for (double val : values) {
			arr.push(val);
		}
		dataTable.addRow(arr);
		currentRecords++;
	}

	public void clearRows() {
		dataTable.removeRows(0, currentRecords);
		currentRecords = 0;
	}

	public void draw() {
		super.draw(dataTable, options);
	}
}

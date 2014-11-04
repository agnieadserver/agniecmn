/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import java.util.List;

import com.agnie.gwt.bootstrap.proto.admin.client.entity.ChartColumn;
import com.agnie.gwt.bootstrap.proto.admin.client.entity.ChartEntity;
import com.agnie.gwt.bootstrap.proto.admin.client.entity.ChartValue;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataSource;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;

/**
 * @author rajgaurav
 *
 */
public class PieChartWidget extends PieChart {

	private DataTable	dataTable;
	PieChartOptions		options	= PieChartOptions.create();

	public PieChartWidget() {
		super();
		dataTable = DataTable.create();
	}

	@Override
	public void draw(DataSource data) {

		super.draw(data);
	}

	public void draw(ChartValue listChartValue) {
		List<ChartEntity> list = listChartValue.getListValue();
		addColumn(listChartValue.getListColumn());
		dataTable.addRows(listChartValue.getListValue().size());
		for (int i = 0; i < list.size(); i++) {
			ChartEntity entity = list.get(i);
			dataTable.setValue(i, 0, entity.getKey());
			dataTable.setValue(i, 1, entity.getValue());
		}
		options = PieChartOptions.create();
		options.setBackgroundColor("#f0f0f0");
		options.setFontName("Tahoma");
		options.setIs3D(false);
		options.setSliceVisibilityThreshold(0.0);
		super.draw(dataTable, options);
	}

	private void addColumn(List<ChartColumn> list) {
		for (int i = 0; i < list.size(); i++) {
			dataTable.addColumn(list.get(i).getColumnType(), list.get(i).getStringValue());
		}
	}

	public void draw(ChartValue listChartValue, PieChartOptions options) {
		List<ChartEntity> list = listChartValue.getListValue();
		for (int i = 0; i < list.size(); i++) {
			ChartEntity entity = list.get(i);
			dataTable.setValue(i, 0, entity.getKey());
			dataTable.setValue(i, 1, entity.getValue());
		}
		super.draw(dataTable, options);
	}

}

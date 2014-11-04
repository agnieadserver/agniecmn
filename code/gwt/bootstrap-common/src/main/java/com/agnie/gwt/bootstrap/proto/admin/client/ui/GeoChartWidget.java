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
import com.googlecode.gwt.charts.client.DataSource;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;

/**
 * @author rajgaurav
 *
 */
public class GeoChartWidget extends GeoChart {
	private DataTable		dataTable;
	private GeoChartOptions	options	= GeoChartOptions.create();

	// private GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();

	public GeoChartWidget() {
		super();
		dataTable = DataTable.create();
	}

	@Override
	public void draw(DataSource data) {
		super.draw(data);
	}

	public void draw(ChartValue listChartValue) {
		if (listChartValue.getListColumn() != null)
			addColumn(listChartValue.getListColumn());
		if (listChartValue.getListValue() != null) {
			dataTable.addRows(listChartValue.getListValue().size());
			for (int i = 0; i < listChartValue.getListValue().size(); i++) {
				ChartEntity entity = listChartValue.getListValue().get(i);
				dataTable.setValue(i, 0, entity.getKey());
				dataTable.setValue(i, 1, entity.getValue());
			}
			options.setDatalessRegionColor("#777777");
			super.draw(dataTable, options);
		}
	}

	private void addColumn(List<ChartColumn> list) {
		for (int i = 0; i < list.size(); i++) {
			dataTable.addColumn(list.get(i).getColumnType(), list.get(i).getStringValue());
		}
	}

	public void draw(ChartValue listChartValue, GeoChartOptions options) {
		List<ChartEntity> list = listChartValue.getListValue();
		for (int i = 0; i < list.size(); i++) {
			ChartEntity entity = list.get(i);
			dataTable.setValue(i, 0, entity.getKey());
			dataTable.setValue(i, 1, entity.getValue());
		}
		super.draw(dataTable, options);
	}
}

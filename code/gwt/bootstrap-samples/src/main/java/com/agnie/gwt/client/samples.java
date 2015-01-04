/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client;

import java.util.ArrayList;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;

import com.agnie.gwt.bootstrap.proto.admin.client.entity.ChartColumn;
import com.agnie.gwt.bootstrap.proto.admin.client.entity.ChartEntity;
import com.agnie.gwt.bootstrap.proto.admin.client.entity.ChartValue;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.Account;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.CheckBox;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.CheckBoxType;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.PieChartWidget;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.RadioButton;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.SearchBox;
import com.agnie.gwt.client.ui.CellTableSample;
import com.agnie.gwt.client.ui.CodeEditorSample;
import com.agnie.gwt.client.ui.GWTUploadSample;
import com.agnie.gwt.client.ui.ListBoxSample;
import com.agnie.gwt.client.ui.SamplePage;
import com.agnie.gwt.client.ui.SelectTableSample;
import com.agnie.gwt.client.ui.SelectUnselectSample;
import com.agnie.gwt.client.ui.TagEditorTest;
import com.agnie.gwt.client.ui.ToggleSample;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class samples implements EntryPoint {
	SamplePage	page	= new SamplePage();

	public void samplePageTest() {
		Button addME = new Button("Add Me");
		addME.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get().clear();
				RootPanel.get().add(page);
			}
		});
		// RootPanel.get().add(addME);
		RootPanel.get().add(page);
		GWT.log("Samples entry point is called  1...... ");
		GWT.log("Samples entry point is called  2...... ");
	}

	public void cellTableTest() {
		RootPanel.get().add(new CellTableSample());
	}

	public void toggleSample() {
		RootPanel.get().add(new ToggleSample());
	}

	public void codeEditorSample() {
		RootPanel.get().add(new CodeEditorSample());
	}

	public void onModuleLoad() {
		// samplePageTest();
		// cellTableTest();
		// toggleSample();
		// codeEditorSample();
		// searchWidgettest();
		// checkChartPanel();
		// RootPanel.get().add(countWidget);
		// checkChartFromWidget();
		// sampleListBox();
		// tagEditor();
		// customCheckBox();
		// selectUnselectSample();
		// selectTableSample();
		// uploadSample();
		createAccountWidget();
	}

	public void uploadSample() {
		RootPanel.get().add(new GWTUploadSample());
	}

	public void selectUnselectSample() {
		SelectUnselectSample sus = new SelectUnselectSample();
		RootPanel.get().add(sus);
		// sus.setSelected();
	}

	public void selectTableSample() {
		RootPanel.get().add(new SelectTableSample());
	}

	public void customCheckBox() {
		CheckBox cb = new CheckBox();
		cb.setType(CheckBoxType.PRIMARY);
		RootPanel.get().add(cb);

		cb.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				Window.alert("This works man");
			}
		});

		RadioButton rb1 = new RadioButton("group", "First");
		RootPanel.get().add(rb1);
		RadioButton rb2 = new RadioButton("group", "Second");
		RootPanel.get().add(rb2);

		rb1.addValueChangeHandler(new ValueChangeHandler<Boolean>() {

			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				Window.alert("This also works");
			}
		});
	}

	public void tagEditor() {
		TagEditorTest tet = new TagEditorTest();
		RootPanel.get().add(tet);
	}

	public void sampleListBox() {
		RootPanel.get().add(new ListBoxSample());
	}

	public void checkChartFromWidget() {

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				PieChartWidget chart = new PieChartWidget();
				List<ChartEntity> listValues = new ArrayList<ChartEntity>();
				listValues.add(new ChartEntity("India", 226));
				listValues.add(new ChartEntity("China", 26));
				listValues.add(new ChartEntity("Pakistan", 5));
				listValues.add(new ChartEntity("Rusia", 5));
				List<ChartColumn> listColumn = new ArrayList<ChartColumn>();
				listColumn.add(new ChartColumn(ColumnType.STRING, "Browsers"));
				listColumn.add(new ChartColumn(ColumnType.NUMBER, "Browsers"));
				ChartValue value = new ChartValue(listColumn, listValues);
				chart.draw(value);
				RootPanel.get().add(chart);
			}
		});

	}

	private void checkChartPanel() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				final PieChart chart = new PieChart();
				// Prepare the data
				DataTable dataTable = DataTable.create();
				dataTable.addColumn(ColumnType.STRING, "Task");
				// dataTable.addColumn(ColumnType.NUMBER, "Hours per Day");
				dataTable.addRows(5);
				dataTable.setValue(0, 0, "Work");
				dataTable.setValue(0, 1, 11);
				dataTable.setValue(1, 0, "Sleep");
				dataTable.setValue(1, 1, 7);
				dataTable.setValue(2, 0, "Watch TV");
				dataTable.setValue(2, 1, 1);
				dataTable.setValue(3, 0, "Eat");
				dataTable.setValue(3, 1, 1);
				dataTable.setValue(4, 0, "Commute");
				dataTable.setValue(4, 1, 1);
				// Set options
				PieChartOptions options = PieChartOptions.create();
				options.setBackgroundColor("#f0f0f0");
				// options.setColors(colors);
				options.setFontName("Tahoma");
				options.setIs3D(true);
				// options.setPieResidueSliceColor("#FFFFFF");
				// options.setPieResidueSliceLabel("Others");
				options.setSliceVisibilityThreshold(0.0);
				options.setTitle("So, how was your day?");
				// Draw the chart
				chart.draw(dataTable);
				RootPanel.get().add(chart);

				// chart.addReadyHandler(new ReadyHandler() {
				//
				// @Override
				// public void onReady(ReadyEvent event) {
				// chart.setSelection(Selection.create(1, null));
				// }
				// });

			}
		});

	}

	private void searchWidgettest() {
		RootPanel.get().add(new SearchBox());
	}

	private void createAccountWidget() {
		Account account = new Account();
		account.setName("Raj Gaurav ");
		RootPanel.get().add(account);

	}
}

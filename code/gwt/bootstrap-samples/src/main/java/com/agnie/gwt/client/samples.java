/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client;

import java.util.Date;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.base.constants.DateTimePickerView;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.base.events.ChangeDateEvent;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.base.events.ChangeDateHandler;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.Account;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.CheckBox;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.CheckBoxType;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.RadioButton;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.SearchBox;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.charts.ColumnChartWidget;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.charts.GeoChartWidget;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.charts.PieChartWidget;
import com.agnie.gwt.bootstrap.proto.admin.client.ui.charts.StackedColumnChartWidget;
import com.agnie.gwt.client.ui.CellTableSample;
import com.agnie.gwt.client.ui.CodeEditorSample;
import com.agnie.gwt.client.ui.CollapsePanelWidget;
import com.agnie.gwt.client.ui.GWTUploadSample;
import com.agnie.gwt.client.ui.ListBoxSample;
import com.agnie.gwt.client.ui.SamplePage;
import com.agnie.gwt.client.ui.SelectTableSample;
import com.agnie.gwt.client.ui.SelectUnselectSample;
import com.agnie.gwt.client.ui.TagEditorTest;
import com.agnie.gwt.client.ui.TimeZoneSample;
import com.agnie.gwt.client.ui.ToggleSample;
import com.agnie.gwt.client.ui.sampleLoginPage;
import com.agnie.gwt.common.client.widget.LoaderWidget;
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
		Button btn = new Button("For texct");
		RootPanel.get().add(btn);

		btn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().add(new ToggleSample());

			}
		});

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
		// geoChartCheck();
		// columnChartCheck();
		// stckedColumnChartCheck();
		// sampleListBox();
		// tagEditor();
		// customCheckBox();
		// selectUnselectSample();
		// selectTableSample();
		// uploadSample();
		// createAccountWidget();
		// sampleDatePickerTest();
		// DateBox dateBox = new DateBox();
		// dateBox.setValue(new Date());
		// RootPanel.get().add(dateBox);
		// collapsePanelExample();
		// SampleLoginPage();
		loaderWidgetTest();
	}

	private void loaderWidgetTest() {
		LoaderWidget lw = new LoaderWidget();
		lw.setVisible(true);
		RootPanel.get().add(lw);

	}

	private void SampleLoginPage() {
		sampleLoginPage lp = new sampleLoginPage();
		RootPanel.get().add(lp);
	}

	private void collapsePanelExample() {

		RootPanel.get().add(new CollapsePanelWidget());
	}

	private void sampleDatePickerTest() {
		final DateTimePicker dateTimePicker = new DateTimePicker();
		final DateTimePicker dateTimePicker1 = new DateTimePicker();
		dateTimePicker.setMaxView(DateTimePickerView.MONTH);
		dateTimePicker1.setMaxView(DateTimePickerView.MONTH);
		dateTimePicker.setFormat("dd-M-yyyy");
		RootPanel.get().add(dateTimePicker);
		RootPanel.get().add(dateTimePicker1);
		dateTimePicker.setStartDate(new Date());

		dateTimePicker.addChangeDateHandler(new ChangeDateHandler() {

			@Override
			public void onChangeDate(ChangeDateEvent evt) {
				dateTimePicker1.setValue(null);
				dateTimePicker1.setStartDate(dateTimePicker.getValue());
			}
		});
		timeZoneSample();
	}

	public void timeZoneSample() {
		RootPanel.get().add(new TimeZoneSample());

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

	public void stckedColumnChartCheck() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				final StackedColumnChartWidget chart = new StackedColumnChartWidget();
				chart.setTitle("Ad Requests");
				chart.addColumn(ColumnType.STRING, "Publisher", false);
				chart.addColumn(ColumnType.NUMBER, "blank", true);
				chart.addColumn(ColumnType.NUMBER, "passed back", true);
				chart.addColumn(ColumnType.NUMBER, "impression", true);
				chart.addRow("TOI", 0, 5, 10);
				chart.addRow("MATA", 3, 3, 20);
				chart.addRow("Indian Express", 3, 0, 10);
				chart.addRow("The Hindu", 15, 3, 0);
				chart.draw();
				RootPanel.get().add(chart);
				Button btn = new Button("Redraw New");
				RootPanel.get().add(btn);
				btn.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						chart.clearRows();
						chart.addRow("Star Maza", 500000, 50);
						chart.addRow("IBN Live", 200000, 2);
						chart.addRow("Your Story", 5600000, 100);
						chart.addRow("Track.in", 345000, 30);
						chart.addRow("inc42", 584573, 34);
						chart.draw();
					}
				});
			}
		});
	}

	public void columnChartCheck() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				final ColumnChartWidget chart = new ColumnChartWidget();
				chart.setTitle("Impression & Clicks");
				chart.addColumn(ColumnType.STRING, "Publisher", false);
				chart.addColumn(ColumnType.NUMBER, "Impressions", true);
				chart.addColumn(ColumnType.NUMBER, "Clicks", true);
				chart.addRow("TOI", 200000, 10);
				chart.addRow("MATA", 10000, 2);
				chart.addRow("Indian Express", 20000, 6);
				chart.addRow("The Hindu", 50000, 20);
				chart.draw();
				RootPanel.get().add(chart);
				Button btn = new Button("Redraw New");
				RootPanel.get().add(btn);
				btn.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						chart.clearRows();
						chart.addRow("Star Maza", 500000, 50);
						chart.addRow("IBN Live", 200000, 2);
						chart.addRow("Your Story", 5600000, 100);
						chart.addRow("Track.in", 345000, 30);
						chart.addRow("inc42", 584573, 34);
						chart.draw();
					}
				});
			}
		});

	}

	public void geoChartCheck() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				final GeoChartWidget chart = new GeoChartWidget();
				chart.addColumn(ColumnType.STRING);
				chart.addColumn(ColumnType.NUMBER);
				chart.addRow("India", 226);
				chart.addRow("China", 26);
				chart.addRow("Pakistan", 5);
				chart.addRow("Rusia", 5);
				chart.draw();
				RootPanel.get().add(chart);
				Button btn = new Button("Redraw New");
				RootPanel.get().add(btn);
				btn.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						chart.clearRows();
						chart.addRow("America", 300);
						chart.addRow("Japan", 30);
						chart.addRow("France", 10);
						chart.addRow("Rusia", 10);
						chart.draw();
					}
				});
			}
		});

	}

	public void checkChartFromWidget() {

		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				final PieChartWidget chart = new PieChartWidget();
				chart.setTitle("Country Some chart");
				chart.addColumn(ColumnType.STRING);
				chart.addColumn(ColumnType.NUMBER);
				chart.addRow("India", 226);
				chart.addRow("China", 26);
				chart.addRow("Pakistan", 5);
				chart.addRow("Rusia", 5);
				chart.draw();
				RootPanel.get().add(chart);
				Button btn = new Button("Redraw New");
				RootPanel.get().add(btn);
				btn.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						chart.clearRows();
						chart.addRow("America", 300);
						chart.addRow("Japan", 30);
						chart.addRow("France", 10);
						chart.addRow("Rusia", 10);
						chart.draw();
					}
				});
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
		account.setTimeZone("Asia/Calcutta");
		RootPanel.get().add(account);

	}
}

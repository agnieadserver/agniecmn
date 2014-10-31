/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client;

import org.gwtbootstrap3.client.ui.Button;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.SearchBox;
import com.agnie.gwt.client.ui.CellTableSample;
import com.agnie.gwt.client.ui.CodeEditorSample;
import com.agnie.gwt.client.ui.SamplePage;
import com.agnie.gwt.client.ui.ToggleSample;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;

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
		searchWidgettest();
	}

	private void searchWidgettest() {
		RootPanel.get().add(new SearchBox());

	}
}

package com.agnie.gwt.client;

import com.agnie.gwt.common.client.widget.DialogBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PopupPanel;

public class DialogBoxTest {

	String			header		= "DialogBox Test";
	
	Button			testButton	= new Button("TestME");
	Button			testButton1	= new Button("TestME1");
	Button			testButton2	= new Button("TestME2");
	Button			testButton3	= new Button("TestME3");
	ClickHandler	ch			= new ClickHandler() {

									public void onClick(ClickEvent event) {
										Window.alert("Closing DialogBox");
										db.hide();
									}
								};

	DialogBox		db			= new DialogBox(header, testButton, ch);
	

	public void main(String args[]) {
		db.setHeader("DialogBox Test");
		db.addContent(testButton);
		db.addContent(testButton1);
		db.addContent(testButton2);
		db.addContent(testButton3);
		db.addCloseHandler(new CloseHandler<PopupPanel>() {

			public void onClose(CloseEvent<PopupPanel> event) {

				Window.alert("Closing DialogBox");
				db.hide();
			}
		});

	}

	public DialogBox getDialogBox() {
		return this.db;
	}

}

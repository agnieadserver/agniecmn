/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client;

import com.agnie.gwt.common.client.widget.DialogBox;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;

public class DialogBoxTest {

	String			header		= "DialogBox Test";
	
	Button			testButton	= new Button("TestME");
	Button			testButton1	= new Button("TestME1");
	Button			testButton2	= new Button("TestME2");
	Button			testButton3	= new Button("TestME3");
	DialogBox		db			= new DialogBox(header, testButton);
	

	public void main(String args[]) {
		db.setHeader("DialogBox Test");
		db.addContent(testButton);
		db.addContent(testButton1);
		db.addContent(testButton2);
		db.addContent(testButton3);
		db.addCloseHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				Window.alert("Closing DialogBox");
				db.hide();
			}
		});

	}

	public DialogBox getDialogBox() {
		return this.db;
	}

}

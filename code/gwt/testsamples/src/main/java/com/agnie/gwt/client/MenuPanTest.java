/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client;

import com.agnie.gwt.common.client.widget.MenuPan;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;

public class MenuPanTest {
	MenuPan			menuPan		= new MenuPan();

	Label			menuItem1	= new Label("Item1");
	Label			menuItem2	= new Label("Item2");
	Label			menuItem3	= new Label("Item3");
	Label			menuItem4	= new Label("Item4");
	Label			menuItem5	= new Label("Item5");
	Label			menuItem6	= new Label("Item6");
	Label			menuItem7	= new Label("Item7");
	Label			menuItem8	= new Label("Item8");

	ClickHandler	ch			= new ClickHandler() {

									public void onClick(ClickEvent event) {
										Window.alert("Don't touch me again ");
									}
								};

	public MenuPan getMenuPan() {
		menuItem1.addClickHandler(ch);
		menuPan.addMenuItem(menuItem1);
		
		menuItem2.addClickHandler(ch);
		menuPan.addMenuItem(menuItem2);
		
		menuPan.addMenuSeparator();
		
		menuItem3.addClickHandler(ch);
		menuPan.addMenuItem(menuItem3);
		
		menuItem4.addClickHandler(ch);
		menuPan.addMenuItem(menuItem4);
		
		Window.alert("Removing menuItem3");
		menuPan.removeMenuItem(2);
		menuPan.disableMenuItem(1);

		menuPan.setPopupPosition(100, 200);
		Window.alert("Editing menuItem1");
		menuPan.getMenuItem(0).setText("Edited");

		return menuPan;
	}

}

/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget;

import java.util.ArrayList;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * MenuPan widget.
 * 
 */
public class MenuPan extends PopupPanel {
	private static MenuPanResources	resource	= MenuPanResources.INSTANCE;
	static {
		resource.css().ensureInjected();
	}

	public MenuPan() {
		this(resource.css().menuPanContext());
	}

	public HTMLPanel	container		= new HTMLPanel("");
	ArrayList<Label>	menuItemList	= new ArrayList<Label>();
	DivElement			separator;

	public MenuPan(String styleClassName) {
		this.addStyleName(styleClassName);
		this.add(container);
		this.getElement().getStyle().setPosition(Style.Position.ABSOLUTE);
	}

	/**
	 * To set menuPan width.
	 * 
	 * @param width
	 */

	public void setMenuPanWidth(String width) {
		this.container.setWidth(width);
	}

	/**
	 * To set menuPan height.
	 * 
	 * @param width
	 */

	public void setMenuPanHeight(String height) {
		this.container.setHeight(height);
	}

	/**
	 * To Get menuItem(Label).
	 * 
	 * @param itemIndex
	 * @return
	 */
	public Label getMenuItem(int itemIndex) {
		return menuItemList.get(itemIndex);
	}

	/**
	 * To add separator in between two menuItem groups.
	 */
	public void addMenuSeparator() {
		DivElement separator = Document.get().createDivElement();
		separator.getStyle().setBackgroundColor("#EAEAEA");
		separator.getStyle().setHeight(1, Style.Unit.PX);
		this.container.getElement().appendChild(separator);
	}

	/**
	 * To add MenuItem (Label)
	 * 
	 * @param item
	 */
	public void addMenuItem(Label item) {
		item.addStyleName(resource.css().menuItem());
		this.menuItemList.add(item);
		this.container.add(item);
	}

	/**
	 * To remove menuItem(Label).
	 * 
	 * @param item
	 */
	public void removeMenuItem(int item) {
		this.menuItemList.remove(item);
		this.container.remove(item);
	}

	/**
	 * To Inactive menuItem.
	 * 
	 * @param item
	 */
	public void disableMenuItem(int item) {
		this.getMenuItem(item).addStyleName(resource.css().menuItemDisabled());
	}

	/**
	 * To active menuItem.
	 * 
	 * @param item
	 */
	public void enableMenuItem(int item) {
		this.getMenuItem(item).removeStyleName(resource.css().menuItemDisabled());
	}

	public static MenuPanResources getResources() {
		return resource;
	}

	public Widget asWidget() {
		return this;
	}

}

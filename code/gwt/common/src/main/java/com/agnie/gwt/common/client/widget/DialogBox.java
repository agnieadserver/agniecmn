package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class DialogBox extends PopupPanel{
	
	public DecoratedPanel decPanel=new DecoratedPanel();
	
	public DialogBox() {
		this.add(decPanel);
	}
	
	public DialogBox(String header,Widget content) {
		this.decPanel.setHeader(header);
		this.decPanel.addContent(content);
		this.add(decPanel);
	}
	
	public DialogBox(String header,Widget content,ClickHandler handler) {
		this.decPanel.setHeader(header);
		this.decPanel.addContent(content);
		this.decPanel.closeClickHandler(handler);
		this.add(decPanel);
	}
	
	
	public void setHeader(String header){
		decPanel.setHeader(header);
	}
	
	public void addContent(Widget content) {
		decPanel.addContent(content);
	}

	/**
	 * remove an widget from panel content
	 * 
	 * @param content
	 *            widget to remove
	 */
	public void removeContent(Widget content) {
		if (content != null) {
			this.decPanel.contentPan.remove(content);
		}
	}

	/**
	 * clears the content element of decorated panel
	 */
	public void clear() {
		this.decPanel.contentPan.clear();
	}
	
	/**
	 * clickHandler for close button.Also sets close button visible.
	 * 
	 * @param handler
	 *            click handler for close button.
	 */
	public void closeClickHandler(ClickHandler handler) {
		this.decPanel.close.setVisible(true);
		this.decPanel.close.addClickHandler(handler);
	}
}

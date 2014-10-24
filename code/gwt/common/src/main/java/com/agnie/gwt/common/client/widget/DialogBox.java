/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * Simple DialogBox with all PopupPanel features.
 * <br>Note:Default close button is invisible,visible only if we call method 'addCloseHandler(ClickHandler handler)'
 *
 */
public class DialogBox extends PopupPanel{
	
	public DecoratedPanel decPanel=new DecoratedPanel();
	
	public DialogBox() {
		this.add(decPanel);
	}
	
	public DialogBox(String header,Widget content) {
		this.decPanel.setHeader(header);
		this.decPanel.addContent(content);
		this.add(decPanel);
		this.setGlassEnabled(true);
		this.setGlassStyleName(DecoratedPanel.getResources().css().whiteout());
	}
	
	public void addDialogBoxStyleName(String styleName) {
		this.addStyleName(styleName);
	}
	
	public void addDecPanStyleName(String styleName) {
		this.decPanel.addStyleName(styleName);
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
	public void addCloseHandler(ClickHandler handler) {
		//this.decPanel.close.setVisible(true);
		this.decPanel.addCloseHandler(handler);
	}
}

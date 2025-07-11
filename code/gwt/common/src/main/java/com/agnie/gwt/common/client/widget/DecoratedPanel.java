/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Creates an Decorated panel with the specified contents inside a panel content element. content element can contain a
 * child widget. close,min,max Button only visible in respective clickHandlers.
 * 
 */
public class DecoratedPanel extends Composite {
	private static DecoratedPanelResources	resource	= DecoratedPanelResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, DecoratedPanel> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	protected HTMLPanel			headerPan;
	@UiField
	protected SpanElement		headerText;
	@UiField
	protected Anchor			close;
	@UiField
	protected Anchor			minimize;
	@UiField
	protected Anchor			maxmize;
	protected HTMLPanel			container;
	@UiField
	protected HTMLPanel			contentPan;

	public DecoratedPanel() {
		this(null, null);
	}

	public DecoratedPanel(String header) {
		this(header, null);
	}

	public DecoratedPanel(String header, Widget content) {

		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);

		headerPan.addStyleName(resource.css().headerPan());
		contentPan.addStyleName(resource.css().contentPan());
		container.addStyleName(resource.css().decPanel());

		setHeader(header);
		addContent(content);
		this.close.setVisible(false);
		this.maxmize.setVisible(false);
		this.minimize.setVisible(false);
	}

	/**
	 * sets header title
	 * 
	 * @param header
	 *            String title for header
	 */
	public void setHeader(String header) {
		if (header != null && !("".equals(header))) {
			this.headerText.setInnerText(header);
		}
	}

	/**
	 * add widget to panel
	 * 
	 * @param content
	 *            widget to add
	 */
	@UiChild
	public void addContent(Widget content) {
		if (content != null) {
			this.contentPan.add(content.asWidget());
		}
	}

	/**
	 * remove an widget from panel content
	 * 
	 * @param content
	 *            widget to remove
	 */
	public void remove(Widget content) {
		if (content != null) {
			this.contentPan.remove(content);
		}
	}

	/**
	 * clears the content element of decorated panel
	 */
	public void clear() {
		this.contentPan.clear();
	}

	/**
	 * clickHandler for close button.Also sets close button visible.
	 * 
	 * @param handler
	 *            click handler for close button.
	 */
	public void addCloseHandler(ClickHandler handler) {
		this.close.setVisible(true);
		this.close.addClickHandler(handler);
	}

	/**
	 * clickHandler for minimise button.Also sets minimise button visible.
	 * 
	 * @param handler
	 *            click handler for minimise button.
	 */
	public void minimizeClickHandler(ClickHandler handler) {
		this.minimize.setVisible(true);
		this.minimize.addClickHandler(handler);
	}

	/**
	 * clickHandler for maximise button.Also sets maximise button visible.
	 * 
	 * @param handler
	 *            click handler for maximise button.
	 */
	public void maxmizeClickHandler(ClickHandler handler) {
		this.maxmize.setVisible(true);
		this.maxmize.addClickHandler(handler);
	}

	public static DecoratedPanelResources getResources() {
		return resource;
	}
}

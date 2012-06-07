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
 *
 * 
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

	public DecoratedPanel(String header, Widget content) {

		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		headerPan.addStyleName(resource.css().headerPan());
		contentPan.addStyleName(resource.css().contentPan());
		container.addStyleName(resource.css().decPanel());
		initWidget(container);

		setHeader(header);
		addContent(content);
		this.close.setVisible(false);
		this.maxmize.setVisible(false);
		this.minimize.setVisible(false);
	}

	public void setHeader(String header) {
		if (header != null && !("".equals(header))) {
			this.headerText.setInnerText(header);
		}
	}

	@UiChild
	public void addContent(Widget content) {
		if (content != null) {
			this.contentPan.add(content);
		}
	}
	/**
	 * 
	 * @param content
	 */
	public void remove(Widget content) {
		if (content != null) {
			this.contentPan.remove(content);
		}
	}

	public void clear() {
		this.contentPan.clear();
	}

	public void closeClickHandler(ClickHandler handler) {
		this.close.setVisible(true);
		this.close.addClickHandler(handler);
	}

	public void minimizeClickHandler(ClickHandler handler) {
		this.minimize.setVisible(true);
		this.minimize.addClickHandler(handler);
	}

	public void maxmizeClickHandler(ClickHandler handler) {
		this.maxmize.setVisible(true);
		this.maxmize.addClickHandler(handler);
	}

	public static DecoratedPanelResources getResources() {
		return resource;
	}
}

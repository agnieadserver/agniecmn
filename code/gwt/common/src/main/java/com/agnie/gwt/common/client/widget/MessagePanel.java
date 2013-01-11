package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * a common widget to display messages like info, error and warning.
 * 
 * 
 */
public class MessagePanel extends Composite {
	private static MessagePanelResources	resource	= MessagePanelResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, MessagePanel> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	protected HTMLPanel			container;
	@UiField
	HTMLPanel					messagePan;
	@UiField
	ImageElement				img;
	@UiField
	HTML						message;
	@UiField
	Anchor						close;
	public String				type;

	public MessagePanel() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);

		close.addStyleName(resource.css().closeBtn());
		close.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				container.setVisible(false);
			}
		});
	}

	/**
	 * 
	 * enum to set Message type Error Warning Information etc.
	 * 
	 */
	public static enum MessageType {
		ERROR("Error_Message"), WARNING("Warning_Message"), INFORMATION("Information_Message");

		private String	key;

		private MessageType(String key) {
			this.key = key;
		}

		/**
		 * @return the key
		 */
		public String getKey() {
			return key;
		}
	}

	/**
	 * sets Message in Message Panel.
	 * 
	 * @param String
	 *            message.
	 */
	public void setMessage(String message) {
		this.message.setText(message);
	}
	
	/**
	 * sets Message in MessagePanel 
	 * @param html
	 */

	
	public void setMessage(SafeHtml html) {
		this.message.setHTML(html);
	}

	/**
	 * sets height of Message Panel.
	 */
	public void setHeight(String height) {
		container.setHeight(height);
	}

	/**
	 * sets width of Message Panel.
	 */
	public void setWidth(String width) {
		container.setWidth(width);
	}

	/**
	 * sets type of message like ERROR,WARNING,INFO.
	 * 
	 * @param enum
	 *            MessageType
	 */
	public void setType(MessageType mt) {
		type = new String();
		type = mt.toString();
		GWT.log("type==" + mt.toString());
		if (type.equals("ERROR")) {
			messagePan.addStyleName(resource.css().errorMessagePan());
			message.addStyleName(resource.css().errorMessage());
			img.addClassName(resource.css().error());
		} else if (type.equals("WARNING")) {
			messagePan.addStyleName(resource.css().warningMessagePan());
			message.addStyleName(resource.css().warningMessage());
			img.addClassName(resource.css().warning());

		} else if (type.equals("INFORMATION")) {
			messagePan.addStyleName(resource.css().infoMessagePan());
			message.addStyleName(resource.css().infoMessage());
			img.addClassName(resource.css().info());
		}
	}

	public static MessagePanelResources getResources() {
		return resource;
	}
	
	@UiFactory
	public static String getBasePath() {
		return GWT.getModuleBaseURL();
	}

}

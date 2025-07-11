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
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Its common widget which will encapsulate all the components related to one form filed
 * <ul>
 * <li>Label of a field</li>
 * <li>Can mark required field</li>
 * <li>Container for input field</li>
 * <li>Place to show validation error message</li>
 * </ul>
 * 
 */
public class FormFieldContainer extends Composite {

	private static FormFieldResources	resource	= FormFieldResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, FormFieldContainer> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	protected SpanElement		label;
	@UiField
	protected SpanElement		required;
	@UiField
	protected SpanElement		error;
	@UiField
	protected SpanElement		desc;
	protected HTMLPanel			container;
	@UiField
	protected SimplePanel		inputContainer;

	@UiField
	DivElement					labelDiv;

	public FormFieldContainer() {
		this(null, null, false);
	}

	public boolean				autoHide	= false;
	private Timer				timer		= null;
	private static final int	TIMEOUT		= 20000;

	public FormFieldContainer(String label, Widget inputFieldContainer, boolean required) {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		container.addStyleName(resource.css().formFieldContainer());
		initWidget(container);
		setLabel(label);
		addInputFieldContainer(inputFieldContainer);
		setRequired(required);

		timer = new Timer() {

			@Override
			public void run() {
				errorFixed();
			}
		};
	}

	public void setLabel(String label) {
		if (label != null && !("".equals(label))) {
			this.label.setInnerText(label);
		}
	}

	public void setDesc(String desc) {
		if (desc != null && !("".equals(desc))) {
			this.desc.setInnerText(desc);
		}
	}

	@UiChild
	public void addInputFieldContainer(Widget inputFieldContainer) {
		if (inputFieldContainer != null) {
			inputContainer.add(inputFieldContainer);
		}
	}

	public void setRequired(boolean required) {
		if (required) {
			this.required.setInnerText("*");
		} else {
			this.required.setInnerText("");
		}
	}

	public void setError(String errorMessage, boolean autoHide) {
		if (autoHide) {
			timer.schedule(TIMEOUT);
		}
		inputContainer.getElement().getFirstChildElement().addClassName(resource.css().formFieldError());
		this.error.setInnerText(errorMessage);
	}

	public void errorFixed() {
		inputContainer.getElement().getFirstChildElement().removeClassName(resource.css().formFieldError());
		this.error.setInnerText("");
	}

	@UiFactory
	public static FormFieldResources getResources() {
		return resource;
	}

}

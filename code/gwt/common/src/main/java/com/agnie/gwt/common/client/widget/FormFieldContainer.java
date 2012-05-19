package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Its common widget which will encapsulate all the components related to one form filed
 * <p>
 * <ul>
 * <li>Label of a field</li>
 * <li>Can mark required field</li>
 * <li>Container for input field</li>
 * <li>Place to show validation error message</li>
 * </ul>
 * </p>
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
	protected HTMLPanel			container;
	@UiField
	protected HTMLPanel			inputContainer;

	public FormFieldContainer() {
		this(null, null, false);
	}

	public FormFieldContainer(String label, Widget inputFieldContainer, boolean required) {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		container.addStyleName(resource.css().formFiledContainer());
		inputContainer.addStyleName(resource.css().formFiledInput());
		initWidget(container);
		setLabel(label);
		addInputFieldContainer(inputFieldContainer);
		setRequired(required);
	}

	public void setLabel(String label) {
		if (label != null && !("".equals(label))) {
			this.label.setInnerText(label);
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

	public void setError(String errorMessage) {
		container.addStyleName(resource.css().formFiledError());
		this.error.setInnerText(errorMessage);
	}

	public void errorFixed() {
		container.removeStyleName(resource.css().formFiledError());
		this.error.setInnerText("");
	}

	@UiFactory
	public static FormFieldResources getResources() {
		return resource;
	}

}

/*******************************************************************************
 * ? 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FormPanel extends Composite {
	private static FormPanelResources	resource	= FormPanelResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, FormPanel> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	public FormPanel() {
		this(resource.css().formPanel());
	}

	@UiField
	Label				label;
	@UiField
	HTMLPanel			formContainer;

	protected HTMLPanel	container;

	public FormPanel(String styleClassName) {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		container.addStyleName(styleClassName);
		initWidget(container);
	}

	public void setFormPanWidth(String width) {
		this.container.setWidth(width);
	}

	public void setFormPanHeight(String height) {
		this.container.setHeight(height);
	}

	public void setFormContWidth(String width) {
		this.formContainer.setWidth(width);
	}

	public void setFormContHeight(String height) {
		this.formContainer.setHeight(height);
	}

	public void setLabel(String label) {
		this.label.setText(label);
	}

	@UiChild
	public void addFormContainer(Widget formContainer) {
		if (formContainer != null) {
			this.formContainer.add(formContainer);
		}
	}

	@UiFactory
	public static FormPanelResources getResources() {
		return resource;
	}
}

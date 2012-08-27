package com.agnie.gwt.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * StyledListBox with method to add any listbox widget.
 * In this the dropDown css is changed.
 */
public class StyledListBox extends Composite {
	private static StyledListBoxResources	resource	= StyledListBoxResources.INSTANCE;

	static {
		resource.css().ensureInjected();
	}

	interface MyUiBinder extends UiBinder<Widget, StyledListBox> {
	}

	private static MyUiBinder	uiBinder		= GWT.create(MyUiBinder.class);


	protected HTMLPanel			container;

	public StyledListBox() {
		container = (HTMLPanel) uiBinder.createAndBindUi(this);
		initWidget(container);
	}
	
	/**
	 * Sets ListBox widget in main container.
	 * @param listBox
	 */
	@UiChild
	public void addListBox(Widget listBox){
		if(listBox!=null){
		container.add(listBox);
		}
	}

	public static StyledListBoxResources getResources() {
		return resource;
	}

}

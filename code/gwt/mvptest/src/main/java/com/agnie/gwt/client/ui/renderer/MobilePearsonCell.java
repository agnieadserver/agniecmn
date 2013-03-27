package com.agnie.gwt.client.ui.renderer;

import com.agnie.gwt.client.Person;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;

public class MobilePearsonCell extends AbstractCell<Person> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, Person person);
	}

	private static MyUiRenderer	renderer	= GWT.create(MyUiRenderer.class);

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, Person value, SafeHtmlBuilder sb) {
		renderer.render(sb, value);
	}

}

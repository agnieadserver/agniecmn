package com.agnie.gwt.client.renderer;

import com.agnie.gwt.common.client.renderer.TitleString;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;

public class CustomListCell extends AbstractCell<TitleString>{
	
	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, TitleString string);
	}

	private static MyUiRenderer	renderer	= GWT.create(MyUiRenderer.class);
	
	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, TitleString value, SafeHtmlBuilder sb) {
		renderer.render(sb, value);
	}
}

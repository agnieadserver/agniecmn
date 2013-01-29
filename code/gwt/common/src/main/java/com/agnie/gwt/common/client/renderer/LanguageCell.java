package com.agnie.gwt.common.client.renderer;

import com.agnie.common.gwt.serverclient.client.enums.Language;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;

public class LanguageCell extends AbstractCell<Language> {

	interface MyUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, Language string);
	}

	private static MyUiRenderer	renderer	= GWT.create(MyUiRenderer.class);

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, Language value, SafeHtmlBuilder sb) {
		renderer.render(sb, value);
	}
}

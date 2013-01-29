package com.agnie.gwt.common.client.renderer;

import com.agnie.common.gwt.serverclient.client.renderer.Title;

public class TitleString implements Title {
	String	string;

	public TitleString() {

	}

	public TitleString(String string) {
		super();
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getTitle() {
		return string;
	}

}

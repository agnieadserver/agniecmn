package com.agnie.gwt.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.client.renderer.PearsonCell;
import com.agnie.gwt.client.renderer.Person;
import com.agnie.gwt.common.client.widget.CustomListBox;
import com.agnie.gwt.common.client.widget.FormFieldContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class HomeContentView extends Composite {
	interface MyUiBinder extends UiBinder<Widget, HomeContentView> {
	}

	private static MyUiBinder	uiBinder	= GWT.create(MyUiBinder.class);

	@UiField
	FormFieldContainer errorTest;
	@UiField
	HTMLPanel custListPan;
	
	CustomListBox<Person> custListBox= new CustomListBox<Person>(new PearsonCell());
	
	public HomeContentView() {
		initWidget(uiBinder.createAndBindUi(this));
		errorTest.setError("ThisField is required", false);
		setCustomListData();
	}
	
	public void setCustomListData(){
		this.custListBox.setList(createPersonList());
		custListPan.add(custListBox);
	}
	
	public List<Person> createPersonList() {
		List<Person> list = new ArrayList<Person>();
		for (int i = 0; i < 4; i++) {
			Person p = new Person();
			p.setFname("PersonF" + i);
			p.setLname("PersonL" + i);
			p.setEmailid("person@agnie." + i + ".co." + "in");
			list.add(p);
		}
		return list;
	}

}

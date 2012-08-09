package com.agnie.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.client.renderer.UserRenderer;
import com.agnie.gwt.common.client.widget.DecoratedPanel;
import com.agnie.gwt.common.client.widget.LabelPasswordBox;
import com.agnie.gwt.common.client.widget.LabelTextBox;
import com.agnie.gwt.common.client.widget.MessagePanel;
import com.agnie.gwt.common.client.widget.MessagePanel.MessageType;
import com.agnie.gwt.common.client.widget.SearchBox;
import com.agnie.gwt.common.client.widget.SearchBoxResources;
import com.agnie.gwt.common.client.widget.SuggestionBox;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class samples implements EntryPoint {
	LabelPasswordBox					lpb			= new LabelPasswordBox();
	LabelTextBox						ltb			= new LabelTextBox();
	Button								save		= new Button("save");
	MessagePanel						mpi			= new MessagePanel();
	MessagePanel						mpe			= new MessagePanel();
	MessagePanel						mpw			= new MessagePanel();
	TextBox								tb			= new TextBox();
	List<String>						celllist	= new ArrayList<String>();
	SuggestionBox						sb			= new SuggestionBox();
	DecoratedPanel						dp;
	SearchBox							searchBox	= new SearchBox();
	Image								img			= new Image();
	private static SearchBoxResources	resource	= SearchBoxResources.INSTANCE;

	public void onModuleLoad() {
		GWT.log("IN onmoduleLoad Start.");
		// RendererSample rendSample = new RendererSample();
		UserRenderer ur = new UserRenderer();
		for (int i = 0; i < 5; i++) {
			celllist.add("sug" + i);
		}
		sb.setData(celllist);
		sb.setWidth("300px");
		sb.setHeight("300px");
		dp = new DecoratedPanel("Decorated Panel");

		dp.addContent(save);
		dp.addContent(tb);
		dp.closeClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				dp.getParent().removeFromParent();
			}
		});
		ltb.setLabel("username");
		lpb.setLabel("password");
		GWT.log("In Entry point ltb.getLabel==" + ltb.getLabel() + "ltb.getText()==" + ltb.getText());
		save.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				GWT.log("In Entry point save ltb.getLabel==" + ltb.getLabel() + "ltb.getText()==" + ltb.getText());
			}
		});
		mpi.setType(MessageType.INFORMATION);
		mpi.setMessage("Information: infromation information height set nothing");
		// mpi.setHeight("20px");
		mpe.setType(MessageType.ERROR);
		mpe.setMessage("Error:error error height set to 40px");
		mpe.setHeight("40px");
		mpw.setType(MessageType.WARNING);
		mpw.setMessage("Warning:warning warning height set to 60 px");
		mpw.setHeight("60px");

		searchBox.setSize("200px", "20px");
		searchBox.setLabel("suresh");
		img.setUrl("images/search.png");
		img.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Window.alert("You are finding " + searchBox.getValue());
			}
		});
		searchBox.addInputWidget(img);
		// RootPanel.get().add(ltb);
		// RootPanel.get().add(save);
		// RootPanel.get().add(mpi);
		// RootPanel.get().add(mpw);
		// RootPanel.get().add(mpe);
		// RootPanel.get().add(lpb);
		// TAB Bar Test -- start
		// TabBarTest tabbar = new TabBarTest();
		// RootPanel.get().add(tabbar);
		// TAB Bar Test -- end

		// Bread Crumb Test -- start
		// BreadCrumbTest test = new BreadCrumbTest();
		// RootPanel.get().add(test);
		// Bread CrumbTest -- end

		// Wizard Bar Test -- start
		// WizardBarTest wbtest = new WizardBarTest();
		// RootPanel.get().add(wbtest);
		// Wizard Bar Test -- end

		// Green Button Test -- start
		// GreenButton btn = new GreenButton("This is just a test");
		// RootPanel.get().add(btn);
		// Green Button Test -- end

		// Form Field Test -- start
		// HomeContentView view = new HomeContentView();
		// RootPanel.get().add(view);
		// Form Field Test -- end
		RootPanel.get().add(searchBox);
	}
}

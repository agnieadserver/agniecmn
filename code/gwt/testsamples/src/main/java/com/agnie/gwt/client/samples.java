package com.agnie.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.client.renderer.RendererSample;
import com.agnie.gwt.common.client.widget.Account;
import com.agnie.gwt.common.client.widget.BreadCrumbPanel;
import com.agnie.gwt.common.client.widget.CloseBtn;
import com.agnie.gwt.common.client.widget.CustomListBox;
import com.agnie.gwt.common.client.widget.DecoratedPanel;
import com.agnie.gwt.common.client.widget.LabelPasswordBox;
import com.agnie.gwt.common.client.widget.LabelTextBox;
import com.agnie.gwt.common.client.widget.MessagePanel;
import com.agnie.gwt.common.client.widget.PageTitle;
import com.agnie.gwt.common.client.widget.PasswordTextBox;
import com.agnie.gwt.common.client.widget.SearchBox;
import com.agnie.gwt.common.client.widget.SearchBoxResources;
import com.agnie.gwt.common.client.widget.StyledListBox;
import com.agnie.gwt.common.client.widget.SuggestionBox;
import com.agnie.gwt.common.client.widget.TextBox;
import com.agnie.gwt.common.client.widget.WizardBar;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class samples implements EntryPoint {
	LabelPasswordBox lpb = new LabelPasswordBox();
	LabelTextBox ltb = new LabelTextBox();
	Button save = new Button("save");
	MessagePanel mpi = new MessagePanel();
	MessagePanel mpe = new MessagePanel();
	MessagePanel mpw = new MessagePanel();
	TextBox tb = new TextBox();
	List<String> celllist = new ArrayList<String>();
	SuggestionBox sb = new SuggestionBox();
	DecoratedPanel dp = new DecoratedPanel();
	VerticalPanel dp1 = new VerticalPanel();
	SearchBox searchBox = new SearchBox();
	Image img = new Image();
	PageTitle pt = new PageTitle();
	StyledListBox slb = new StyledListBox();
	List<User> sel = new ArrayList<User>();
	List<User> av = new ArrayList<User>();
	Account acc = new Account();
	Account acc1 = new Account();
	Account acc2 = new Account();
	CustomListBox clb = new CustomListBox();
	User us = new User();
	BreadCrumbPanel bcp = new BreadCrumbPanel();
	private static SearchBoxResources resource = SearchBoxResources.INSTANCE;
	TextBox tB = new TextBox();
	PasswordTextBox ptb = new PasswordTextBox();
	WizardBar wb = new WizardBar();
	CloseBtn cb = new CloseBtn();

	public void onModuleLoad() {
		// GWT.log("IN onmoduleLoad Start.");
		// img.setUrl("images/search.png");
		// bcp.addBreadCrumb("BreadCrumb1",img);
		// bcp.addBreadCrumb("BreadCrumb2");
		// bcp.addBreadCrumb("BreadCrumb3");
		// for(int i=0;i<bcp.getBreadCrumbCount();i++){
		// GWT.log("breadcrumbcount=="+bcp.getBreadCrumbCount());
		// GWT.log(bcp.getBreadCrumbText(i));
		//
		// }
		// int i=bcp.getBreadCrumbCount()-1;
		// GWT.log("last node index=="+i);
		// RootPanel.get().add(bcp);
		// RendererSample rendSample = new RendererSample();
		// RootPanel.get().add(rendSample);
		// UserRenderer ur = new UserRenderer();
		// for (int i = 0; i < 5; i++) {
		// celllist.add("sug" + i);
		// }
		// sb.setData(celllist);
		// sb.setWidth("300px");
		// sb.setHeight("300px");
		// dp = new DecoratedPanel("Decorated Panel");
		//
		// dp.addContent(save);
		// dp.addContent(tb);
		// dp.closeClickHandler(new ClickHandler() {
		//
		// public void onClick(ClickEvent event) {
		// dp.getParent().removeFromParent();
		// }
		// });
		// ltb.setLabel("username");
		// lpb.setLabel("password");
		// GWT.log("In Entry point ltb.getLabel==" + ltb.getLabel() +
		// "ltb.getText()==" + ltb.getText());
		// save.addClickHandler(new ClickHandler() {
		//
		// public void onClick(ClickEvent event) {
		// GWT.log("In Entry point save ltb.getLabel==" + ltb.getLabel() +
		// "ltb.getText()==" + ltb.getText());
		// }
		// });
		// mpi.setType(MessageType.INFORMATION);
		// mpi.setMessage("Information: infromation information height set nothing");
		// // mpi.setHeight("20px");
		// mpe.setType(MessageType.ERROR);
		// mpe.setMessage("Error:error error height set to 40px");
		// mpe.setHeight("40px");
		// mpw.setType(MessageType.WARNING);
		// mpw.setMessage("Warning:warning warning height set to 60 px");
		// mpw.setHeight("60px");
		//
		// searchBox.setSize("200px", "20px");
		// searchBox.setLabel("suresh");
		// img.setUrl("images/search.png");
		// img.addClickHandler(new ClickHandler() {
		//
		// public void onClick(ClickEvent event) {
		// Window.alert("You are finding " + searchBox.getValue());
		// }
		// });
		// searchBox.addInputWidget(img);
		// RootPanel.get().add(searchBox);
		//
		// pt.setPageTitle("Title for page1");
		// pt.addTitleImage(img);

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
		 WizardBarTest wbtest = new WizardBarTest();
		 RootPanel.get().add(wbtest);
		// Wizard Bar Test -- end

		// Green Button Test -- start
		// GreenButton btn = new GreenButton("This is just a test");
		// RootPanel.get().add(btn);
		// Green Button Test -- end

		// Form Field Test -- start
		// HomeContentView view = new HomeContentView();
		// RootPanel.get().add(view);
		// Form Field Test -- end
		// RootPanel.get().add(pt);
		// RootPanel.get().add(searchBox);
		// RootPanel.get().add(sb);
		// Locale Box Test -- start
		// LocaleListBox box = new LocaleListBox();
		// LocaleListBox box1 = new LocaleListBox();
		// slb.addListBox(box);
		// RootPanel.get().add(box1);
		// RootPanel.get().add(slb);
		// Locale Box Test -- end

		// ListBoxt Test -- start
		// HorizontalPanel hlpanel = new HorizontalPanel();
		// final ListBoxTest lbTest = new ListBoxTest();
		// hlpanel.add(lbTest);
		// Button btnlbTest = new Button("List Box Test");
		// hlpanel.add(btnlbTest);
		// btnlbTest.addClickHandler(new ClickHandler() {
		//
		// public void onClick(ClickEvent arg0) {
		// Window.alert("User = >" + lbTest.getSelected());
		// }
		// });
		// RootPanel.get().add(hlpanel);
		// ListBoxt Test -- end

		// ListBoxt Test -- start
		// VerticalPanel vselunPanel = new VerticalPanel();
		// final SelectUnselectTest selTest = new SelectUnselectTest();
		// vselunPanel.add(selTest);
		// Button shwAva = new Button("Show available List");
		// vselunPanel.add(shwAva);
		// shwAva.addClickHandler(new ClickHandler() {
		//
		// public void onClick(ClickEvent arg0) {
		// selTest.showAvailable();
		// }
		// });
		// Button shwSel = new Button("Show Selected List");
		// vselunPanel.add(shwSel);
		// shwSel.addClickHandler(new ClickHandler() {
		//
		// public void onClick(ClickEvent arg0) {
		// selTest.showSelected();
		// }
		// });
		// RootPanel.get().add(vselunPanel);
		// //ListBoxt Test -- end
		// acc.setAccName("UserName");
		// acc1.setAccName("UserName1" + "Username1");
		// acc2.setAccName("UserName2" + "UserName22222");
		// dp1.add(acc);
		// dp1.add(acc1);
		// dp1.add(acc2);
		//
		// RootPanel.get().add(acc);
		// clb.setWidth("100px");
		// RootPanel.get().add(clb);

		//
		// tB.setErrorMessage("data entered is not valid plz enter your name");
		// tB.setErrorMessVisible(true);
		// tB.setErrorPanWidth(400);
		// tB.setText("username");
		// ltb.setErrorMessage("data entered is not valid plz enter your name");
		// ltb.setErrorMessVisible(true);
		// ltb.setErrorPanWidth(400);
		// lpb.setErrorMessage("data entered is not valid plz enter your password");
		// lpb.setErrorMessVisible(true);
		// lpb.setErrorPanWidth(400);
		// //dp1.add(ltb);
		// dp1.add(lpb);
		// dp1.add(mpw);
		// dp1.add(mpe);

		// ptb.setErrorMessage("data entered is not valid plz enter your password");
		// ptb.setErrorMessVisible(true);
		// ptb.setErrorPanWidth(400);
		// ptb.setText("password");
		// dp1.add(ptb);
		// wb.addStep("step1");
		// wb.addStep("step2");
		// RootPanel.get().add(wb);
//		cb.addClickHandler(new ClickHandler() {
//
//			public void onClick(ClickEvent event) {
//				GWT.log("close clicked");
//				Window.alert("Don't click again");
//			}
//		});
//		RootPanel.get().add(cb);
	}
}

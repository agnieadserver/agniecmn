package com.agnie.gwt.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.agnie.common.gwt.serverclient.client.dto.UserAccount;
import com.agnie.gwt.client.renderer.CustomListCell;
import com.agnie.gwt.client.renderer.PearsonCell;
import com.agnie.gwt.client.renderer.Person;
import com.agnie.gwt.client.ui.HomeContentView;
import com.agnie.gwt.common.client.base.BasePageResourceLoader;
import com.agnie.gwt.common.client.renderer.TitleString;
import com.agnie.gwt.common.client.widget.Account;
import com.agnie.gwt.common.client.widget.BreadCrumbPanel;
import com.agnie.gwt.common.client.widget.CloseBtn;
import com.agnie.gwt.common.client.widget.CustomListBox;
import com.agnie.gwt.common.client.widget.CustomMenuPan;
import com.agnie.gwt.common.client.widget.DandD;
import com.agnie.gwt.common.client.widget.DandD.BarValueChangedEvent;
import com.agnie.gwt.common.client.widget.DandD.BarValueChangedHandler;
import com.agnie.gwt.common.client.widget.DandD.Position;
import com.agnie.gwt.common.client.widget.DandDDrag;
import com.agnie.gwt.common.client.widget.DecoratedPanel;
import com.agnie.gwt.common.client.widget.GreenButton;
import com.agnie.gwt.common.client.widget.LabelPasswordBox;
import com.agnie.gwt.common.client.widget.LabelTextBox;
import com.agnie.gwt.common.client.widget.Loader;
import com.agnie.gwt.common.client.widget.LoaderResources;
import com.agnie.gwt.common.client.widget.LocaleListBox;
import com.agnie.gwt.common.client.widget.MessagePanel;
import com.agnie.gwt.common.client.widget.MessagePanel.MessageType;
import com.agnie.gwt.common.client.widget.PageTitle;
import com.agnie.gwt.common.client.widget.PasswordTextBox;
import com.agnie.gwt.common.client.widget.SearchBox;
import com.agnie.gwt.common.client.widget.SlideButton;
import com.agnie.gwt.common.client.widget.SlideButtonDrag;
import com.agnie.gwt.common.client.widget.SlideButtonScale;
import com.agnie.gwt.common.client.widget.SuggestionBox;
import com.agnie.gwt.common.client.widget.TextBox;
import com.agnie.gwt.common.client.widget.WizardBar;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.validation.client.impl.Validation;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class samples implements EntryPoint {

	// List<User> sel = new ArrayList<User>();
	// List<User> av = new ArrayList<User>();

	// User us = new User();

	// private static SearchBoxResources resource = SearchBoxResources.INSTANCE;

	// MenuPanTest menuPanTest = new MenuPanTest();

	BasePageResourceLoader	basePageResource	= new BasePageResourceLoader();

	public void onModuleLoad() {

		// GWT.log("IN onmoduleLoad Start.");

		// RendererSample rendSample = new RendererSample();
		// RootPanel.get().add(rendSample);

		// UserRenderer ur = new UserRenderer();

		// RootPanel.get().add(menuPanTest.getMenuPan());

		// messagePanelTest();
		// loaderTest();
		// formFieldTest();
		// labeledTextBoxPassBoxTest();
		// accPanTest();
		// textBoxTest();
		// customListBoxTest();
		// localeListBoxTest();
		// breadCrumbClassTest();
		// testValidation();
		// testSlideBar();
		// testSlideButtonScale();
		// testSlideButtonDrag();
		// testSlideButton();
		// listBoxTestV();
		// dialogBoxTest();
		// decoratedPanelTest();
		newDragAndDropTest();
		//labelPasswordTextBoxTest();
	}

	private void newDragAndDropTest() {
		// RootPanel.get().setPixelSize(600, 600);
		HTMLPanel hp = new HTMLPanel("");
		hp.setHeight("200px");
		hp.setWidth("300px");
	/*	final AbsolutePanel scale = new AbsolutePanel();
		scale.addStyleName("slide-button-scale");
		hp.add(scale);
		// create a DragController to manage drag-n-drop actions
		// note: This creates an implicit DropController for the boundary panel
		PickupDragController dragController = new PickupDragController(scale, true);
		final DandDDrag sbDrag = new DandDDrag();
		scale.add(sbDrag, -2, -2);*/
/*		sbDrag.addMouseUpHandler(new MouseUpHandler() {

			public void onMouseUp(MouseUpEvent event) {
				int x = sbDrag.getAbsoluteLeft();
				int y = sbDrag.getAbsoluteTop();
				int scaleX = scale.getAbsoluteLeft();
				int scaleY = scale.getAbsoluteTop();
				int scaleHFWidth = scale.getOffsetWidth() / 2;
				int dragHFWidth = sbDrag.getOffsetWidth() / 2;
				int cal = scaleX + scaleHFWidth - dragHFWidth;
				int leftPos = -2;
				if (x < cal) {
					leftPos = -2;
				} else {
					leftPos = sbDrag.getOffsetWidth() - 6;
				}
				sbDrag.getElement().setAttribute("style", "position: relative;" + "left:" + leftPos + "px ;");

				GWT.log("x=" + x + " y=" + y + " cal=" + cal + " mouseLeft=" + event.getScreenX());
				GWT.log("scale x=" + scaleX + " scale y=" + scaleY);
				GWT.log("scale half width" + scaleHFWidth + " dragHalfWidth=" + dragHFWidth);
			}
		});*/
		/*
		 * Label label=new Label("label", false); label.addStyleName("slide-button-drag"); scale.add(label, 0, 0);
		 */

		/*
		 * Widget hpDrag=new HTMLPanel("hpDrag"); hpDrag.addStyleName("slide-button-drag"); scale.add(hpDrag, 0, 0);
		 */

		/*
		 * AbsolutePanel abDrag=new AbsolutePanel(); abDrag.addStyleName("slide-button-drag"); scale.add(abDrag, 0, 0);
		 */

		//dragController.makeDraggable(sbDrag);
		
		/*dandD.setScale(scale);
		dandD.addDragWidget(sbDrag, 2, 2);*/
		final DandD dandD=new DandD();
		dandD.setDragPosition(Position.ONE);
		
		dandD.addBarValueChangedHandler(new BarValueChangedHandler() {
			
			public void onBarValueChanged(com.agnie.gwt.common.client.widget.DandD.BarValueChangedEvent event) {
				Window.alert("BarValueChanged="+event.getValue());
			}

		});
		
		Button b=new Button("Click");
		b.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				dandD.setDragPosition(Position.ZERO);
			}
		});
		
		hp.add(dandD);
		hp.add(b);
		RootPanel.get().add(hp);
		
	}

	private void testSlideButton() {
		final SlideButton sbh = new SlideButton();
		/*
		 * sbh.setLeftTitle("LeftTitle"); sbh.setRightTitle("RightTitle");
		 */
		/*sbh.addBarValueChangedHandler(new BarValueChangedHandler() {

			public void onBarValueChanged(BarValueChangedEvent event) {
				
				 * if (1 == event.getValue()) { sbh.getDragWidget().setWidth("96px"); } else {
				 * sbh.getDragWidget().setWidth("100px"); }
				 
				Window.alert("Bar value changed==" + event.getValue());
			}
		});
		sbh.addBarValueChangedHandler(new BarValueChangedHandler() {

			public void onBarValueChanged(BarValueChangedEvent event) {
				
				 * if (1 == event.getValue()) { sbh.getDragWidget().setWidth("96px"); } else {
				 * sbh.getDragWidget().setWidth("100px"); }
				 
				Window.alert("Bar value changedeeeee==" + event.getValue() + " ValueChangeHandlerRegsList size==" + sbh.getValueChangeHandlerRegsList().size());
			}
		});
		// sbh.clearBarValueChangeHandlers();

		RootPanel.get().add(sbh);*/
	}

	private void testSlideButtonDrag() {
		SlideButtonDrag sbd = new SlideButtonDrag();
		sbd.setHeight("30px");
		sbd.setWidth("50px");
		RootPanel.get().add(sbd);
	}

	private void testSlideButtonScale() {
		SlideButtonScale sbs = new SlideButtonScale();
		int width = 100;
		int height = 50;
		sbs.setHeight(String.valueOf(height - 4) + "px");
		sbs.setWidth(String.valueOf(width - 4) + "px");
		sbs.addMouseDownHandler(new MouseDownHandler() {

			public void onMouseDown(MouseDownEvent event) {
				Window.alert("Mouse down event=" + event.getX());
			}
		});
		RootPanel.get().add(sbs);
	}

	private void testSlideBar() {
		KDEHorizontalLeftBW sbh = new KDEHorizontalLeftBW(1, 100, 30);
		sbh.addStyleName("slide-button");
		/*sbh.addBarValueChangedHandler(new BarValueChangedHandler() {

			public void onBarValueChanged(BarValueChangedEvent event) {
				Window.alert("Bar value changed==" + event.getValue());
			}
		});*/
		sbh.setValue(0);
		RootPanel.get().add(sbh);
	}

	private void testValidation() {
		VerticalPanel vp = new VerticalPanel();
		final com.agnie.gwt.client.validation.Person p = new com.agnie.gwt.client.validation.Person();
		final TextBox tb = new TextBox();
		Button setTextBtn = new Button("SetText");
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(tb);
		hp.add(setTextBtn);

		final TextBox emailtb = new TextBox();
		Button setEmailBtn = new Button("setEmail");
		HorizontalPanel hp1 = new HorizontalPanel();
		hp1.add(emailtb);
		hp1.add(setEmailBtn);

		vp.add(hp);
		vp.add(hp1);
		RootPanel.get().add(vp);
		final javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		setTextBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				/*
				 * p.setName(tb.getText()); Window.alert("person name=="+p.getName()+" size="+p.getName().length() );
				 * Set<ConstraintViolation<com.agnie.gwt.client.validation.Person>> violations = validator.validate(p);
				 * //[message= Name must be at least 4 characters long., path= name, invalidValue=sd,
				 * desc=com.agnie.gwt.client.validation._PersonValidatorImpl$2@3909b7] for
				 * (ConstraintViolation<com.agnie.gwt.client.validation.Person> violation : violations) {
				 * Window.alert(violation.getPropertyPath()+violation.getMessage()); }
				 */
			}
		});
		setEmailBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				com.agnie.gwt.client.validation.Person p = new com.agnie.gwt.client.validation.Person();
				p.setEmail(emailtb.getText());
				javax.validation.Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
				Set<ConstraintViolation<com.agnie.gwt.client.validation.Person>> violations = validator.validate(p);
				Window.alert("viola==" + violations);
			}
		});

	}

	public void localeListBoxTest() {
		// Locale Box Test -- start
		LocaleListBox box = new LocaleListBox();
		box.addChangeHandler(new CustomListBox.ChangeHandler() {

			public void onChange() {
				Window.alert("Hello OnChange working");
			}

		});
		RootPanel.get().add(box);
		// Locale Box Test -- end
	}

	public void customListBoxTest() {
		CustomListCell clc = new CustomListCell();
		CustomListBox<TitleString> clb = new CustomListBox<TitleString>(clc);

		PearsonCell pCell = new PearsonCell();
		CustomListBox<Person> personCustomListBox = new CustomListBox<Person>(pCell);
		/* CustomListBox test starts here */
		clb.setWidth("100px");
		clb.setList(createDummyList());
		clb.setSelectedItem(createDummyList().get(5), true);

		clb.addChangeHandler(new CustomListBox.ChangeHandler() {

			public void onChange() {
				Window.alert("CustomListBox<TitleString>addChangeHandler working");
			}
		});

		personCustomListBox.setWidth("200px");
		personCustomListBox.setList(createPersonList());
		personCustomListBox.setSelectedItem(createPersonList().get(2), true);
		personCustomListBox.addChangeHandler(new CustomListBox.ChangeHandler() {

			public void onChange() {
				Window.alert("CustomListBox<Person> addChangeHandler working");
			}

		});

		RootPanel.get().add(personCustomListBox);

		/* CustomListBox test ends here */
	}

	public void customMenuPanTest() {
		CustomMenuPan cMenuPan = new CustomMenuPan();
		RootPanel.get().add(cMenuPan);
	}

	public void labeledTextBoxPassBoxTest() {
		final LabelTextBox ltb = new LabelTextBox("Labeled Text Box!");
		// final LabelPasswordBox lpb = new LabelPasswordBox("Labeled Password Box");
		final VerticalPanel dp1 = new VerticalPanel();
		Button errorMessBtn = new Button("SetErrorMessage");
		Button nextPageBtn = new Button("NextPage");
		Button resetBtn = new Button("resetTexBox");
		Button resetPassBtn = new Button("resetPassBox");
		Button getBtn = new Button("getTexBox");
		Button getPassBtn = new Button("getPassBox");

		dp1.add(ltb);
		dp1.add(errorMessBtn);// To test onDetach(textBox) for erroPan.hide
		dp1.add(nextPageBtn);// To test onDetach(textBox) for erroPan.hide
		// dp1.add(lpb);
		dp1.add(resetBtn);
		dp1.add(resetPassBtn);
		dp1.add(getBtn);
		dp1.add(getPassBtn);

		RootPanel.get().add(dp1);
		errorMessBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				ltb.setErrorMessage("required field", false);
			}
		});

		// ltb.setErrorPanWidth(200);
		// lpb.setErrorMessage("required field", true);
		// lpb.setErrorPanWidth(200);
		resetBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				ltb.reset();
			}
		});
		resetPassBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				// lpb.reset();
			}
		});
		getBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Window.alert("getValue==" + ltb.getValue());
			}
		});
		getPassBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				// Window.alert("getValue==" + lpb.getValue());
			}
		});
		nextPageBtn.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				dp1.clear();
				Window.Location.assign("http://127.0.0.1:8888/Test.html?gwt.codesvr=127.0.0.1:9997");
			}
		});
	}

	public void closeBtnTest() {
		CloseBtn cb = new CloseBtn();
		cb.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				GWT.log("close clicked");
				Window.alert("Don't click again");
			}
		});
		RootPanel.get().add(cb);
	}

	public void passwordTextBoxTest() {
		PasswordTextBox ptb = new PasswordTextBox();
		ptb.setErrorMessage("Invalid data ", true);
		ptb.setErrorMessVisible(true);
		ptb.setErrorPanWidth(400);
		ptb.setText("password");
		RootPanel.get().add(ptb);
	}

	public void textBoxTest() {
		TextBox tB = new TextBox();
		tB.setErrorMessVisible(true);
		tB.setText("username");
		RootPanel.get().add(tB);
		tB.setErrorMessage("Invalid data ", false);
	}

	public void wizardBarTes() {
		WizardBar wb = new WizardBar();
		wb.addStep("step1");
		wb.addStep("step2");
		RootPanel.get().add(wb);
	}

	public void formFieldTest() {
		// Form Field Test -- start
		HomeContentView view = new HomeContentView();
		RootPanel.get().add(view);
		// Form Field Test -- end
	}

	public void breadCrumbClassTest() {
		// Bread Crumb Test -- start
		BreadCrumbTest test = new BreadCrumbTest();
		RootPanel.get().add(test);
		// Bread CrumbTest -- end
	}

	public void tabBarTest() {
		// TAB Bar Test -- start
		TabBarTest tabbar = new TabBarTest();
		RootPanel.get().add(tabbar);
		// TAB Bar Test -- end
	}

	public void accPanTest() {
		Account acc = new Account();
		Account acc1 = new Account();
		Account acc2 = new Account();
		VerticalPanel dp1 = new VerticalPanel();

		UserAccount ua = new UserAccount();
		ua.setFirstName("firstName");
		ua.setLastName("lastName");
		acc.setUserAcc(ua);
		acc.addChangePassClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("Are you sure to change your password");
			}
		});
		acc.addModifyClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Window.alert("Are you sure to update your profile");
			}
		});
		acc.addLogoutClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Window.alert("Are you sure to logout");
			}
		});
		UserAccount ua1 = new UserAccount();
		ua1.setFirstName("firstName1");
		ua1.setLastName("lastName1");
		ua1.setUserImgUrl("images/personE.png");
		acc1.setUserAcc(ua1);

		UserAccount ua2 = new UserAccount();
		ua2.setFirstName("firstName2");
		ua2.setLastName("lastName2");
		ua2.setUserImgUrl("images/personE.png");
		acc2.setUserAcc(ua2);

		dp1.add(acc);
		dp1.add(acc1);
		dp1.add(acc2);
		RootPanel.get().add(dp1);
	}

	public void pageTitleTest() {
		PageTitle pt = new PageTitle();
		Image img = new Image();
		pt.setPageTitle("Title for page1");
		pt.addTitleImage(img);
		RootPanel.get().add(pt);
	}

	public void labelPasswordTextBoxTest() {
		LabelPasswordBox lpb = new LabelPasswordBox();
		final LabelTextBox ltb = new LabelTextBox();
		Button save = new Button("save");
		ltb.setLabel("username");
		lpb.setLabel("password");
		GWT.log("In Entry point ltb.getLabel==" + ltb.getLabel() + "ltb.getText()==" + ltb.getText());
		save.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				GWT.log("In Entry point save ltb.getLabel==" + ltb.getLabel() + "ltb.getText()==" + ltb.getText());
			}
		});
		RootPanel.get().add(ltb);
		RootPanel.get().add(save);
		RootPanel.get().add(lpb);
	}

	public void decoratedPanelTest() {
		TextBox tb = new TextBox();
		Button save = new Button("save");
		final DecoratedPanel dp = new DecoratedPanel("Decorated Panel");
		dp.addContent(save);
		dp.addContent(tb);
		dp.addCloseHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dp.getParent().removeFromParent();
			}
		});
		RootPanel.get().add(dp);
	}

	public void suggestionBoxTest() {
		List<String> celllist = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			celllist.add("sug" + i);
		}
		SuggestionBox sb = new SuggestionBox();
		sb.setData(celllist);
		sb.setWidth("300px");
		sb.setHeight("300px");
		RootPanel.get().add(sb);
	}

	public void searchBoxTest() {
		final SearchBox searchBox = new SearchBox();
		Image img = new Image();
		searchBox.setSize("200px", "20px");
		searchBox.setLabel("suresh");

		img.setUrl("images/search.png");
		img.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Window.alert("You are finding " + searchBox.getValue());
			}
		});
		searchBox.addInputWidget(img);
		RootPanel.get().add(searchBox);
	}

	public void dialogBoxTest() {
		HTMLPanel hp = new HTMLPanel("Hello all how are you!\n Its exciting to styling any widget\n is it?");
		CloseBtn cb = new CloseBtn();
		final DialogBoxTest dbt = new DialogBoxTest();
		dbt.getDialogBox().addCloseHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				dbt.getDialogBox().hide();
			}
		});
		dbt.getDialogBox().addContent(hp);
		dbt.getDialogBox().addContent(cb);
		dbt.getDialogBox().show();
	}

	public void breadCrumbTest() {
		// Wizard Bar Test -- start
		WizardBarTest wbtest = new WizardBarTest();
		RootPanel.get().add(wbtest);
		// Wizard Bar Test -- end
	}

	public void breadCrumbPanelTest() {
		BreadCrumbPanel bcp = new BreadCrumbPanel();
		Image img = new Image();

		img.setUrl("images/search.png");
		// bcp.addBreadCrumb("BreadCrumb1",img);
		bcp.addBreadCrumb("BreadCrumb2");
		bcp.addBreadCrumb("BreadCrumb3");
		for (int i = 0; i < bcp.getBreadCrumbCount(); i++) {
			GWT.log("breadcrumbcount==" + bcp.getBreadCrumbCount());
			GWT.log(bcp.getBreadCrumbText(i));
		}
		int i = bcp.getBreadCrumbCount() - 1;
		GWT.log("last node index==" + i);
		RootPanel.get().add(bcp);
	}

	public void greenBtnTest() {
		// Green Button Test -- start
		GreenButton btn = new GreenButton("This is just a test");
		RootPanel.get().add(btn);
		// Green Button Test -- end
	}

	public void listBoxTestV() {
		// ListBoxt Test -- start
		VerticalPanel vselunPanel = new VerticalPanel();
		final SelectUnselectTest selTest = new SelectUnselectTest();
		vselunPanel.add(selTest);
		Button shwAva = new Button("Show available List");
		vselunPanel.add(shwAva);
		shwAva.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {
				selTest.showAvailable();
			}
		});
		Button shwSel = new Button("Show Selected List");
		vselunPanel.add(shwSel);
		shwSel.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {
				selTest.showSelected();
			}
		});
		RootPanel.get().add(vselunPanel);
		// //ListBoxt Test -- end
	}

	public void listBoxTest() {
		// ListBoxt Test -- start
		HorizontalPanel hlpanel = new HorizontalPanel();
		final ListBoxTest lbTest = new ListBoxTest();
		hlpanel.add(lbTest);
		Button btnlbTest = new Button("List Box Test");
		hlpanel.add(btnlbTest);
		btnlbTest.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {
				Window.alert("User = >" + lbTest.getSelected());
			}
		});
		RootPanel.get().add(hlpanel);
		// ListBoxt Test -- end
	}

	public void messagePanelTest() {
		Button save = new Button("save");
		Button save1 = new Button("save1");
		Button save2 = new Button("save2");
		Button save3 = new Button("save3");
		final MessagePanel mpi = new MessagePanel();
		final MessagePanel mpe = new MessagePanel();
		final MessagePanel mpw = new MessagePanel();

		mpi.setType(MessageType.INFORMATION);
		mpi.setMessage("Information: infromation information height set nothing");

		mpi.setType(MessageType.ERROR);
		mpi.setMessage("ERRORINFORMATION: infromation information height set nothing");

		save.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				mpi.setType(MessageType.INFORMATION);
				mpi.setMessage("Information: infromation information height set nothing");
				mpi.show(true);
			}
		});
		save1.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				mpi.setType(MessageType.WARNING);
				mpi.setMessage("WARNInformation: infromation information height set nothing");
				mpi.show(true);
			}
		});
		save2.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				mpi.setType(MessageType.ERROR);
				mpi.setMessage("ERRORInformation: infromation information height set nothing");
				mpi.show(true);
			}
		});
		save3.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				mpi.setType(MessageType.WARNING);
				mpi.setMessage("WARNINGInformation: infromation information height set nothing");
				mpi.show(true);
			}
		});
		// mpi.setHeight("20px");
		mpe.setType(MessageType.ERROR);
		mpe.setMessage("Error:error error height set to 40px");
		mpe.setHeight("40px");
		mpw.setType(MessageType.WARNING);
		mpw.setMessage("Warning:warning warning height set to 60 px");
		mpw.setHeight("60px");
		mpw.setWidth("200px");

		mpe.show(false);
		mpw.show(false);
		RootPanel.get().add(mpi);
		RootPanel.get().add(mpw);
		RootPanel.get().add(mpe);
		RootPanel.get().add(save);
		RootPanel.get().add(save1);
		RootPanel.get().add(save2);
		RootPanel.get().add(save3);
	}

	private void loaderTest() {
		Button test = new Button("Test");
		RootPanel.get().add(test);
		test.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				Loader loader = new Loader(LoaderResources.INSTANCE.gettingSynced());
				loader.show();
			}
		});
	}

	public List<TitleString> createDummyList() {
		List<TitleString> list = new ArrayList<TitleString>();

		for (int i = 0; i < 10; i++) {
			list.add(new TitleString("UserName" + i));
		}

		return list;
	}

	public List<Person> createPersonList() {
		List<Person> list = new ArrayList<Person>();
		for (int i = 0; i < 5; i++) {
			Person p = new Person();
			p.setFname("PersonF" + i);
			p.setLname("PersonL" + i);
			p.setEmailid("person@agnie." + i + ".co." + "in");
			list.add(p);
		}
		return list;
	}
}

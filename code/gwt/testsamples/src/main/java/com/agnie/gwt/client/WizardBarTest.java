package com.agnie.gwt.client;

import com.agnie.gwt.common.client.widget.WizardBar;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WizardBarTest extends Composite {

	public WizardBarTest() {
		final WizardBar wizard = new WizardBar();
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":Add society details ", "");
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":Add member details ", "");
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":Add parking details ", "");
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":Add society details ", "");
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":Add member details ", "");
		wizard.selectStep(4);

		VerticalPanel panel = new VerticalPanel();
		panel.add(wizard);
		Button next = new Button("Next");
		next.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				wizard.nextStep();
			}
		});

		Button back = new Button("Back");
		back.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				wizard.selectStep(2);
			}
		});
		wizard.addSelectionHandler(new SelectionHandler<Integer>() {

			public void onSelection(SelectionEvent<Integer> event) {
				switch (event.getSelectedItem()) {
				case 0:
					Window.alert("First Step");
					break;
				case 1:
					Window.alert("Second Step");
					break;
				case 2:
					Window.alert("Third Step");
					break;
				default:
					break;
				}
			}
		});
		panel.add(next);
		panel.add(back);
		initWidget(panel);
	}
}

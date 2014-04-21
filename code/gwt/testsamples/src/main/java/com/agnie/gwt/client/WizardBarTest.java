/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
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
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":", "Add society details");
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":", "Add member details");
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":", "Add parking details");
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":", "Add society details");
		wizard.addStep("Step " + WizardBar.NO_MACRO + ":", "Add member details");
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

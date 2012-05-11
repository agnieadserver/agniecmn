package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.HasBeforeSelectionHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * This wizard bar doesn't support click action on any of the steps in any state. If required the support need to be
 * added in this class it self.
 */
public class WizardBar extends Composite implements HasBeforeSelectionHandlers<Integer>, HasSelectionHandlers<Integer> {

	public interface Step {
	}

	private class ClickDelegatePanel extends Composite implements Step {

		public static final String	INACTIVE_STYLE	= "inactive";
		public static final String	ACTIVE_STYLE	= "active";
		public static final String	DONE_STYLE		= "done";
		public static final String	LAST_DONE_STYLE	= "last-done";
		public static final String	FINAL_STYLE		= "final";

		private SimplePanel			simplePanel;
		private boolean				enabled			= true;

		ClickDelegatePanel(Widget child) {

			simplePanel = new SimplePanel();
			simplePanel.setWidget(child);
			initWidget(simplePanel);
			simplePanel.addStyleName(INACTIVE_STYLE);
		}

		@SuppressWarnings("unused")
		public SimplePanel getPanel() {
			return simplePanel;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

	}

	private static final String	WIZARD_CONTAINER_DEFAULT_STYLE	= "wizard";
	private HTMLPanel			panel;
	private Widget				selected;
	public static final String	NO_MACRO						= "#NO&";

	public WizardBar() {
		this(WIZARD_CONTAINER_DEFAULT_STYLE);
	}

	/**
	 * Creates an empty Wizard bar.
	 */
	public WizardBar(String styleClassName) {
		panel = new HTMLPanel("");
		initWidget(panel);
		panel.setStyleName(styleClassName);
	}

	public void setStyleClassName(String styleClassName) {
		panel.setStyleName(styleClassName);
	}

	public HandlerRegistration addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler) {
		return addHandler(handler, BeforeSelectionEvent.getType());
	}

	public HandlerRegistration addSelectionHandler(SelectionHandler<Integer> handler) {
		return addHandler(handler, SelectionEvent.getType());
	}

	public void addStep(String text) {
		addStep(null, text);
	}

	/**
	 * Adds a new Step in wizard with specified text.
	 * 
	 * @param text
	 *            the new Step's text
	 */
	public void addStep(String label, String text) {
		StringBuffer html = new StringBuffer();
		if (label != null) {
			html.append("<em>" + processMacro(label) + "</em>");
		} else {
			html.append("<em>" + (getStepCount() + 1) + "</em>");
		}
		html.append("<span>" + text + "</span>");

		Anchor anchor = new Anchor();
		anchor.setHTML(html.toString());
		addStep(anchor);
	}

	private String processMacro(String text) {
		return text.replace(NO_MACRO, "" + (getStepCount() + 1));
	}

	/**
	 * Adds a new Step in wizard with specified widget.
	 * 
	 * @param text
	 *            the new Step's text
	 */
	public void addStep(Widget step) {
		int currentCount = getStepCount();
		// Remove "final" style class from previous step widget
		if (currentCount != 0) {
			ClickDelegatePanel p = (ClickDelegatePanel) panel.getWidget(currentCount - 1);
			p.removeStyleName(ClickDelegatePanel.FINAL_STYLE);
		}
		ClickDelegatePanel delWidget = new ClickDelegatePanel(step);
		delWidget.addStyleName(ClickDelegatePanel.FINAL_STYLE);
		panel.add(delWidget);
	}

	/**
	 * Gets the index of Step that is currently selected.
	 * 
	 * @return the selected Step
	 */
	public int getSelectedStep() {
		if (selected == null) {
			return -1;
		}
		return panel.getWidgetIndex(selected);
	}

	/**
	 * Gets the given Step.
	 * 
	 * This method is final because the Step interface will expand. Therefore it is highly likely that subclasses which
	 * implemented this method would end up breaking.
	 * 
	 * @param index
	 *            the step's index
	 * @return the step wrapper
	 */
	public final Step getStep(int index) {
		if (index >= getStepCount()) {
			return null;
		}
		ClickDelegatePanel p = (ClickDelegatePanel) panel.getWidget(index);
		return p;
	}

	/**
	 * Gets the number of Steps present.
	 * 
	 * @return the Step count
	 */
	public int getStepCount() {
		return panel.getWidgetCount();
	}

	/**
	 * Check if a Step is enabled or disabled. If disabled, the user cannot select the Step.
	 * 
	 * @param index
	 *            the index of the Step
	 * @return true if the Step is enabled, false if disabled
	 */
	public boolean isStepEnabled(int index) {
		assert (index >= 0) && (index < getStepCount()) : "Step index out of bounds";
		ClickDelegatePanel delPanel = (ClickDelegatePanel) panel.getWidget(index);
		return delPanel.isEnabled();
	}

	/**
	 * Removes the Step at the specified index.
	 * 
	 * @param index
	 *            the index of the Step to be removed
	 */
	public void removeStep(int index) {
		checkStepIndex(index);

		Widget toRemove = panel.getWidget(index);
		if (toRemove == selected) {
			selected = null;
		}
		panel.remove(toRemove);
	}

	/**
	 * 
	 * Go to next Step
	 * 
	 * @return <code>true</code> if successful, <code>false</code> if the change is denied by the
	 *         {@link BeforeSelectionHandler}.
	 */
	public boolean nextStep() {
		return nextStep(true);
	}

	public boolean backStep() {
		return backStep(true);
	}

	public boolean backStep(boolean fireEvents) {
		int currentlySelectedIndex = getSelectedStep();
		int index = currentlySelectedIndex - 1;
		checkStepIndex(index);
		if (fireEvents & !checkForBeforeSelectionEvent(index)) {
			return false;
		}

		if (currentlySelectedIndex > -1) {
			ClickDelegatePanel p = (ClickDelegatePanel) panel.getWidget(currentlySelectedIndex);
			p.removeStyleName(ClickDelegatePanel.ACTIVE_STYLE);
			p.addStyleName(ClickDelegatePanel.INACTIVE_STYLE);
		}

		if (currentlySelectedIndex > 1) {
			ClickDelegatePanel p = (ClickDelegatePanel) panel.getWidget(currentlySelectedIndex - 2);
			p.removeStyleName(ClickDelegatePanel.DONE_STYLE);
			p.addStyleName(ClickDelegatePanel.LAST_DONE_STYLE);
		}

		selected = panel.getWidget(index);
		selected.removeStyleName(ClickDelegatePanel.INACTIVE_STYLE);
		selected.removeStyleName(ClickDelegatePanel.LAST_DONE_STYLE);
		selected.addStyleName(ClickDelegatePanel.ACTIVE_STYLE);
		if (fireEvents) {
			SelectionEvent.fire(this, index);
		}
		return true;
	}

	/**
	 * 
	 * @param fireEvents
	 *            true to fire events, false not to
	 * @return <code>true</code> if successful, <code>false</code> if the change is denied by the
	 *         {@link BeforeSelectionHandler}.
	 */
	public boolean nextStep(boolean fireEvents) {
		int currentlySelectedIndex = getSelectedStep();
		int index = currentlySelectedIndex + 1;
		checkStepIndex(index);
		if (fireEvents & !checkForBeforeSelectionEvent(index)) {
			return false;
		}

		if (currentlySelectedIndex > -1) {
			ClickDelegatePanel p = (ClickDelegatePanel) panel.getWidget(currentlySelectedIndex);
			p.removeStyleName(ClickDelegatePanel.ACTIVE_STYLE);
			p.addStyleName(ClickDelegatePanel.LAST_DONE_STYLE);
		}
		if (currentlySelectedIndex > 0) {
			ClickDelegatePanel p = (ClickDelegatePanel) panel.getWidget(currentlySelectedIndex - 1);
			p.removeStyleName(ClickDelegatePanel.LAST_DONE_STYLE);
			p.addStyleName(ClickDelegatePanel.DONE_STYLE);
		}

		selected = panel.getWidget(index);
		selected.removeStyleName(ClickDelegatePanel.INACTIVE_STYLE);
		selected.addStyleName(ClickDelegatePanel.ACTIVE_STYLE);
		if (fireEvents) {
			SelectionEvent.fire(this, index);
		}
		return true;
	}

	private boolean checkForBeforeSelectionEvent(int index) {

		BeforeSelectionEvent<?> event = BeforeSelectionEvent.fire(this, index);
		if (event != null && event.isCanceled()) {
			return false;
		}
		return true;
	}

	/**
	 * Enable or disable a Step. When disabled, users cannot select the Step.
	 * 
	 * @param index
	 *            the index of the Step to enable or disable
	 * @param enabled
	 *            true to enable, false to disable
	 */
	public void setStepEnabled(int index, boolean enabled) {
		assert (index >= 0) && (index < getStepCount()) : "Step index out of bounds";

		ClickDelegatePanel delPanel = (ClickDelegatePanel) panel.getWidget(index);
		delPanel.setEnabled(enabled);
	}

	private void checkStepIndex(int index) {
		if ((index < -1) || (index >= getStepCount())) {
			throw new IndexOutOfBoundsException();
		}
	}
}

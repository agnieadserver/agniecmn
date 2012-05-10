package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.HasBeforeSelectionHandlers;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 */
public class WizardBar extends Composite implements HasBeforeSelectionHandlers<Integer>, HasSelectionHandlers<Integer> {

	public interface Step {
	}

	private class ClickDelegatePanel extends Composite implements Step {

		public static final String	INACTIVE_STYLE	= "inactive";
		public static final String	ACTIVE_STYLE	= "active";
		private SimplePanel			simplePanel;
		private boolean				enabled			= true;

		ClickDelegatePanel(Widget child) {

			simplePanel = new SimplePanel();
			simplePanel.setWidget(child);
			initWidget(simplePanel);
			simplePanel.setStyleName(INACTIVE_STYLE);
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

	/**
	 * Adds a new Step in wizard with specified text.
	 * 
	 * @param text
	 *            the new Step's text
	 */
	public void addStep(String text) {
		Label item;
		item = new Label(text);
		item.setWordWrap(false);
		addStep(item);
	}

	/**
	 * Adds a new Step in wizard with specified widget.
	 * 
	 * @param text
	 *            the new Step's text
	 */
	public void addStep(Widget step) {
		ClickDelegatePanel delWidget = new ClickDelegatePanel(step);
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

	public boolean nextStep() {
		int currentStepIndex = getSelectedStep();
		return selectStep(currentStepIndex + 1);
	}

	/**
	 * 
	 * @param index
	 *            the index of the Step to be selected
	 * @return <code>true</code> if successful, <code>false</code> if the change is denied by the
	 *         {@link BeforeSelectionHandler}.
	 */
	private boolean selectStep(int index) {
		return selectStep(index, true);
	}

	/**
	 * 
	 * @param index
	 *            the index of the Step to be selected
	 * @param fireEvents
	 *            true to fire events, false not to
	 * @return <code>true</code> if successful, <code>false</code> if the change is denied by the
	 *         {@link BeforeSelectionHandler}.
	 */
	private boolean selectStep(int index, boolean fireEvents) {
		checkStepIndex(index);

		if (fireEvents) {
			BeforeSelectionEvent<?> event = BeforeSelectionEvent.fire(this, index);
			if (event != null && event.isCanceled()) {
				return false;
			}
		}

		// Check for -1.
		setSelectionStyle(selected, false);
		if (index == -1) {
			selected = null;
			return true;
		}

		selected = panel.getWidget(index);
		setSelectionStyle(selected, true);
		if (fireEvents) {
			SelectionEvent.fire(this, index);
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

	private void setSelectionStyle(Widget item, boolean selected) {
		if (item != null) {
			if (selected) {
				item.removeStyleName(ClickDelegatePanel.INACTIVE_STYLE);
				item.addStyleName(ClickDelegatePanel.ACTIVE_STYLE);
			} else {
				item.removeStyleName(ClickDelegatePanel.ACTIVE_STYLE);
				item.addStyleName(ClickDelegatePanel.INACTIVE_STYLE);
			}
		}
	}
}

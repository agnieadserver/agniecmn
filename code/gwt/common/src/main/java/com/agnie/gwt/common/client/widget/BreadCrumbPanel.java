/**
 * 
 */
package com.agnie.gwt.common.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class BreadCrumbPanel extends Composite {

	public interface BreadCrumb extends HasClickHandlers {

	}

	private class ClickDelegatePanel extends Composite implements BreadCrumb {

		protected static final String	NODE_STYLE	= "node";
		private FocusPanel				focusablePanel;
		private boolean					enabled		= true;

		ClickDelegatePanel(Widget child) {

			focusablePanel = new FocusPanel();

			focusablePanel.setWidget(child);
			initWidget(focusablePanel);
			focusablePanel.setStyleName(NODE_STYLE);
			sinkEvents(Event.ONCLICK);
		}

		public HandlerRegistration addClickHandler(ClickHandler handler) {
			return addHandler(handler, ClickEvent.getType());
		}

		public SimplePanel getFocusablePanel() {
			return focusablePanel;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

	}

	private static final String	BREAD_CRUMB_CONTAINER_DEFAULT_STYLE	= "bread-crumb";
	private HTMLPanel			panel;

	public BreadCrumbPanel() {
		this(BREAD_CRUMB_CONTAINER_DEFAULT_STYLE);
	}

	/**
	 * Creates an empty Bread Crumb panel.
	 */
	public BreadCrumbPanel(String styleClassName) {
		panel = new HTMLPanel("");
		initWidget(panel);
		sinkEvents(Event.ONCLICK);
		panel.addStyleName(styleClassName);
	}

	/**
	 * Add new bread crumb with the text specified
	 * 
	 * @param text
	 *            the new bread crumb's text
	 */
	public void addBreadCrumb(String text) {
		Anchor item;
		item = new Anchor(text);

		item.setWordWrap(false);
		ClickDelegatePanel delWidget = new ClickDelegatePanel(item);
		panel.add(delWidget);
	}

	/**
	 * Gets the number of bread crumbs present.
	 * 
	 * @return the bread crumb count
	 */
	public int getBreadCrumbCount() {
		return panel.getWidgetCount();
	}

	/**
	 * Gets the given bread crumb .
	 * 
	 * This method is final because the BreadCrumb interface will expand. Therefore it is highly likely that subclasses
	 * which implemented this method would end up breaking.
	 * 
	 * @param index
	 *            the bread crumb's index
	 * @return the bread crumb wrapper
	 */
	public final BreadCrumb getBreadCrumb(int index) {
		if (index >= getBreadCrumbCount()) {
			return null;
		}
		ClickDelegatePanel p = (ClickDelegatePanel) panel.getWidget(index);
		return p;
	}

	/**
	 * Check if a BreadCrumb is enabled or disabled. If disabled, the user cannot click the BreadCrumb.
	 * 
	 * @param index
	 *            the index of the BreadCrumb
	 * @return true if the BreadCrumb is enabled, false if disabled
	 */
	public boolean isBreadCrumbEnabled(int index) {
		assert (index >= 0) && (index < getBreadCrumbCount()) : "BreadCrumb index out of bounds";
		ClickDelegatePanel delPanel = (ClickDelegatePanel) panel.getWidget(index);
		return delPanel.isEnabled();
	}

	/**
	 * Enable or disable a BreadCrumb. When disabled, users cannot click the tab.
	 * 
	 * @param index
	 *            the index of the BreadCrumb to enable or disable
	 * @param enabled
	 *            true to enable, false to disable
	 */
	public void setBreadCrumbEnabled(int index, boolean enabled) {
		assert (index >= 0) && (index < getBreadCrumbCount()) : "BreadCrumb index out of bounds";

		// // Style the wrapper
		ClickDelegatePanel delPanel = (ClickDelegatePanel) panel.getWidget(index);
		delPanel.setEnabled(enabled);
		// Need to Decide on what style to apply in case of diabled tab.
		// setStyleName(delPanel.getElement(), "gwt-TabBarItem-disabled", !enabled);
		// setStyleName(delPanel.getElement().getParentElement(), "gwt-TabBarItem-wrapper-disabled", !enabled);
	}

	/**
	 * Sets a BreadCrumb's text contents.
	 * 
	 * @param index
	 *            the index of the BreadCrumb whose text is to be set
	 * @param text
	 *            the object's new text
	 */
	public void setBreadCrumbText(int index, String text) {
		assert (index >= 0) && (index < getBreadCrumbCount()) : "BreadCrumb index out of bounds";

		ClickDelegatePanel delPanel = (ClickDelegatePanel) panel.getWidget(index);
		SimplePanel focusablePanel = delPanel.getFocusablePanel();

		((Anchor) focusablePanel.getWidget()).setText(text);
	}

	public String getBreadCrumbText(int index) {
		if (index >= getBreadCrumbCount()) {
			return null;
		}
		ClickDelegatePanel delPanel = (ClickDelegatePanel) panel.getWidget(index);
		SimplePanel focusablePanel = delPanel.getFocusablePanel();
		Widget widget = focusablePanel.getWidget();
		if (widget instanceof Anchor) {
			return ((Anchor) widget).getText();
		} else {
			// This will be a focusable panel holding a user-supplied widget.
			return focusablePanel.getElement().getParentElement().getInnerHTML();
		}
	}
}

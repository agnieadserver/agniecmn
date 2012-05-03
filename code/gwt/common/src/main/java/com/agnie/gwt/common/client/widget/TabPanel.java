package com.agnie.gwt.common.client.widget;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * This is generic tab panel which is bare structure. It will take the shape as per css applied.
 * 
 * <p>
 * <div class="tabBox"> <div class="tabs"> <div> <a class="gwt-Anchor" href="javascript:;">Home</a> </div> <div
 * class="selected"> <a class="gwt-Anchor" href="javascript:;">User Manager</a> </div> </div> </div>
 * </p>
 * 
 * You can create different set of styles to have different look and feel for your tab. e.g. you can create vertical tab
 * panel by creating such kind of css.
 * 
 * default css is "tabBox". for complete set refer tab.css inside same package.
 */
public class TabPanel extends Composite {

	private SimplePanel					container;
	private HTMLPanel					innerContainer;
	private Map<String, SimplePanel>	tabsMappedtoLabel	= new HashMap<String, SimplePanel>();
	private Map<Integer, SimplePanel>	tabsMappedtoIndex	= new HashMap<Integer, SimplePanel>();
	private int							tabCount			= 0;
	private Set<TabClickHandler>		clickHandlers		= new HashSet<TabClickHandler>();
	private SimplePanel					selectedTab;

	public TabPanel() {
		this("tabBox");
	}

	/**
	 * Create tab panel with different set of styles.
	 * 
	 * @param styleClassName
	 */
	public TabPanel(String styleClassName) {
		container = new SimplePanel();
		innerContainer = new HTMLPanel("");
		initWidget(container);
		container.setStyleName(styleClassName);
		innerContainer.setStyleName("tabs");
		container.add(innerContainer);
	}

	/**
	 * Add tab with given label to tab panel.
	 * 
	 * @param label
	 */
	public void addTab(final String label) {
		SimplePanel tab = new SimplePanel();
		Anchor anchTab = new Anchor(label);
		int tabIndex = tabCount++;
		InternalTabClickHandler handler = new InternalTabClickHandler(label, tabIndex);
		anchTab.addClickHandler(handler);
		tab.add(anchTab);
		// Maintaining separate maps for given tab by index and label.
		tabsMappedtoLabel.put(label, tab);
		tabsMappedtoIndex.put(tabIndex, tab);
		innerContainer.add(tab);
	}

	/**
	 * Set the selected tab by passing the index of the tab.
	 * 
	 * @param tabIndex
	 */
	public void setSelected(int tabIndex) {
		if (selectedTab != null) {
			selectedTab.removeStyleName("selected");
		}
		selectedTab = tabsMappedtoIndex.get(tabIndex);
		if (selectedTab != null) {
			selectedTab.setStyleName("selected");
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	/**
	 * Set the selected tab by passing label of the tab.
	 * 
	 * @param tabLabel
	 */
	public void setSelected(String tabLabel) {
		if (selectedTab != null) {
			selectedTab.removeStyleName("selected");
		}
		selectedTab = tabsMappedtoLabel.get(tabLabel);
		if (selectedTab != null) {
			selectedTab.setStyleName("selected");
		}
	}

	/**
	 * Add click handler on tab to handle the events on tab.
	 * 
	 * @param handler
	 */
	public void addClickHanlder(TabClickHandler handler) {
		clickHandlers.add(handler);
	}

	private class InternalTabClickHandler implements ClickHandler {

		private String	label;
		private int		index;

		/**
		 * @param label
		 * @param index
		 */
		public InternalTabClickHandler(String label, int index) {
			super();
			this.label = label;
			this.index = index;
		}

		@Override
		public void onClick(ClickEvent event) {
			for (TabClickHandler handler : clickHandlers) {
				handler.onClick(label, index);
			}
		}

	}

}

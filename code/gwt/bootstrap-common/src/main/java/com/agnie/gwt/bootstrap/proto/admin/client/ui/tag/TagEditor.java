/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui.tag;

import java.util.ArrayList;
import java.util.List;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.UlHTMLPanel;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author Pandurang Patil 25-Nov-2014
 *
 */
public class TagEditor extends UlHTMLPanel {
	private static TagResources	resource			= TagResources.INSTANCE;
	private SimpleEventBus		privateEventBus		= new SimpleEventBus();
	int							focusIndex			= -1;
	int							tagElementCounter	= 0;
	List<TagElement>			list				= new ArrayList<TagElement>();
	List<Widget>				allList				= new ArrayList<Widget>();
	TagElement					lastFocusElement	= null;
	static {
		resource.css().ensureInjected();
	}

	public TagEditor() {
		this("");

	}

	/**
	 * @param html
	 */
	public TagEditor(String html) {
		super(html);
		setStyleName(resource.css().tageditList());
		InputTagElement ite = new InputTagElement();
		ite.setEventBus(privateEventBus);
		ite.setEditor(this);
		ite.setWidth("70px");
		super.add(ite);
		allList.add(ite);
	}

	/**
	 * Add New Tag Element according to current focus .
	 * 
	 * @param element
	 */
	@SuppressWarnings("deprecation")
	public void addTagElement(TagElement element) {
		int oldFocusIndex = focusIndex;
		blurLastElement();
		element.setEventBus(privateEventBus);
		element.setEditor(this);
		int insertionIndex = allList.size() - 1;
		GWT.log("oldFocusIndex -> " + oldFocusIndex);
		if (oldFocusIndex != -1) {
			insertionIndex = (oldFocusIndex == 0 || (oldFocusIndex % 2) == 0 ? oldFocusIndex : oldFocusIndex + 1);
		}
		GWT.log("Insertion Index -> " + insertionIndex);
		InputTagElement inputElement = new InputTagElement();
		inputElement.setEventBus(privateEventBus);
		inputElement.setEditor(this);
		super.insert(inputElement, getElement(), insertionIndex, true);
		allList.add(insertionIndex++, inputElement);
		super.insert(element, getElement(), insertionIndex, true);
		allList.add(insertionIndex, element);
		list.add((insertionIndex == 0 ? insertionIndex : insertionIndex / 2), element);
		element.setFocus();
	}

	/**
	 * Retrieve list of tag elements
	 * 
	 * @return
	 */
	public List<TagElement> getTagElementList() {
		return list;
	}

	/**
	 * Get selected Tag Element if any element is in selected state.
	 * 
	 * @return
	 */
	public TagElement getSelectedTagElement() {
		Widget element = allList.get(focusIndex);
		if (element instanceof TagElement) {
			return (TagElement) element;
		}
		return null;
	}

	/**
	 * Remove given tag element.
	 * 
	 * @param element
	 */
	public void removeElement(TagElement element) {
		int index = list.indexOf(element);
		list.remove(index);
		index = allList.indexOf(element);
		remove(allList.get(index));
		allList.remove(index);
		remove(allList.get(index - 1));
		allList.remove(index - 1);
		if (focusIndex == index) {
			focusIndex = -1;
		}
		privateEventBus.fireEvent(new RemoveEvent(element));
	}

	/**
	 * Set focus to given Input Tag element used internally.
	 * 
	 * @param element
	 */
	void setFocusElement(InputTagElement element) {
		focusIndex = allList.indexOf(element);
		GWT.log("Inside input setFocusElement Focus Index ->" + focusIndex);
	}

	/**
	 * remove focus from currently focused element, will be used internally.
	 */
	void blurLastElement() {
		if (focusIndex != -1 && focusIndex < allList.size()) {
			Widget element = allList.get(focusIndex);
			if (element != null) {
				((FocusElement) element).setBlur();
				focusIndex = -1;
			}
		} else {
			focusIndex = -1;
		}
	}

	/**
	 * Set focus to given tag element.
	 * 
	 * @param element
	 */
	void setFocusElement(TagElement element) {
		if (lastFocusElement != null) {
			lastFocusElement.setActive(false);
		}
		lastFocusElement = element;
		focusIndex = allList.indexOf(element);
		GWT.log("Inside TagElement setFocusElement Focus Index ->" + focusIndex);
	}

	/**
	 * Register handler to receive selection event.
	 * 
	 * @param handler
	 *            handler to register for selection events.
	 * @return handler using which handler registered can unregister its handler registration.
	 */
	public HandlerRegistration addSelectionHandler(SelectionEventHandler handler) {
		return privateEventBus.addHandler(SelectionEvent.TYPE, handler);
	}

	/**
	 * Make use of addTagElement. This will have no impact.
	 */
	@Override
	@Deprecated
	public void add(Widget widget) {
	}

	/**
	 * Make use of addTagElement. This will have no impact.
	 */
	@Override
	@Deprecated
	public void add(Widget widget, String id) {
	}

	/**
	 * Make use of addTagElement. This will have no impact.
	 */
	@Override
	@Deprecated
	public void add(Widget widget, Element elem) {
	}

	static KeyPressHandler	restrictKeyPressHandler	= new KeyPressHandler() {

														public void onKeyPress(KeyPressEvent event) {
															((TextBox) event.getSource()).cancelKey();
														}
													};
	KeyDownHandler			keyDownHandler			= new KeyDownHandler() {

														@Override
														public void onKeyDown(KeyDownEvent event) {
															GWT.log("Focus index -> " + focusIndex);
															switch (event.getNativeKeyCode()) {
															case KeyCodes.KEY_LEFT:
																if (focusIndex > 0) {
																	((FocusElement) allList.get(focusIndex - 1)).setFocus();
																}
																break;
															case KeyCodes.KEY_RIGHT:
																if (focusIndex < allList.size() - 1) {
																	((FocusElement) allList.get(focusIndex + 1)).setFocus();
																}
																break;
															case KeyCodes.KEY_BACKSPACE:
																GWT.log("It comes here in backspace event.");
																if (focusIndex != -1 && focusIndex != 0 && focusIndex < allList.size()) {
																	Widget element = allList.get(focusIndex);
																	if (element instanceof InputTagElement) {
																		// if focused index is pointing to
																		// InputTagElement type then remove previous
																		// combination of TagElement and InputTagElement
																		int oldFocusIndex = focusIndex;
																		removeElement((TagElement) allList.get(focusIndex - 1));
																		((FocusElement) allList.get(oldFocusIndex - 2)).setFocus();
																	} else {
																		// if backspace is pressed while TagElement has
																		// the focus then the focused TagElement will be
																		// deleted. after its been deleted change the
																		// focus to Next InputTagElement.
																		GWT.log("Focus index=" + focusIndex);
																		int oldFocusIndex = focusIndex;
																		removeElement((TagElement) allList.get(focusIndex));
																		// With above removal of TagElement it will
																		// remove TagElements its relative
																		// InputTagElement.
																		GWT.log("Focus index=" + focusIndex);
																		GWT.log("Old Focus Index -1 index=" + (oldFocusIndex - 1));
																		((FocusElement) allList.get(oldFocusIndex - 1)).setFocus();
																	}
																}
																break;
															default:
																break;
															}
														}
													};

}

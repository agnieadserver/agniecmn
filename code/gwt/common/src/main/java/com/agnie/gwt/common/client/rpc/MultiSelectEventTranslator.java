/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.rpc;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.DefaultSelectionEventManager.EventTranslator;
import com.google.gwt.view.client.DefaultSelectionEventManager.SelectAction;

public class MultiSelectEventTranslator<T> implements EventTranslator<T> {

	private RowSelectCommand<T>	cmd;

	public MultiSelectEventTranslator() {
	}

	public MultiSelectEventTranslator(RowSelectCommand<T> cmd) {
		this.cmd = cmd;
	}

	@Override
	public boolean clearCurrentSelection(CellPreviewEvent<T> event) {
		return false;
	}

	@Override
	public SelectAction translateSelectionEvent(CellPreviewEvent<T> event) {
		NativeEvent nativeEvent = event.getNativeEvent();
		if ("click".equals(nativeEvent.getType())) {

			// Determine if we clicked on a checkbox.
			Element target = nativeEvent.getEventTarget().cast();
			if ("input".equals(target.getTagName().toLowerCase())) {
				final InputElement input = target.cast();
				if ("checkbox".equals(input.getType().toLowerCase())) {
					// Synchronize the checkbox with the current selection state.
					input.setChecked(event.getDisplay().getSelectionModel().isSelected(event.getValue()));
					return SelectAction.TOGGLE;
				}
			} else if (cmd != null) {
				cmd.setSelected(event.getValue());
				Scheduler.get().scheduleDeferred(cmd);
			}
		}
		return SelectAction.IGNORE;
	}

	public static interface RowSelectCommand<T> extends ScheduledCommand {
		void setSelected(T selected);
	}
}

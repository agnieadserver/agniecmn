/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui.mixin;

import org.gwtbootstrap3.client.ui.base.ComplexWidget;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Text;

import com.agnie.gwt.bootstrap.proto.admin.client.ui.ProtoStyles;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.user.client.ui.HasText;

/**
 * @author Pandurang Patil 25-Aug-2014
 *
 */
public class NumberTextMixin<T extends ComplexWidget & HasText> implements HasText {

	private final T				widget;
	private final Text			text		= new Text();
	private final Text			separator	= new Text("   ");
	private final SpanElement	number		= Document.get().createSpanElement();

	public NumberTextMixin(final T widget) {
		this.widget = widget;
		number.addClassName(Styles.BADGE);
		number.addClassName(ProtoStyles.HIDDEN_XS);

	}

	public void addTextWidgetToParent() {
		widget.add(text);
	}

	@Override
	public void setText(final String text) {
		this.text.setText(" " + text);
	}

	@Override
	public String getText() {
		return text.getText();
	}

	public void setNumber(Integer number) {
		render();
		this.number.setInnerText(number + "");
	}

	public void setProgress(boolean flag) {
		if (flag) {
			this.number.setClassName("");
		}
	}

	public Integer getNumber() {
		String txt = this.number.getInnerText();
		if (txt != null && !txt.isEmpty()) {
			return Integer.parseInt(txt);
		}
		return null;
	}

	private void render() {
		// We defer to make sure the elements are available to manipulate their positions
		Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
			@Override
			public void execute() {
				if (text.isAttached()) {
					text.removeFromParent();
				}
				if (separator.isAttached()) {
					separator.removeFromParent();
				}

				if (number != null) {
					number.removeFromParent();
				}

				// Since we are dealing with Icon/Text, we can insert them at the right position
				// Helps on widgets like ButtonDropDown, where it has a caret added
				int position = 0;

				if (getNumber() != null) {
					widget.getElement().appendChild(number);
					widget.insert(separator, position++);
				}

				if (text.getText() != null && text.getText().length() > 0) {
					widget.insert(text, position);
				}

			}
		});
	}
}

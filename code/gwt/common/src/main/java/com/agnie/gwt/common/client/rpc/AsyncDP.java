/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.common.client.rpc;

import java.util.List;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class AsyncDP<T> extends AsyncDataProvider<T> {
	private Range			lastrange;
	private DataFetcher<T>	dataFetcher;

	public AsyncDP(DataFetcher<T> dataFetcher) {
		this.dataFetcher = dataFetcher;
	}

	@Override
	protected void onRangeChanged(final HasData<T> display) {
		final Range range = display.getVisibleRange();

		dataFetcher.fire(range, new ListReceiver(range, display));
	}

	public static interface DataFetcher<T> {
		void fire(Range range, Receiver<List<T>> reciever);
	}

	private class ListReceiver extends Receiver<List<T>> {

		private Range		range;

		private HasData<T>	display;

		ListReceiver(Range range, HasData<T> display) {
			this.range = range;
			this.display = display;
		}

		@Override
		public void onSuccess(List<T> response) {
			if ((lastrange == null || lastrange.equals(new Range(0, range.getLength()))) && (response == null || response.size() == 0)) {
				updateRowCount(0, true);
			} else if (response != null && response.size() > 0) {
				updateRowData(range.getStart(), response);
				lastrange = range;
				if (response.size() < range.getLength()) {
					updateRowCount(range.getStart() + response.size(), true);
				}
			} else if (display.getRowCount() > 0) {
				updateRowCount(display.getRowCount(), true);
				display.setVisibleRange(lastrange);
			} else {
				updateRowCount(0, true);
			}

		}
	}

	/**
	 * @return the dataFetcher
	 */
	public DataFetcher<T> getDataFetcher() {
		return dataFetcher;
	};

}

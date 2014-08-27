/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *  
 *  NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 *  any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 *  and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 *  law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 *  permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.bootstrap.proto.admin.client.ui;

import java.util.HashMap;
import java.util.Map;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Pagination;
import org.gwtbootstrap3.client.ui.constants.IconType;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.SimplePager;

/**
 * TODO: This pager is tested with List data provider. Probably we may have to make some changed when it is being used
 * with Async data provider.
 * 
 * @author Pandurang Patil 27-Aug-2014
 *
 */
public class SimplePagination extends Pagination {

	// Default limit is 1
	protected int							limit	= 1;
	protected int							first	= 0;
	protected int							last	= 0;
	protected int							current	= 0;
	protected SimplePager					pager;
	protected AnchorListItem				prev	= addPreviousLink();
	protected AnchorListItem				next	= addNextLink();
	protected AnchorListItem				lastPage;
	private Map<Integer, AnchorListItem>	pageMap	= new HashMap<Integer, AnchorListItem>();

	@Override
	public AnchorListItem addPreviousLink() {
		AnchorListItem listItem = new AnchorListItem();
		listItem.setIcon(IconType.CHEVRON_LEFT);
		return listItem;
	}

	@Override
	public AnchorListItem addNextLink() {
		AnchorListItem listItem = new AnchorListItem();
		listItem.setIcon(IconType.CHEVRON_RIGHT);
		return listItem;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	/**
	 * It is expected to set pager once for a instance.
	 * 
	 * @param pager
	 */
	public void setPager(SimplePager pager) {
		this.pager = pager;

		prev.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				if (prev.isEnabled()) {
					SimplePagination.this.pager.previousPage();
					if (current > first) {
						current--;
						SimplePagination.this.pager.setPage(current - 1);
						setPageActive(getItemForPage(current), current);
					} else {
						rebuild(false);
					}
				}
			}
		});

		next.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				if (next.isEnabled()) {
					SimplePagination.this.pager.nextPage();
					if (current < last) {
						current++;
						SimplePagination.this.pager.setPage(current - 1);
						setPageActive(getItemForPage(current), current);
					} else {
						rebuild(true);
					}
				}
			}
		});
	}

	private AnchorListItem getItemForPage(final int pageNumber) {
		AnchorListItem page = pageMap.get(pageNumber);
		if (page == null) {
			page = new AnchorListItem(String.valueOf(pageNumber));
			page.addClickHandler(new PageClickHandler(pageNumber, page));
			pageMap.put(pageNumber, page);
		}
		return page;
	}

	public class PageClickHandler implements ClickHandler {
		private int				pageNumber;
		private AnchorListItem	page;

		/**
		 * @param pageNumber
		 * @param page
		 */
		public PageClickHandler(int pageNumber, AnchorListItem page) {
			this.pageNumber = pageNumber;
			this.page = page;
		}

		@Override
		public void onClick(ClickEvent event) {
			if (current != pageNumber) {
				pager.setPage(pageNumber - 1);
				setPageActive(page, pageNumber);
			}
		}

	}

	private void setPageActive(AnchorListItem page, int pageNumber) {
		page.setActive(true);
		if (lastPage != null) {
			lastPage.setActive(false);
		}
		lastPage = page;
		current = pageNumber;
	}

	/**
	 * This will help to rebuild the Pagination based on the data inside the SimplePager passed in.
	 * <p/>
	 * Make sure to all this after adding/remove data from any of the grid to ensure that this stays current with the
	 * SimplePager.
	 * <p/>
	 * ex. dataProvider.getList().addAll(newData); pagination.rebuild(mySimplePager);
	 *
	 * @param pager
	 *            the SimplePager of the CellTable/DataGrid
	 */
	public void rebuild(boolean nextFlag) {
		clear();
		int pageCount = pager.getPageCount();
		if (pageCount == 0) {
			return;
		}
		prev.setEnabled(pager.hasPreviousPage());
		add(prev);
		if (pageCount < limit) {
			first = 1;
			last = pageCount;
			rebuildPagination(first, last);
		} else {
			if (current == 0) {
				first = 1;
				last = limit;
				rebuildPagination(first, last);
			} else if (nextFlag && !(pager.getPage() == (last - 1))) {
				last = ++first + limit - 1;
				if (last > pageCount) {
					last = pageCount;
				}
				rebuildPagination(first, last);
			} else if ((!nextFlag) && !(pager.getPage() == (first - 1))) {
				rebuildPagination(--first, --last);
			} else {
				rebuildPagination(first, last);
			}
		}

		// next.setEnabled(pager.hasNextPage());
		next.setEnabled(true);
		add(next);
	}

	private void rebuildPagination(int first, int last) {
		for (int i = first - 1; i < last; i++) {
			final int display = i + 1;
			final AnchorListItem page = getItemForPage(display);

			if (i == pager.getPage()) {
				if (current != display) {
					setPageActive(page, display);
				}
			}

			add(page);
		}
	}
}

/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.gwt.client.renderer;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;

public class RendererSample extends Composite {

	CellList<Person>	cellList;

	public RendererSample() {
		PearsonCell cell = new PearsonCell();
		cellList = new CellList<Person>(cell);
		initWidget(cellList);
		init();
	}

	private void init() {
		List<Person> list = new ArrayList<Person>();
		Person p = new Person();
		p.setFname("Pranoti");
		p.setLname("Patil");
		p.setEmailid("pranoti.patil@gmail.com");
		p.setAge(30);
		list.add(p);

		p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailid("pandurang.patil@gmail.com");
		p.setAge(30);
		list.add(p);

		p = new Person();
		p.setFname("Ravi");
		p.setLname("Kumar");
		p.setEmailid("ravi.kumar@gmail.com");
		p.setAge(30);
		list.add(p);

		cellList.setRowCount(list.size(), true);
		cellList.setRowData(list);
	}
}

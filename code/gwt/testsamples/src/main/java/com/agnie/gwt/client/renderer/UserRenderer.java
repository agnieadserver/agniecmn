/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.gwt.client.renderer;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;

public class UserRenderer extends Composite {

	CellList<User>	cellList;

	public UserRenderer() {
		UserCell cell = new UserCell();
		cellList = new CellList<User>(cell);
		initWidget(cellList);
		init();
	}

	private void init() {
		List<User> list = new ArrayList<User>();
		User u = new User();
		u.setName("PPA");
		u.setWingName("wingA");
		u.setFlatNo("101");
		u.setType("type1");
		u.setStatus("status1");
		u.setSocImgUrl("images/person.png");
		u.setTakeAction("Action1");
		
		list.add(u);
		User u1 = new User();
		u1.setName("PPB");
		u1.setWingName("wingB");
		u1.setFlatNo("201");
		u1.setType("type2");
		u1.setStatus("status2");
		u1.setSocImgUrl("images/person.png");
		u1.setTakeAction("Action2");
		
		list.add(u1);
		User u2 = new User();
		u2.setName("PPC");
		u2.setWingName("wingC");
		u2.setFlatNo("301");
		u2.setType("type3");
		u2.setStatus("status3");
		u2.setSocImgUrl("images/person.png");
		u2.setTakeAction("Action3");
		
		list.add(u2);

		cellList.setRowCount(list.size(), true);
		cellList.setRowData(list);
	}
}

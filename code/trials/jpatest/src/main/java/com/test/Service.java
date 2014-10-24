/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class Service {

	EntityManager	em;

	@Inject
	public Service(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public String save() {
		Child child = new Child();
		child.setName("Child1");
		child.setDescription("desc");
		em.persist(child);
		Parent parent = new Parent();
		parent.setName("Parent");
		parent.setDescription("Description");
		List<Child> childs = new ArrayList<Child>();
		childs.add(child);
		parent.setChilds(childs);
		em.persist(parent);

		SecondParent sp = new SecondParent();
		sp.setName("Second PArent");
		sp.setChild(parent);
		em.persist(sp);
		return sp.getId();
	}

	@Transactional
	public void remove(String id) {
		List<SecondParent> list = em.createQuery("SELECT sp FROM SecondParent sp", SecondParent.class).getResultList();
		for (SecondParent secondParent : list) {
			em.remove(secondParent);
		}
	}

}

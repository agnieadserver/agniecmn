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
		SecondParent parent = em.find(SecondParent.class, id);
		em.remove(parent);
	}

}

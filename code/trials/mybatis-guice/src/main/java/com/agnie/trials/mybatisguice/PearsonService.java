package com.agnie.trials.mybatisguice;

import java.util.List;

import org.mybatis.guice.transactional.Transactional;

import com.google.inject.Inject;

public class PearsonService {

	@Inject
	private PearsonMapper	pmapper;

	@Transactional
	public Pearson getPearsonByEmailId(String emailId) {
		return this.pmapper.getPearson(emailId);
	}

	@Transactional
	public List<Pearson> getAll() {
		return this.pmapper.getAll();
	}

	@Transactional
	public String getPearsonNameByEmailId(String emailId) {
		return this.pmapper.getName(emailId);
	}
}

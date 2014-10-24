/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
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

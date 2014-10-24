/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
/**
 * 
 */
package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;

/**
 * Base validator
 */
public abstract class Validator {

	protected Annotation	constraint;

	/**
	 * @param constraint
	 */
	public Validator(Annotation constraint) {
		this.constraint = constraint;
	}

	/**
	 * @return the constraint
	 */
	public Annotation getConstraint() {
		return constraint;
	}

	/**
	 * When validator instance is created it will be passed with constraint annotation applied on the entity. If your
	 * validator requires to take some inputs e.g. max length. That one can take it through constraint and that
	 * constraint is accessible in validator. Developer implementing validator have to cast it to corresponding
	 * constraint you declared in Validator factory Mapping.
	 * 
	 * @param value
	 *            if valid return true other wise false
	 * @return
	 */
	public abstract boolean validate(String value);
}

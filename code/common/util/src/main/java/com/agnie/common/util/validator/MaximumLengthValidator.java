/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;

/**
 * Validator for @MaximumLength constraint
 * 
 */
@Constraint(MaximumLength.class)
public class MaximumLengthValidator extends Validator {

	private MaximumLength	maxConstraint;

	public MaximumLengthValidator(Annotation constraint) {
		super(constraint);
		if (constraint instanceof MaximumLength) {
			maxConstraint = (MaximumLength) constraint;
		}

	}

	@Override
	public boolean validate(String value) {
		if (maxConstraint != null && value != null) {
			if (value.length() <= maxConstraint.value())
				return true;
		}
		return false;
	}

}

/*******************************************************************************
 * © 2014 Copyright AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 *     
 * NOTICE: All information contained herein is, and remains the property of AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers, if any. 
 * The intellectual and technical concepts contained herein are proprietary to AGNIE MEDIA SOFTWARE PRIVATE LIMITED and its suppliers and 
 * may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless prior written permission is obtained from AGNIE MEDIA SOFTWARE PRIVATE LIMITED.
 ******************************************************************************/
package com.agnie.common.util.validator;

import java.lang.annotation.Annotation;

/**
 * Validator for @MinimumLength constraint
 * 
 */
@Constraint(MinimumLength.class)
public class MinimumLengthValidator extends Validator {

	private MinimumLength	minConstraint;

	public MinimumLengthValidator(Annotation constraint) {
		super(constraint);
		if (constraint instanceof MinimumLength) {
			minConstraint = (MinimumLength) constraint;
		}

	}

	@Override
	public boolean validate(String value) {
		if (minConstraint != null && value != null && !("".equals(value))) {
			if (value.length() >= minConstraint.value())
				return true;
		}
		return false;
	}

}

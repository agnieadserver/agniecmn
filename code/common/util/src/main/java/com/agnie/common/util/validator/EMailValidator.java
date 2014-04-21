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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.agnie.common.util.client.validator.ConstraintRegularExpressions;

/**
 * Validator for @EMail constraint
 * 
 */
@Constraint(EMail.class)
public class EMailValidator extends Validator {

	@SuppressWarnings("unused")
	private EMail	eMailConstraint;

	public EMailValidator(Annotation constraint) {
		super(constraint);
		if (constraint instanceof EMail) {
			eMailConstraint = (EMail) constraint;
		}

	}

	@Override
	public boolean validate(String value) {

		if (value != null) {
			Pattern pattern = Pattern.compile(ConstraintRegularExpressions.EMAIL_VALIDATOR_EXP);
			Matcher matcher = pattern.matcher(value);
			return matcher.matches();
		}
		return false;
	}

}

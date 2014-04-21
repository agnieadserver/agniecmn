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

/**
 * Validator for @RegularExp constraint
 * 
 */
@Constraint(RegularExp.class)
public class RegularExpValidator extends Validator {

	private RegularExp	regularExpConstraint;

	public RegularExpValidator(Annotation constraint) {
		super(constraint);
		if (constraint instanceof RegularExp) {
			regularExpConstraint = (RegularExp) constraint;
		}

	}

	@Override
	public boolean validate(String value) {
		// TODO Auto-generated method stub
		
		if(value != null)
		{
			Pattern pattern = Pattern.compile(regularExpConstraint.value());
			Matcher matcher = pattern.matcher(value);
			return matcher.matches();
		}
		return false;
	}

}

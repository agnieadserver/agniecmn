/*******************************************************************************
 * © 2014 Copyright Agnie Technologies
 *   
 * NOTICE: All information contained herein is, and remains the property of Agnie Technologies and its suppliers, if
 * any. The intellectual and technical concepts contained herein are proprietary to Agnie Technologies and its suppliers
 * and may be covered by Indian and Foreign Patents, patents in process, and are protected by trade secret or copyright
 * law. Dissemination of this information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from Agnie Technologies.
 ******************************************************************************/
package com.agnie.common.util.client.validator;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class ConstraintRegularExpressions {

	public static final String EMAIL_VALIDATOR_EXP = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String NO_WHITE_SPACE_EXP = "\\S+";
	// This regular expression will do check for validity of url
	// https://test.com:890/ till last slash after wards it
	// doesn't do validity on query parameters and all other sutff.
	public static final String VALID_URL_EXP = "^http[s]?[:][\\/][\\/][A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})[:]?[\\d]*[\\/]?(.?)";

	public static boolean validateWhiteSpace(String str) {
		RegExp pattern = RegExp
				.compile(ConstraintRegularExpressions.NO_WHITE_SPACE_EXP);
		MatchResult mr = pattern.exec(str);
		if ((mr != null) && (mr.toString().equals(mr.getInput()))) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateEmail(String str) {
		RegExp patternUrl = RegExp
				.compile(ConstraintRegularExpressions.EMAIL_VALIDATOR_EXP);
		MatchResult mrUrl = patternUrl.exec(str);
		if ((mrUrl != null)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean validateUrl(String str) {
		RegExp patternUrl = RegExp
				.compile(ConstraintRegularExpressions.VALID_URL_EXP);
		MatchResult mrUrl = patternUrl.exec(str);
		if ((mrUrl != null)) {
			return true;
		} else {
			return false;
		}
	}
}

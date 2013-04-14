package com.agnie.common.util.client.validator;

public class ConstraintRegularExpressions {

	public static final String	EMAIL_VALIDATOR_EXP	= "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String	NO_WHITE_SPACE_EXP	= "\\S+";
	// This regular expression will do check for validity of url https://test.com:890/ till last slash after wards it
	// doesn't do validity on query parameters and all other sutff.
	public static final String	VALID_URL_EXP		= "^http[s]?[:][\\/][\\/][A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})[:]?[\\d]*[\\/]?(.?)";
}

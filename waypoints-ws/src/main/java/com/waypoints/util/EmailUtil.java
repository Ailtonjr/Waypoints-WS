package com.waypoints.util;

public class EmailUtil {

	private static final String REGEX = "^[-a-z0-9~!$%^&*_=+}{\'?]+"
			+ "(\\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]"
			+ "*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov"
			+ "|info|int|mil|museum|name|net|org|pro|travel|mobi|[a"
			+ "-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0"
			+ "-9]{1,3}))(:[0-9]{1,5})?$";
	
	public static boolean isValid(String email) {
		return email.matches(REGEX);
	}
	
}

package org.krams.util;

import java.util.ArrayList;
import java.util.List;

public class RoleUtil {

	public static final int ROLE_ADMIN = 1;
	public static final int ROLE_USER = 2;
	
	public static final List<Integer> roles() {
		List<Integer> roles = new ArrayList<Integer>();
		roles.add(ROLE_USER);
		roles.add(ROLE_ADMIN);
		return roles;
	}
}

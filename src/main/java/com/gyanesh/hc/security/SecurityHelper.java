package com.gyanesh.hc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * This class is used to expose static methods that retrieves security related
 * information.
 * 
 * @author gyanesh.sharma
 *
 */
public class SecurityHelper {

	public static String getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt principal = (Jwt) authentication.getPrincipal();
		String currentUserName = principal.getClaimAsString("cognito:username");
		return currentUserName;
	}

}

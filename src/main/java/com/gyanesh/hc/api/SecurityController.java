package com.gyanesh.hc.api;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gyanesh.hc.security.SecurityHelper;

/**
 * This API exposes end points that return logged in user information. The
 * purpose of this API is just to demonstrate how to get user information from a
 * JWT token.
 * 
 * @author gyanesh.sharma
 *
 */
@RestController
@RequestMapping("/security")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {

	@GetMapping(value = "current-user", produces = "application/json")
	ResponseEntity<String> getLoggedInUser() {
		return new ResponseEntity<String>(SecurityHelper.getLoggedInUser(), HttpStatus.OK);
	}

	@GetMapping("current-user-claims")
	@ResponseBody
	public Map<String, Object> getUserInfo(@AuthenticationPrincipal Jwt principal) {
		Map<String, String> map = new Hashtable<String, String>();
		return principal.getClaims();
	}
}

package com.gyanesh.hc.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configure spring security beans to authenticate and authorize API requests from authorized OAuth2 clients.  
 *
 * @author gyanesh.sharma
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
			// other public end-points of your API may be appended to this array
	};
 
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		// Configure the URL of the client application. 
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {

		JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		/*
		 * Having no authority prefix allows more flexibility to configure security.
		 * Authorities that start with ROLE_ can be checked using hasRole or hasAnyRoles
		 * methods. Similarly individual privileges like "Domain.READ" could be checked
		 * using hasAuthority method.
		 */ 
		grantedAuthoritiesConverter.setAuthorityPrefix("");

		// Set the name of the JWT claim that contains space separated authorities/roles. 
		grantedAuthoritiesConverter.setAuthoritiesClaimName("custom:authorities");

		JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
		return jwtAuthenticationConverter;
	}

	/*
	 * Using a combination of generic end points using antMatchers or mvcMatchers,
	 * role and role hierarchy we can set complex role based authorization for
	 * authenticated users. 
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(authz -> {
			authz
			.antMatchers(AUTH_WHITELIST).permitAll()
			.antMatchers(HttpMethod.POST, "/**").hasRole("PCS.SME")
			.antMatchers(HttpMethod.PUT, "/**").hasRole("PCS.SME")
			.antMatchers(HttpMethod.DELETE, "/**").hasRole("PCS.ADMIN")
			.antMatchers(HttpMethod.GET, "/**").hasRole("PCS.USER")
			.anyRequest().authenticated();
		}).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
		and().
		cors() // This call utilizes corsConfigurationSource configurations.
				
		.and()
		.oauth2ResourceServer().jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()));

		return http.build();
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		final String role_hierarchy = "ROLE_PCS.ADMIN > ROLE_PCS.SME > ROLE_PCS.STAFF \n ROLE_PCS.STAFF > ROLE_PCS.USER";
		roleHierarchy.setHierarchy(role_hierarchy);
		return roleHierarchy;
	}

}
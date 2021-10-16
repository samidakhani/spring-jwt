/**
 * Copyright (c) GCOM Software Inc, NY. All Rights Reserved.
 * 
 * THIS INFORMATION IS COMPANY CONFIDENTIAL.
 * 
 * NOTICE: This material is a confidential trade secret and proprietary
 * information of GCOM Software Inc, NY which may not be reproduced, used, sold, or
 * transferred to any third party without the prior written consent of GCOM Software Inc, NY.
 */
package org.techbrains.jwt.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.techbrains.jwt.common.JsonWebTokenUtil;
import org.techbrains.jwt.model.AuthenticationRequest;
import org.techbrains.jwt.model.AuthenticationResponse;
import org.techbrains.jwt.service.CustomUserDetailService;

/**
 * The Class AuthenticationController.
 *
 * @author Sami Dakhani Created on Oct 15, 2021
 */
@RestController
public class AuthenticationController {

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;

	/** The user details service. */
	@Autowired
	private CustomUserDetailService userDetailsService;

	/**
	 * Create authentication token.
	 *
	 * @param authenticationRequest
	 *            the authentication request
	 * @return the response entity
	 * @throws Exception
	 *             the exception
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody final AuthenticationRequest authenticationRequest)
			throws Exception {
		// Step 1 (Authenticate)
		String username = authenticationRequest.getUsername();
		String password = authenticationRequest.getPassword();
		try {
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					username, password);
			this.authenticationManager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		// Step 2 (Generate JWT)
		UserDetails userDetails = this.userDetailsService
				.loadUserByUsername(username);
		String jsonWebToken = JsonWebTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jsonWebToken));
	}
}

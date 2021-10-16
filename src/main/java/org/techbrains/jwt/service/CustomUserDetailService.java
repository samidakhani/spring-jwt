/**
 * Copyright (c) GCOM Software Inc, NY. All Rights Reserved.
 * 
 * THIS INFORMATION IS COMPANY CONFIDENTIAL.
 * 
 * NOTICE: This material is a confidential trade secret and proprietary
 * information of GCOM Software Inc, NY which may not be reproduced, used, sold, or
 * transferred to any third party without the prior written consent of GCOM Software Inc, NY.
 */
package org.techbrains.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.techbrains.jwt.ds.CustomUserRepository;
import org.techbrains.jwt.model.CustomUser;
import org.techbrains.jwt.model.JwtUserDetails;

/**
 * The Class CustomUserDetailService.
 *
 * @author Sami Dakhani Created on Oct 15, 2021
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

	/** The user repository. */
	@Autowired
	private CustomUserRepository userRepository;

	/**
	 * Load user by username.
	 *
	 * @param username
	 *            the username
	 * @return the user details
	 * @throws UsernameNotFoundException
	 *             the username not found exception
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		CustomUser customUser = this.userRepository
				.findUserByUsername(username);
		JwtUserDetails userDetails = new JwtUserDetails(customUser);
		return userDetails;
	}

}

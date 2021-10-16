/**
 * Copyright (c) GCOM Software Inc, NY. All Rights Reserved.
 * 
 * THIS INFORMATION IS COMPANY CONFIDENTIAL.
 * 
 * NOTICE: This material is a confidential trade secret and proprietary
 * information of GCOM Software Inc, NY which may not be reproduced, used, sold, or
 * transferred to any third party without the prior written consent of GCOM Software Inc, NY.
 */
package org.techbrains.jwt.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The Class JwtUserDetails.
 *
 * @author Sami Dakhani Created on Oct 15, 2021
 */
public class JwtUserDetails implements UserDetails {

	/** Attribute for serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The custom user. */
	private CustomUser customUser;

	/**
	 * Instantiates a new jwt user details.
	 *
	 * @param customUser
	 *            the custom user
	 */
	public JwtUserDetails(final CustomUser customUser) {
		this.customUser = customUser;
	}

	/**
	 * Get authorities.
	 *
	 * @return the authorities
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(this.customUser.getRoles().split(","))
				.map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return this.customUser.getPassword();
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return this.customUser.getUsername();
	}

	/**
	 * Checks if is account non expired.
	 *
	 * @return true, if is account non expired
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Checks if is account non locked.
	 *
	 * @return true, if is account non locked
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Checks if is credentials non expired.
	 *
	 * @return true, if is credentials non expired
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Checks if is enabled.
	 *
	 * @return true, if is enabled
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return this.customUser.isActive();
	}

}

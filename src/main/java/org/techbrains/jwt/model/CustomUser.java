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

/**
 * The Class CustomUser.
 *
 * @author Sami Dakhani Created on Oct 15, 2021
 */
public class CustomUser {

	/** The is active. */
	private boolean isActive;

	/** The password. */
	private String password;

	/** The roles. */
	private String roles;

	/** The username. */
	private String username;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return this.roles;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return this.isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(final boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(final String roles) {
		this.roles = roles;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}
}

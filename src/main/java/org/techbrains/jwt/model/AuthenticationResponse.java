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
 * The Class AuthenticationResponse.
 *
 * @author Sami Dakhani Created on Oct 15, 2021
 */
public class AuthenticationResponse {

	/** The json web token. */
	private String jsonWebToken;

	/**
	 * @param jsonWebToken
	 */
	public AuthenticationResponse(final String jsonWebToken) {
		super();
		this.jsonWebToken = jsonWebToken;
	}

	/**
	 * @return the jsonWebToken
	 */
	public String getJsonWebToken() {
		return this.jsonWebToken;
	}

	/**
	 * @param jsonWebToken
	 *            the jsonWebToken to set
	 */
	public void setJsonWebToken(final String jsonWebToken) {
		this.jsonWebToken = jsonWebToken;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthenticationResponse [jsonWebToken=" + this.jsonWebToken
				+ "]";
	}
}

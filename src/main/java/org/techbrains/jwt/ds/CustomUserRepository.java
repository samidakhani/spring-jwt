/**
 * Copyright (c) GCOM Software Inc, NY. All Rights Reserved.
 * 
 * THIS INFORMATION IS COMPANY CONFIDENTIAL.
 * 
 * NOTICE: This material is a confidential trade secret and proprietary
 * information of GCOM Software Inc, NY which may not be reproduced, used, sold, or
 * transferred to any third party without the prior written consent of GCOM Software Inc, NY.
 */
package org.techbrains.jwt.ds;

import org.springframework.stereotype.Service;
import org.techbrains.jwt.model.CustomUser;

/**
 * The Class CustomUserRepository.
 *
 * @author Sami Dakhani Created on Oct 15, 2021
 */
@Service
public class CustomUserRepository {

	/**
	 * Find user by username.
	 *
	 * @param username
	 *            the username
	 * @return the custom user
	 */
	public CustomUser findUserByUsername(final String username) {
		CustomUser customUser = new CustomUser();
		customUser.setUsername("admin");
		customUser.setPassword("admin");
		customUser.setActive(true);
		customUser.setRoles("ADMIN");
		return customUser;
	}

}

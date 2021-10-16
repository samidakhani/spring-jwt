/**
 * Copyright (c) GCOM Software Inc, NY. All Rights Reserved.
 * 
 * THIS INFORMATION IS COMPANY CONFIDENTIAL.
 * 
 * NOTICE: This material is a confidential trade secret and proprietary
 * information of GCOM Software Inc, NY which may not be reproduced, used, sold, or
 * transferred to any third party without the prior written consent of GCOM Software Inc, NY.
 */
package org.techbrains.jwt.filter;

import static org.techbrains.jwt.common.CommonConstants.HEADER_AUTHORIZATION;
import static org.techbrains.jwt.common.CommonConstants.TOKEN_BEARER;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.techbrains.jwt.common.JsonWebTokenUtil;
import org.techbrains.jwt.service.CustomUserDetailService;

/**
 * @author Sami Dakhani Created on Oct 15, 2021
 *
 */
@Component
public class JsonWebTokenRequestFilter extends OncePerRequestFilter {

	/** The user details service. */
	@Autowired
	private CustomUserDetailService userDetailsService;

	/**
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(final HttpServletRequest request,
			final HttpServletResponse response, final FilterChain filterChain)
			throws ServletException, IOException {
		String username = null;
		String jsonWebToken = null;

		String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
		if (authorizationHeader != null
				&& authorizationHeader.startsWith(TOKEN_BEARER)) {
			jsonWebToken = authorizationHeader.substring(7);
			username = JsonWebTokenUtil.extractUsername(jsonWebToken);
		}

		if (username != null && SecurityContextHolder.getContext()
				.getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService
					.loadUserByUsername(username);

			if (JsonWebTokenUtil.validateToken(jsonWebToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource()
								.buildDetails(request));
				SecurityContextHolder.getContext()
						.setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

}

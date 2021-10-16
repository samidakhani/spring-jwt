/**
 * Copyright (c) GCOM Software Inc, NY. All Rights Reserved.
 * 
 * THIS INFORMATION IS COMPANY CONFIDENTIAL.
 * 
 * NOTICE: This material is a confidential trade secret and proprietary
 * information of GCOM Software Inc, NY which may not be reproduced, used, sold, or
 * transferred to any third party without the prior written consent of GCOM Software Inc, NY.
 */
package org.techbrains.jwt.common;

import static org.techbrains.jwt.common.CommonConstants.SECRET_KEY;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * The Class JsonWebTokenUtil.
 *
 * @author Sami Dakhani Created on Oct 15, 2021
 */
public class JsonWebTokenUtil {

	/**
	 * Extract claim.
	 *
	 * @param <T>
	 *            the generic type
	 * @param token
	 *            the token
	 * @param claimsResolver
	 *            the claims resolver
	 * @return the t
	 */
	public static <T> T extractClaim(final String token,
			final Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	/**
	 * Extract expiration.
	 *
	 * @param token
	 *            the token
	 * @return the date
	 */
	public static Date extractExpiration(final String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * Extract username.
	 *
	 * @param token
	 *            the token
	 * @return the string
	 */
	public static String extractUsername(final String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Generate token.
	 *
	 * @param userDetails
	 *            the user details
	 * @return the string
	 */
	public static String generateToken(final UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	/**
	 * Validate token.
	 *
	 * @param token
	 *            the token
	 * @param userDetails
	 *            the user details
	 * @return the boolean
	 */
	public static Boolean validateToken(final String token,
			final UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())
				&& !isTokenExpired(token));
	}

	/**
	 * Create token.
	 *
	 * @param claims
	 *            the claims
	 * @param subject
	 *            the subject
	 * @return the string
	 */
	private static String createToken(final Map<String, Object> claims,
			final String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(
						System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	/**
	 * Extract all claims.
	 *
	 * @param token
	 *            the token
	 * @return the claims
	 */
	private static Claims extractAllClaims(final String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token)
				.getBody();
	}

	/**
	 * Is token expired.
	 *
	 * @param token
	 *            the token
	 * @return the boolean
	 */
	private static Boolean isTokenExpired(final String token) {
		return extractExpiration(token).before(new Date());
	}
}

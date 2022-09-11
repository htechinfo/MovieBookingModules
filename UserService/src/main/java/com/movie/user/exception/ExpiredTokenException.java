package com.movie.user.exception;

import org.springframework.http.HttpStatus;

public class ExpiredTokenException extends JWTException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2141778783512609775L;

	public ExpiredTokenException() {
        super(HttpStatus.BAD_REQUEST, "EXPIRED_JWT");
    }
}

package com.movie.user.exception;

import org.springframework.http.HttpStatus;

public class InvalidTokenException extends JWTException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5203728170821971200L;

	public InvalidTokenException() {
		super(HttpStatus.BAD_REQUEST, "INVALID_JWT");
	}
}

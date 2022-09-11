package com.movie.booking.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JWTException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1903116855087454817L;
	
	private final HttpStatus status;
    private final String error;

}

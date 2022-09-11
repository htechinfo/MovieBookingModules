package com.movie.user.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.movie.user.config.BookingSystemUserDetailService;
import com.movie.user.exception.ExpiredTokenException;
import com.movie.user.exception.InvalidTokenException;
import com.movie.user.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private BookingSystemUserDetailService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		JwtUtil.getJwtFromRequest(request).ifPresent(token -> {
			try {
				setSecurityContext(new WebAuthenticationDetailsSource().buildDetails(request), token);
			} catch (IllegalArgumentException | MalformedJwtException e) {
				throw new InvalidTokenException();
			} catch (ExpiredJwtException e) {
				throw new ExpiredTokenException();
			}
		});

		chain.doFilter(request, response);
	}

	private void setSecurityContext(WebAuthenticationDetails authDetails, String token) {

		// Once we get the token validate it.
		String username = jwtTokenUtil.getUsernameFromToken(token);
		UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

		// if token is valid configure Spring Security to manually set authentication
		if (jwtTokenUtil.validateToken(token, userDetails)) {

			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}
}

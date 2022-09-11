package com.movie.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.user.config.BookingSystemUserDetailService;
import com.movie.user.config.CustomAuthenticationProvider;
import com.movie.user.constant.CommonConstant;
import com.movie.user.entity.User;
import com.movie.user.exception.ResourceNotFoundException;
import com.movie.user.model.JwtRequest;
import com.movie.user.model.JwtResponse;
import com.movie.user.repository.UserRepository;
import com.movie.user.util.JwtTokenUtil;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomAuthenticationProvider authenticationProvider;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private BookingSystemUserDetailService userDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	@Hidden()
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.USER_NOT_FOUND_WITH_ID + userId));
	}

	@Hidden()
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}

	@Hidden()
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.USER_NOT_FOUND_WITH_ID + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
	}

	@Hidden()
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.USER_NOT_FOUND_WITH_ID + userId));
		this.userRepository.delete(existingUser);
		return ResponseEntity.ok().build();
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}

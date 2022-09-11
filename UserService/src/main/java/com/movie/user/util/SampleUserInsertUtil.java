package com.movie.user.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.movie.user.constant.CommonConstant;
import com.movie.user.entity.Authority;
import com.movie.user.entity.User;
import com.movie.user.repository.AuthorityRepository;
import com.movie.user.repository.UserRepository;

@Component
public class SampleUserInsertUtil {

	@Bean
	CommandLineRunner addSanokeData(UserRepository repository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder) {
		return (args) -> {
			List<Authority> authorities = Arrays.asList(getAuthority(CommonConstant.ROLE_ADMIN), getAuthority(CommonConstant.ROLE_USER));
			authorityRepository.saveAll(authorities);
			
			List<User> users = new ArrayList<>();
			users.add(getUser("hituahuja", passwordEncoder.encode("hituahuja"), "hituahuja@gmail.com", authorities.get(1)));
			users.add(getUser("haahuja121", passwordEncoder.encode("haahuja121"), "haahuja121@gmail.com", authorities.get(0)));
			repository.saveAll(users);
		};
	}
	
	private User getUser(String username, String password, String email, Authority authority) {
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(authority);
		user.setAuthorities(authorities);
		user.setFirstName("Hitesh");
		user.setLastName("Ahuja");
		user.setEnabled(true);
		return user;
	}
	
	private Authority getAuthority(String auth) {
		Authority authority = new Authority();
		authority.setName(auth);
		return authority;
	}
}

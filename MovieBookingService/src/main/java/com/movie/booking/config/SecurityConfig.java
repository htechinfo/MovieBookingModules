package com.movie.booking.config;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.jwtutil.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JwtRequestFilter authenticationFilter;

	private static final String[] SWAGGER_UI = { "/", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui",
			"/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security",
			"/configuration/security", "/swagger-ui.html", "/webjars/**", "/swagger-ui/**", "/v3/api-docs/**",
			"/error" };

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().configurationSource((HttpServletRequest request) -> {
			CorsConfiguration config = new CorsConfiguration();
			// config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
			config.setAllowedMethods(Collections.singletonList("*"));
			config.setAllowCredentials(true);
			config.setAllowedHeaders(Collections.singletonList("*"));
			config.setMaxAge(3600L);
			return config;
		}).and()

				.csrf().disable()

				.authorizeHttpRequests(auth -> auth.mvcMatchers(HttpMethod.GET, "/").permitAll().mvcMatchers(SWAGGER_UI)
						.permitAll()
						.mvcMatchers(HttpMethod.GET, "/api/mb/theatre/**", "/api/mb/movie/**", "/api/mb/city/**",
								"api/mb/movie-show/**", "/api/mb/seats/**").permitAll()
						.mvcMatchers("/api/mb/**").hasAuthority(CommonConstant.ROLE_ADMIN)
						.antMatchers("/**").authenticated())
				.httpBasic(Customizer.withDefaults());

		return http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

}

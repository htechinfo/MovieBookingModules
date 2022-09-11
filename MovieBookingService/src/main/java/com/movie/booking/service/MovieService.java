package com.movie.booking.service;

import java.util.List;

import com.movie.booking.entity.Movie;

public interface MovieService {
	public String registerMovie(Movie movie);
	
	public boolean deRegisterMovie(String movieUniqueId);
	
	List<Movie> getAllMovies();
}

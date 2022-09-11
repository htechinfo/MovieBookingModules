package com.movie.booking.service;

import org.springframework.data.domain.Page;

import com.movie.booking.entity.MovieShow;

public interface MovieShowService {
	MovieShow updateMovieShow(MovieShow movieShow, long movieShowId);
	
	Page<MovieShow> getAllMovieShow(Integer pageNumber);
	
	MovieShow deleteMovieShow(long movieShowId);
}

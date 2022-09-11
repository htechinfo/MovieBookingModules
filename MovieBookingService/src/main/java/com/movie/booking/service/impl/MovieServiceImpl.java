package com.movie.booking.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.entity.Movie;
import com.movie.booking.repository.MovieRepository;
import com.movie.booking.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public String registerMovie(Movie movie) {
		// TODO Auto-generated method stub
		Optional<Movie> optionalMovie = movieRepository.findByName(movie.getName());
		String movieUniqueId = null;
		if (optionalMovie.isPresent()) {
			Movie existingMovie = optionalMovie.get();
			existingMovie.setIsActive(movie.getIsActive());
			movieUniqueId = movieRepository.save(existingMovie).getMovieUniqueId();
			
		} else {
			movieUniqueId = movieRepository.save(movie).getMovieUniqueId();
		}
		return movieUniqueId;
	}

	@Override
	public boolean deRegisterMovie(String movieUniqueId) {
		boolean isSuccess = false;
		Optional<Movie> optionalMovie = movieRepository.findByMovieUniqueId(movieUniqueId);
		Movie movie = optionalMovie.orElseThrow(EntityNotFoundException::new);
		movie.setIsActive(false);
		movieRepository.save(movie);
		isSuccess = true;
		return isSuccess;
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	

}

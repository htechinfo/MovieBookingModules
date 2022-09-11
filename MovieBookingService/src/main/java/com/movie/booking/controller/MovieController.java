package com.movie.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.entity.Movie;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.repository.MovieRepository;
import com.movie.booking.service.MovieService;

@RestController
@RequestMapping("/api/mb/movie")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping
	public ResponseEntity<List<Movie>> getAllMovies() {
		return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.MOVIE_NOT_FOUND_WITH_ID + id));
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> registerMovie(@Validated @RequestBody Movie movie) {
		return new ResponseEntity<String>(movieService.registerMovie(movie), HttpStatus.CREATED);
	}

	@DeleteMapping("/{movie_id}")
	public ResponseEntity<HttpStatus> deRegisterMovie(@PathVariable(name = "movie_id") String movieId) {
		ResponseEntity<HttpStatus> responseEntity = null;
		if (movieService.deRegisterMovie(movieId)) {
			responseEntity = new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
		} else {
			responseEntity = new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
}

package com.movie.booking.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.entity.MovieShow;
import com.movie.booking.entity.Seat;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.repository.MovieShowRepository;
import com.movie.booking.service.MovieShowService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api/mb/movie-show")
public class MovieShowController {

	@Autowired
	private MovieShowService movieShowService;

	@Autowired
	private MovieShowRepository movieShowRepository;

	@GetMapping("/{id}/seats")
	public Set<Seat> getSeatByMovieShow(@PathVariable("id") long movieShowId) {
		MovieShow movieShowById = getMovieShowById(movieShowId);
		return movieShowById.getSeats();
	}

	@Hidden
	@GetMapping
	public Page<MovieShow> getAllMovieShow(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber) {
		return movieShowService.getAllMovieShow(pageNumber);
	}

	@GetMapping("/{id}")
	public MovieShow getMovieShowById(@PathVariable(value = "id") long movieShowId) {
		return this.movieShowRepository.findById(movieShowId).orElseThrow(
				() -> new ResourceNotFoundException(CommonConstant.MOVIE_SHOW_NOT_FOUND_WITH_ID + movieShowId));
	}
	
	@GetMapping("/{pincode}/movies")
	public ResponseEntity<List<MovieShow>> getMovieListByCity(@PathVariable(name = "pincode") String pincode) {
		return new ResponseEntity<List<MovieShow>>(movieShowRepository.findByTheatrePinCode(pincode), HttpStatus.OK);
	}

	@GetMapping("/{pincode}/movies/{movie}")
	public ResponseEntity<List<MovieShow>> getListOfTheaterAndShowByMovie(@PathVariable(name = "pincode") String pincode,
			@PathVariable(name = "movie") String movieName, 
			@RequestParam(name = "showDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate showDate) {
		
		List<MovieShow> listOfShow = null;
		if (showDate != null) {
			listOfShow = movieShowRepository.findByTheatrePinCodeAndMovieNameAndDate(pincode, movieName, showDate);
		} else {
			listOfShow = movieShowRepository.findByTheatrePinCodeAndMovieName(pincode, movieName);
		}
		
		return new ResponseEntity<List<MovieShow>>(listOfShow, HttpStatus.OK);
	}

	@PostMapping
	public MovieShow createMovieShow(@RequestBody MovieShow movieShow) {
		return this.movieShowRepository.save(movieShow);
	}

	@Hidden
	@PutMapping("/{id}")
	public MovieShow updateMovieShow(@RequestBody MovieShow movieShow, @PathVariable("id") long movieShowId) {
		return movieShowService.updateMovieShow(movieShow, movieShowId);
	}

	@Hidden
	@DeleteMapping("/{id}")
	public ResponseEntity<MovieShow> deleteMovieShow(@PathVariable("id") long movieShowId) {
		movieShowService.deleteMovieShow(movieShowId);
		return ResponseEntity.ok().build();
	}
}

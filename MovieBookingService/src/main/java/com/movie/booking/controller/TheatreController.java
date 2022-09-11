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
import com.movie.booking.entity.Theatre;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.repository.TheaterRepository;
import com.movie.booking.service.TheaterService;

@RestController
@RequestMapping("/api/mb/theatre")
public class TheatreController {
	
	@Autowired
	TheaterService theaterService;
	
	@Autowired
	private TheaterRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Theatre>> getAllTheater() {
		return new ResponseEntity<List<Theatre>>(theaterService.getAllTheater(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Theatre> getTheatreById(@PathVariable Long id) {
		Theatre theatre = repository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(CommonConstant.THEATER_NOT_FOUND_WITH_ID + id));
		return new ResponseEntity<Theatre>(theatre, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> registerTheater(@Validated @RequestBody Theatre theater) {
		return new ResponseEntity<String>(theaterService.registerTheater(theater), HttpStatus.CREATED);
	}

	@DeleteMapping("/{theater_id}")
	public ResponseEntity<HttpStatus> deRegisterTheater(@PathVariable(name = "theater_id") String theaterId) {
		ResponseEntity<HttpStatus> responseEntity = null;
		if (theaterService.deRegisterTheater(theaterId)) {
			responseEntity = new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
		} else {
			responseEntity = new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		}
		return responseEntity;
	}
}

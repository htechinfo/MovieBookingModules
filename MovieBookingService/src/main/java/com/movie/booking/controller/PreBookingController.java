package com.movie.booking.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.movie.booking.entity.PreBooking;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.repository.PreBookingRepository;
import com.movie.booking.service.PreBookingService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api/mb/pre-booking-lock")
public class PreBookingController {

	@Autowired
	private PreBookingRepository preBookingRepository;

	@Autowired
	private PreBookingService preBookingService;

	@GetMapping
	public Page<PreBooking> getAllPreBookings(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber) {
		return preBookingService.getPrebookings(pageNumber);
	}

	@Hidden
	@GetMapping("/{lockPattern}")
	public Optional<PreBooking> getPreBookingByLockPattern(@PathVariable(value = "lockPattern") String lockPattern) {
		return preBookingService.getPreBookingByLockPattern(lockPattern);
	}

	@Hidden
	@GetMapping("/{id}")
	public PreBooking getBookingById(@PathVariable(value = "id") long bookingId) {
		return this.preBookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException("PreBooking not found with id :" + bookingId));

	}

	@Hidden
	@PostMapping
	public PreBooking createPreBooking(@RequestBody PreBooking preBooking) {
		return preBookingService.createPreBooking(preBooking);
	}

	@Hidden
	@PutMapping("/{id}")
	public PreBooking updateBooking(@RequestBody PreBooking preBooking, @PathVariable("id") long bookingId) {
		return preBookingService.updatePreBooking(preBooking, bookingId);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<PreBooking> deleteBooking(@PathVariable("id") long bookingId) {
		preBookingService.deletePreBooking(bookingId);
		return ResponseEntity.ok().build();
	}
}

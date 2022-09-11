package com.movie.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.entity.Booking;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.model.PreBookModel;
import com.movie.booking.model.TicketModel;
import com.movie.booking.repository.BookingRepository;
import com.movie.booking.service.BookingService;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api/mb/booking")
public class BookingController {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingService bookingService;

	@PostMapping("pre-booking")
	public PreBookModel preBooking(@RequestBody Booking booking) {
		return bookingService.reservceSeats(booking);
	}

	@GetMapping("/{id}")
	public TicketModel generateTicket(@PathVariable(value = "id") long bookingId) {
		return bookingService.generateTicket(bookingId);
	}

	public Booking getBookingById(@PathVariable(value = "id") long bookingId) {
		return bookingService.getBookingById(bookingId);
	}

	@Hidden
	@PostMapping
	public Booking createBooking(@RequestBody Booking booking) {
		return this.bookingRepository.save(booking);
	}

	@Hidden
	@PutMapping("/{id}")
	public Booking updateBooking(@RequestBody Booking booking, @PathVariable("id") long bookingId) {
		return bookingService.updateBooking(booking, bookingId);
	}

	@Hidden
	@DeleteMapping("/{id}")
	public ResponseEntity<Booking> deleteBooking(@PathVariable("id") long bookingId) {
		Booking existingBooking = this.bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.BOOKING_NOT_FOUND_WITH_ID + bookingId));
		this.bookingRepository.delete(existingBooking);
		return ResponseEntity.ok().build();
	}

}

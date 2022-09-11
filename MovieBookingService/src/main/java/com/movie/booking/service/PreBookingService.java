package com.movie.booking.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.movie.booking.entity.PreBooking;

public interface PreBookingService {
	Page<PreBooking> getPrebookings(int pageNumber);
	
	PreBooking createPreBooking(PreBooking preBooking);
	
	PreBooking updatePreBooking(PreBooking preBooking, Long preBookingId);
	
	void deletePreBooking(Long preBookingId);
	
	PreBooking reserveSeats(String lockingPattern, String userName);
	
	Optional<PreBooking> getPreBookingByLockPattern(String lockPattern);
}

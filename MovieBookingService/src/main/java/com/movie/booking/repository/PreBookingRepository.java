package com.movie.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.booking.entity.PreBooking;

public interface PreBookingRepository extends JpaRepository<PreBooking, Long> {
	Optional<PreBooking> findByLockPattern(String lockPattern);
}

package com.movie.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.booking.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	Optional<Booking> findByPaymentTransactionNo(String transactionNo);
}

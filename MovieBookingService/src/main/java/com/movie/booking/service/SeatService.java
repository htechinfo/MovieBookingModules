package com.movie.booking.service;

import java.util.List;

import com.movie.booking.entity.Seat;

public interface SeatService {
	
	Seat getSeatById(Long seatId);
	
	boolean isSeatAvailable(List<Seat> requestedSeats);
}

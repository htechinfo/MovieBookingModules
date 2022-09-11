package com.movie.booking.service;

import com.movie.booking.entity.Booking;
import com.movie.booking.model.PreBookModel;
import com.movie.booking.model.TicketModel;

public interface BookingService {
	PreBookModel reservceSeats(Booking booking);
	
	TicketModel generateTicket(long bookingId);
	
	Booking getBookingById(long bookingId);
	
	Booking updateBooking(Booking booking, long bookingId);
	
	long bookTicket(String transactionNo);
}

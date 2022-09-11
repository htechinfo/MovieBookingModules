package com.movie.booking.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.controller.MovieController;
import com.movie.booking.controller.PaymentController;
import com.movie.booking.controller.TheatreController;
import com.movie.booking.entity.Booking;
import com.movie.booking.entity.PreBooking;
import com.movie.booking.entity.Seat;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.model.PreBookModel;
import com.movie.booking.model.TicketModel;
import com.movie.booking.repository.BookingRepository;
import com.movie.booking.service.BookingService;
import com.movie.booking.service.PreBookingService;
import com.movie.booking.service.SeatService;
import com.movie.booking.strategy.NoDiscountStrategy;
import com.movie.booking.util.CommonUtil;
import com.movie.booking.util.PriceCalculator;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private PreBookingService preBookingService;

	@Autowired
	private SeatService seatService;

	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public PreBookModel reservceSeats(Booking booking) {
		// Proceed Only if Seat Available & booking is Not locked
		String lockPattern = CommonUtil.getLockPattern(booking);

		Optional<PreBooking> optionalPreBooking = preBookingService.getPreBookingByLockPattern(lockPattern);

		boolean isSeatAvailable = seatService.isSeatAvailable(booking.getSeats());

		if (!optionalPreBooking.isPresent() && isSeatAvailable) {
			/** Locking for 10 min **/
			preBookingService.reserveSeats(lockPattern, lockPattern);

			booking.setStatus(CommonConstant.PENDING);

			/** Will not trust on UI input for Seat price fetching again from DB **/
			List<Seat> seats = booking.getSeats().stream().map(seat -> seatService.getSeatById(seat.getId()))
					.collect(Collectors.toList());
			booking.setSeats(seats);

			PriceCalculator calculator = new PriceCalculator(new NoDiscountStrategy());/** Strategy Pattern **/
			BigDecimal calculatedAmount = calculator.calculate(booking);
			booking.setAmount(calculatedAmount);

			booking.getPayment().setAmount(calculatedAmount);
			booking.getPayment().setStatus(CommonConstant.PAYMENT_PENDING);

			/** Booking saved with Pending Status **/

			Booking save = bookingRepository.save(booking);

			/**
			 * Call to third party Payment API goes here Pay_BY_UPI(booking.getId(),
			 * booking.getAmount());
			 */

			/** This Transaction/Booking ID can be shown on UI in case of any issue **/
			PreBookModel preBookModel = new PreBookModel(save.getId(), save.getPayment().getTransactionNo());

			Link paymentStatusLink = WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(PaymentController.class).getPaymentById(preBookModel.getTransactionId()))
					.withRel("payment-status");
			preBookModel.add(paymentStatusLink);

			return preBookModel;
		} else {
			throw new ResourceNotFoundException("Please try after some time.");
		}
	}

	// TODO: Need to be in separate service which is responsible for generating ticket Asynchronized way and sending over notification
	@Override
	public TicketModel generateTicket(long bookingId) {
		Booking booking = getBookingById(bookingId);

		TicketModel ticketModel = new TicketModel();
		ticketModel.setId(booking.getId());
		ticketModel.setAmount(booking.getAmount());
		ticketModel.setSeats(booking.getSeats());
		ticketModel.setMovieName(booking.getMovieShow().getMovie().getName());
		ticketModel.setStartTime(booking.getMovieShow().getStartTime());
		ticketModel.setEndTime(booking.getMovieShow().getEndTime());
		ticketModel.setTheaterName(booking.getMovieShow().getTheatre().getName());
		ticketModel.setScreen(booking.getMovieShow().getScreen().getName());

		Link movieLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MovieController.class)
				.getMovieById(booking.getMovieShow().getMovie().getId())).withRel("Movie Details");

		ticketModel.add(movieLink);

		Link theaterLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TheatreController.class)
				.getTheatreById(booking.getMovieShow().getTheatre().getId())).withRel("Theater Details");
		ticketModel.add(theaterLink);

		return ticketModel;
	}

	@Override
	public Booking getBookingById(long bookingId) {
		return bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.BOOKING_NOT_FOUND_WITH_ID + bookingId));
	}

	@Override
	public Booking updateBooking(Booking booking, long bookingId) {
		Booking existingBooking = this.bookingRepository.findById(bookingId)
				.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.BOOKING_NOT_FOUND_WITH_ID + bookingId));
		existingBooking.setBookingDate(booking.getBookingDate());
		existingBooking.setUsername(booking.getUsername());
		existingBooking.setMovieShow(booking.getMovieShow());
		existingBooking.setSeats(booking.getSeats());
		existingBooking.setStatus(booking.getStatus());
		existingBooking.setAmount(booking.getAmount());
		existingBooking.setPayment(booking.getPayment());
		return bookingRepository.save(existingBooking);
	}

	@Override
	public long bookTicket(String transactionNo) {
		Booking booking = bookingRepository.findByPaymentTransactionNo(transactionNo).orElseThrow(
				() -> new ResourceNotFoundException(CommonConstant.TRANSACTION_NOT_FOUND_WITH_ID + transactionNo));

		booking.setStatus(CommonConstant.BOOKED);
		booking.getSeats().stream().forEach(seat -> seat.setStatus(CommonConstant.BOOKED));
		return bookingRepository.save(booking).getId();
	}

}

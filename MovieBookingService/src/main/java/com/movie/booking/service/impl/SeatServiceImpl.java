package com.movie.booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.entity.Seat;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.repository.SeatRepository;
import com.movie.booking.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;
	
	@Override
	public boolean isSeatAvailable(List<Seat> requestedSeats) {
		boolean isSeatAvailable = true;
		for (Seat seat: requestedSeats) {
			Seat seatById = getSeatById(seat.getId());
			if(seatById.getStatus().equalsIgnoreCase(CommonConstant.BOOKED)){
				isSeatAvailable=false;
				break;
			}
		}
		return isSeatAvailable;
	}

	@Override
	public Seat getSeatById(Long seatId) {
		return seatRepository.findById(seatId)
				.orElseThrow(() -> new ResourceNotFoundException("Seat not found with id :" + seatId));
	}

}

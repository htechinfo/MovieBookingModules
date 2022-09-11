package com.movie.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.entity.Seat;
import com.movie.booking.service.SeatService;

@RestController
@RequestMapping("/api/mb/seats")
public class SeatController {

	@Autowired
	private SeatService seatService;

	@GetMapping("/{id}")
	public Seat getSeatById(@PathVariable (value = "id") long seatId) {
		return seatService.getSeatById(seatId);
	}
}

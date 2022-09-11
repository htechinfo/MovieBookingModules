package com.movie.booking.service;

import java.util.List;

import com.movie.booking.entity.Theatre;

public interface TheaterService {
	public String registerTheater(Theatre theater);
	
	public boolean deRegisterTheater(String uniqueTheaterId);
	
	List<Theatre> getAllTheater();
}

package com.movie.booking.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.booking.entity.Theatre;
import com.movie.booking.repository.TheaterRepository;
import com.movie.booking.service.TheaterService;

@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	TheaterRepository theaterRepository;
	
	@Override
	public String registerTheater(Theatre theater) {
		Optional<Theatre> optionalT = theaterRepository.findByNameAndPinCode(theater.getName(), theater.getPinCode());
		String theaterUniqueId = null;
		if (optionalT.isPresent()) {
			Theatre existing = optionalT.get();
			existing.setIsActive(theater.getIsActive());
			theaterUniqueId = theaterRepository.save(existing).getTheaterUniqueId();
		} else {
			theaterUniqueId = theaterRepository.save(theater).getTheaterUniqueId();
		}
		return theaterUniqueId;
	}

	@Override
	public boolean deRegisterTheater(String uniqueTheaterId) {
		boolean isSuccess = false;
		Optional<Theatre> optionalTheater = theaterRepository.findByTheaterUniqueId(uniqueTheaterId);
		Theatre theater = optionalTheater.orElseThrow(EntityNotFoundException::new);
		theater.setIsActive(false);
		theaterRepository.save(theater);
		isSuccess = true;
		return isSuccess;
	}

	@Override
	public List<Theatre> getAllTheater() {
		return theaterRepository.findAll();
	}

}

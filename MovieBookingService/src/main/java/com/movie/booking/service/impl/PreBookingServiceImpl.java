package com.movie.booking.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.movie.booking.entity.PreBooking;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.repository.PreBookingRepository;
import com.movie.booking.service.PreBookingService;

@Service
public class PreBookingServiceImpl implements PreBookingService {

	@Autowired
	private PreBookingRepository repository;

	@Value("${movie.pre.booking.per.page.records:10}")
	private Integer recordsPerPage;

	@Override
	public Page<PreBooking> getPrebookings(int pageNumber) {
		return repository.findAll(PageRequest.of(pageNumber, recordsPerPage));
	}

	@Override
	public PreBooking createPreBooking(PreBooking preBooking) {
		return repository.save(preBooking);
	}

	@Override
	public PreBooking updatePreBooking(PreBooking preBooking, Long preBookingId) {
		PreBooking existingBooking = this.repository.findById(preBookingId)
				.orElseThrow(() -> new ResourceNotFoundException("PreBooking not found with id :" + preBookingId));
		existingBooking.setLockPattern(preBooking.getLockPattern());
		existingBooking.setUsername(preBooking.getUsername());
		existingBooking.setLockedOn(preBooking.getLockedOn());
		return repository.save(existingBooking);
	}

	@Override
	public void deletePreBooking(Long preBookingId) {
		PreBooking existingBooking = this.repository.findById(preBookingId)
				.orElseThrow(() -> new ResourceNotFoundException("PreBooking not found with id :" + preBookingId));
		this.repository.delete(existingBooking);
	}

	@Override
	public PreBooking reserveSeats(String lockingPattern, String username) {
		PreBooking preBooking = new PreBooking();
		preBooking.setLockPattern(lockingPattern);
		preBooking.setUsername(username);
		preBooking.setLockedOn(LocalDateTime.now());
		return createPreBooking(preBooking);
	}

	@Override
	public Optional<PreBooking> getPreBookingByLockPattern(String lockPattern) {
		return repository.findByLockPattern(lockPattern);
	}

}

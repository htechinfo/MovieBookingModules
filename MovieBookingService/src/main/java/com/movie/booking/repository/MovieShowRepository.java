package com.movie.booking.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.booking.entity.MovieShow;

public interface MovieShowRepository extends JpaRepository<MovieShow, Long> {
	List<MovieShow> findByTheatrePinCode(String pincode);
	
	List<MovieShow> findByTheatrePinCodeAndMovieName(String pincode, String name);
	
	List<MovieShow> findByTheatrePinCodeAndMovieNameAndDate(String pincode, String name, LocalDate showDate);
}

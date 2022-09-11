package com.movie.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.booking.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	Optional<Movie> findByMovieUniqueId(String movieUniqueId);
	
	Optional<Movie> findByName(String name);
}

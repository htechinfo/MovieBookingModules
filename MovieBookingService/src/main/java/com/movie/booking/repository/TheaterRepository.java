package com.movie.booking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.booking.entity.Theatre;

@Repository
public interface TheaterRepository extends JpaRepository<Theatre, Long>{
	Optional<Theatre> findByTheaterUniqueId(String theaterUniqueId);
	
	Optional<Theatre> findByNameAndPinCode(String name, String pinCode);
}

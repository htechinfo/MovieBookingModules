package com.movie.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.booking.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

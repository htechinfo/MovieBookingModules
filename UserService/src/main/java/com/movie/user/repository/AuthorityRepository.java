package com.movie.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.user.entity.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}

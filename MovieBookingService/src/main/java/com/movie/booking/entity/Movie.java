package com.movie.booking.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "movie_sequence")
	private long id;

	@Column
	@NonNull
	private String name;
	
	@Column
	private String movieUniqueId = UUID.randomUUID().toString();

	@Column
	private Boolean isActive;
}

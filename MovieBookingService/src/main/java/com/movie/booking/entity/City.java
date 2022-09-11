package com.movie.booking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "city")
@NoArgsConstructor
@Getter
@Setter
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "city_sequence")
	private long id;

	@Column(unique = true)
	private String name;
}

package com.movie.booking.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seat")
@Getter
@Setter
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seat_sequence")
	private long id;

	@Column(name = "seatNumber")
	private String seatNumber;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "price")
	private BigDecimal price;
}

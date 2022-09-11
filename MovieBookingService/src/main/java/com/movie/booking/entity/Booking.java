package com.movie.booking.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "booking_sequence")
	private long id;
	
	@Column(name = "bookingDate")
	private LocalDateTime bookingDate;
	
	@Column
	private String username;
	
	@Column
	private long userId;

	@OneToOne
	@JoinColumn
	private MovieShow movieShow;

	@OneToMany
	@JoinColumn(name = "booking_id")
	private List<Seat> seats;

	@Column(name = "status")
	private String status;

	@Column(name = "amount")
	private BigDecimal amount;

	@OneToOne
	@Cascade(CascadeType.ALL)
	private Payment payment;
}

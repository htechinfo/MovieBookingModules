package com.movie.booking.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PreBookingLock")
public class PreBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "preBookingLock_sequence")
	private long id;

	@Column(name = "lockPattern")
	private String lockPattern;

	@Column(name = "lockedByUser")
	private String username;

	@Column(name = "lockedOn")
	private LocalDateTime lockedOn;

}

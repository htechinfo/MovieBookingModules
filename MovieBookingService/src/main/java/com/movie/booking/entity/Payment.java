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

@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {

	enum STATUS {
		PENDING,
		SUCCESS,
		FAILED,
		REFUND
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "payment_sequence")
	private long id;
	
	@Column(name = "transactionNo", unique = true)
	private String transactionNo;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "status")
	private String status;
}

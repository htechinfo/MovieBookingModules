package com.movie.booking.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "screen")
@Getter
@Setter
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "screen_sequence")
	private long id;

	@Column
	private String name;
	
	@Column
	private String screenUniqueId = UUID.randomUUID().toString();
}

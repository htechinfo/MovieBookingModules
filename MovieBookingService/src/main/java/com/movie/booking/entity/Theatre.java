package com.movie.booking.entity;

import java.util.List;
import java.util.UUID;

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

@Entity
@Table(name = "theatre")
@Getter
@Setter
public class Theatre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "theatre_sequence")
	private long id;

	@Column
	private String name;
	
	@Column
	private String theaterUniqueId = UUID.randomUUID().toString();
	
	@Column
	private Boolean isActive;
	
    @Column(name = "pinCode")
    private String pinCode;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Address address;
    
	@OneToMany
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "theater_id")
    private List<Screen> screen;
	
}

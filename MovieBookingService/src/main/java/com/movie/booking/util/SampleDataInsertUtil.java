package com.movie.booking.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.entity.Address;
import com.movie.booking.entity.City;
import com.movie.booking.entity.Movie;
import com.movie.booking.entity.MovieShow;
import com.movie.booking.entity.Screen;
import com.movie.booking.entity.Seat;
import com.movie.booking.entity.Theatre;
import com.movie.booking.repository.CityRepository;
import com.movie.booking.repository.MovieRepository;
import com.movie.booking.repository.MovieShowRepository;
import com.movie.booking.repository.TheaterRepository;

@Component
public class SampleDataInsertUtil {

	@Bean
	CommandLineRunner addSanokeData(MovieRepository movieRepository, TheaterRepository theaterRepository,
			MovieShowRepository movieShowRepository, CityRepository cityRepository) {
		return (args) -> {
			City city = getCity("AHMEDABAD");
			cityRepository.saveAll(Arrays.asList(city, getCity("DELHI")));
			
			List<Movie> movies = new ArrayList<Movie>(Arrays.asList(getMovie("Anek"), getMovie("Raid")));
			movieRepository.saveAll(movies);
			
			List<Theatre> theaters = new ArrayList<Theatre>(Arrays.asList(getTheater("PVR Arved", "380059", city, "GUJARAT"),
					getTheater("PVR Acropolish", "380054", city, "GUJARAT")));
			theaterRepository.saveAll(theaters);
			
			List<MovieShow> movieShows = new ArrayList<>();
			theaters.stream().forEach(t -> {
				movies.stream().forEach(m -> {
					movieShows.add(getMovieShow(m, t, LocalDate.now().plusDays(0), LocalTime.now().plusHours(0), t.getScreen().get(0)));
					movieShows.add(getMovieShow(m, t, LocalDate.now().plusDays(1), LocalTime.now().plusHours(1), t.getScreen().get(1)));
				});
			});
			movieShowRepository.saveAll(movieShows);
		};
	}
	
	private MovieShow getMovieShow(Movie movie, Theatre theatre, LocalDate showDate, LocalTime startTime, Screen screen) {
		MovieShow movieShow = new MovieShow();
		movieShow.setDate(showDate);
		movieShow.setStartTime(startTime);
		movieShow.setEndTime(startTime.plusHours(3));
		movieShow.setMovie(movie);
		movieShow.setTheatre(theatre);
		movieShow.setName(theatre.getName() + "_" + movie.getName());
		movieShow.setScreen(screen);
		movieShow.setSeats(getSeats());
		return movieShow;
	}
	
	private Set<Seat> getSeats() {
		Set<Seat> seats = new HashSet<Seat>();
		int lastRow = 'A' + 4;
		for (char row = 'A'; row <= lastRow; row ++) {
			for (int seatNum = 1; seatNum <= 5; seatNum++) {
				Seat seat = new Seat();
				seat.setPrice(new BigDecimal(250));
				seat.setSeatNumber(row + String.format("%02d", seatNum));
				seat.setStatus(CommonConstant.AVAILABLE);
				seat.setType("BASIC");
				seats.add(seat);
			}
		}
		return seats;
	}
	
	private Screen getScreen(String name) {
		Screen screen = new Screen();
		screen.setName(name);
		return screen;
	}

	private Movie getMovie(String name) {
		Movie movie = new Movie();
		movie.setName(name);
		movie.setIsActive(true);
		return movie;
	}

	private Theatre getTheater(String name, String pincode, City city, String state) {
		Theatre theater = new Theatre();
		theater.setName(name);
		theater.setPinCode(pincode);
		theater.setIsActive(true);
		theater.setAddress(getAddress(city, state));
		theater.setScreen(Arrays.asList(getScreen("AUDI-1"),getScreen("AUDI-2")));
		return theater;
	}
	
	private Address getAddress(City city, String state) {
		Address address = new Address();
		address.setAddressLine1("Line-1");
		address.setAddressLine2("Line-2");
		address.setCity(city);
		address.setCountry("India");
		address.setState(state);
		return address;
	}
	
	private City getCity(String name) {
		City city = new City();
		city.setName(name);
		return city;
	}
}

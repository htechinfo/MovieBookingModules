package com.movie.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.entity.City;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.repository.CityRepository;

import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api/mb/city")
public class CityController {

	@Autowired
	private CityRepository cityRepository;

	@GetMapping
	public List<City> getAllCity() {
		return this.cityRepository.findAll();
	}

	@Hidden
	@GetMapping("/{id}")
	public City getCityById(@PathVariable (value = "id") long cityId) {
		return this.cityRepository.findById(cityId)
				.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.CITY_NOT_FOUND_WITH_ID + cityId));
	}

	@PostMapping
	public City createCity(@RequestParam ("city name") String cityName) {
		City city = new City();
		city.setName(cityName);
		return this.cityRepository.save(city);
	}

	@Hidden
	@PutMapping("/{id}")
	public City updateCity(@RequestBody City city, @PathVariable ("id") long cityId) {
		 City existingCity = this.cityRepository.findById(cityId)
			.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.CITY_NOT_FOUND_WITH_ID + cityId));
		 existingCity.setName(city.getName());
		 return this.cityRepository.save(existingCity);
	}

	@Hidden
	@DeleteMapping("/{id}")
	public ResponseEntity<City> deleteCity(@PathVariable ("id") long cityId){
		 City existingCity = this.cityRepository.findById(cityId)
					.orElseThrow(() -> new ResourceNotFoundException(CommonConstant.CITY_NOT_FOUND_WITH_ID+ cityId));
		 this.cityRepository.delete(existingCity);
		 return ResponseEntity.ok().build();
	}
}

package com.movie.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.movie.booking.constants.CommonConstant;
import com.movie.booking.entity.MovieShow;
import com.movie.booking.exception.ResourceNotFoundException;
import com.movie.booking.repository.MovieShowRepository;
import com.movie.booking.service.MovieShowService;

@Service
public class MovieShowServiceImpl implements MovieShowService {

	@Autowired
	private MovieShowRepository movieShowRepository;
	
	@Value("${movie.show.per.page.records:10}")
	private Integer perPageRecords;
	
	@Override
	public MovieShow updateMovieShow(MovieShow movieShow, long movieShowId) {

		MovieShow existingMovieShow = this.movieShowRepository.findById(movieShowId).orElseThrow(
				() -> new ResourceNotFoundException(CommonConstant.MOVIE_SHOW_NOT_FOUND_WITH_ID + movieShowId));
		existingMovieShow.setName(movieShow.getName());
		existingMovieShow.setDate(movieShow.getDate());
		existingMovieShow.setStartTime(movieShow.getStartTime());
		existingMovieShow.setEndTime(movieShow.getEndTime());
		existingMovieShow.setMovie(movieShow.getMovie());
		existingMovieShow.setTheatre(movieShow.getTheatre());
		existingMovieShow.setScreen(movieShow.getScreen());
		existingMovieShow.setSeats(movieShow.getSeats());
		return this.movieShowRepository.save(existingMovieShow);
	}

	@Override
	public Page<MovieShow> getAllMovieShow(Integer pageNumber) {
		return movieShowRepository.findAll(PageRequest.of(pageNumber, perPageRecords));
	}

	@Override
	public MovieShow deleteMovieShow(long movieShowId) {
		MovieShow existingMovieShow = this.movieShowRepository.findById(movieShowId).orElseThrow(
				() -> new ResourceNotFoundException(CommonConstant.MOVIE_SHOW_NOT_FOUND_WITH_ID + movieShowId));
		this.movieShowRepository.delete(existingMovieShow);
		return existingMovieShow;
	}

}

package com.movie.booking.model;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.movie.booking.entity.Movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieShowModel extends RepresentationModel<MovieShowModel> {

    private long id;
    private String name;
    private Date date;
    private Date startTime;
    private Date endTime;
    private Movie movie;
    private TheaterModel theater;
}

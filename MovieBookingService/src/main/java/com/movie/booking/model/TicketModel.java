package com.movie.booking.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.movie.booking.entity.Payment;
import com.movie.booking.entity.Seat;

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
public class TicketModel extends RepresentationModel<TicketModel> {

    private long id;
    private String userName;
    private Date bookingDate;
    private String movieName;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<Seat> seats;
    private String status;
    private BigDecimal amount;
    private String theaterName;
    private String screen;
    private String qr_code_link;
    private Payment payment;

}

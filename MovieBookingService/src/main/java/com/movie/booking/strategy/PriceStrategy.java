package com.movie.booking.strategy;

import java.math.BigDecimal;

import com.movie.booking.entity.Booking;

public interface PriceStrategy {

    public BigDecimal calculateTotal(Booking booking);
}

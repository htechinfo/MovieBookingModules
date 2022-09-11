package com.movie.booking.util;

import java.math.BigDecimal;

import com.movie.booking.entity.Booking;
import com.movie.booking.strategy.PriceStrategy;

public class PriceCalculator {
    private PriceStrategy priceStrategy;

    public PriceCalculator(PriceStrategy priceStrategy){
        this.priceStrategy = priceStrategy;
    }

    public BigDecimal calculate(Booking booking){
        return priceStrategy.calculateTotal(booking);
    }
}

package com.movie.booking.strategy;

import java.math.BigDecimal;

import com.movie.booking.entity.Booking;
import com.movie.booking.entity.Seat;

public class NoDiscountStrategy implements PriceStrategy{

    @Override
    public BigDecimal calculateTotal(Booking booking) {
        BigDecimal total= BigDecimal.valueOf(0);
        for (Seat seat: booking.getSeats()) {
            total = total.add(seat.getPrice());
        }
        return total;
    }
}

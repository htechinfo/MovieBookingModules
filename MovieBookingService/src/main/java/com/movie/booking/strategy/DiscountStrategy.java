package com.movie.booking.strategy;

import java.math.BigDecimal;

import com.movie.booking.entity.Booking;
import com.movie.booking.entity.Seat;

public class DiscountStrategy implements PriceStrategy{

    @Override
    public BigDecimal calculateTotal(Booking booking) {
        BigDecimal total= BigDecimal.valueOf(0);
        for (Seat seat: booking.getSeats()) {
            total = total.add(seat.getPrice());
        }
        /**
         * 10% discount is hardcoded here can be fetched from database
         * OR
         * Can be calculated with DRL rules which city which theater who should get how much discount
         */

        return total.subtract(total.multiply(BigDecimal.valueOf(0.1)));
    }
}

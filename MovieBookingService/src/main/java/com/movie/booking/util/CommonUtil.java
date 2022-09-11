package com.movie.booking.util;

import com.movie.booking.entity.Booking;

public interface CommonUtil {
    public static String getLockPattern(Booking booking) {

        StringBuilder seatIds = new StringBuilder();
        booking.getSeats().forEach(seat -> seatIds.append(seat.getId()));

        String showId = String.valueOf(booking.getMovieShow().getId());

        return showId + seatIds;
    }
}

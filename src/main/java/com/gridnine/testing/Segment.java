package com.gridnine.testing;

import com.gridnine.testing.exception.ArrivalTimeIsNotCorrectException;
import com.gridnine.testing.exception.FlightTimeIsNotCorrectException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Bean that represents a flight segment.
 */
class Segment {
    private final LocalDateTime departureDate;

    private final LocalDateTime arrivalDate;

    Segment(final LocalDateTime dep, final LocalDateTime arr) {
        if(dep.isAfter(LocalDateTime.now())){
            if (arr.isAfter(dep)) {
                departureDate = Objects.requireNonNull(dep);
                arrivalDate = Objects.requireNonNull(arr);
            } else {
                throw new ArrivalTimeIsNotCorrectException("Arrival time shouldn't be before departure time");
            }
        } else {
            throw new FlightTimeIsNotCorrectException("Time flight is before actual time!");
        }
    }

    LocalDateTime getDepartureDate() {
        return departureDate;
    }

    LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
                + ']';
    }
}

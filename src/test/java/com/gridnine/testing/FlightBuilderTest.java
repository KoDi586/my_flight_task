package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.*;

class FlightBuilderTest {
    private static final DateTimeFormatter fmt =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");;

    @Test
    void testNormalCreate() {
        LocalDateTime dep = LocalDateTime.now().plusHours(3);
        LocalDateTime arr = dep.plusHours(2);

        Flight flight = FlightBuilder.createFlight(dep, arr);

        assertNotNull(flight);
        assertEquals(1, flight.getSegments().size());
    }

    @Test
    void testOneArgException() {
        LocalDateTime dep = LocalDateTime.now();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> {
            FlightBuilder.createFlight(dep);
        });

        assertEquals("you must pass an even number of dates", exception.getMessage());
    }

    @Test
    void testIncorrectFlightTime() {
        LocalDateTime dep = LocalDateTime.now().plusHours(7);
        LocalDateTime arr = dep.minusHours(1); // Incorrect flight time

        Flight flight = FlightBuilder.createFlight(dep, arr);

        assertNotNull(flight);
        assertNull(flight.getSegments());
    }
}

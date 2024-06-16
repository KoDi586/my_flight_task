package com.gridnine.testing;

import com.gridnine.testing.exception.ArrivalTimeIsNotCorrectException;
import com.gridnine.testing.exception.FlightTimeIsNotCorrectException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SegmentTest {

    @Test
    void testNormal() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        LocalDateTime later = now.plusHours(2);

        Segment segment = new Segment(now, later);

        assertNotNull(segment);
        assertEquals(now, segment.getDepartureDate());
        assertEquals(later, segment.getArrivalDate());
    }

    @Test
    void testFlightException() {
        LocalDateTime past = LocalDateTime.now().minusHours(1);
        LocalDateTime later = past.plusHours(2);

        FlightTimeIsNotCorrectException exception = assertThrows(
                FlightTimeIsNotCorrectException.class, () -> {
                    new Segment(past, later);
                });

        assertEquals("Time flight is before actual time!", exception.getMessage());
    }

    @Test
    void testArrBeforeDepException() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        LocalDateTime earlier = now.minusHours(1);

        ArrivalTimeIsNotCorrectException exception = assertThrows(
                ArrivalTimeIsNotCorrectException.class, () -> {
                    new Segment(now, earlier);
                });

        assertEquals(
                "Arrival time shouldn't be before departure time",
                exception.getMessage());
    }

    @Test
    void testArrEqualsDepException() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);

        ArrivalTimeIsNotCorrectException exception = assertThrows(
                ArrivalTimeIsNotCorrectException.class, () -> {
                    new Segment(now, now);
                });

        assertEquals(
                "Arrival time shouldn't be before departure time",
                exception.getMessage());
    }
}



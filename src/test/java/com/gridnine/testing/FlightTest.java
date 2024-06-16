package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void testEmptySegmentList() {
        List<Segment> segments = new ArrayList<>();

        Flight flight = new Flight(segments);

        assertNull(flight.getSegments());
    }

    @Test
    void testCreateOneFlight() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        LocalDateTime later = now.plusHours(2);
        Segment segment = new Segment(now, later);
        List<Segment> segments = List.of(segment);

        Flight flight = new Flight(segments);

        assertNotNull(flight.getSegments());
        assertEquals(1, flight.getSegments().size());
        assertEquals(segment, flight.getSegments().get(0));
    }

    @Test
    void testWithMultipleSegments() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        LocalDateTime later = now.plusHours(2);
        LocalDateTime later2 = later.plusHours(1);
        LocalDateTime later3 = later2.plusHours(4);

        Segment segment1 = new Segment(now, later);
        Segment segment2 = new Segment(later2, later3);
        List<Segment> segments = List.of(segment1, segment2);

        Flight flight = new Flight(segments);

        assertNotNull(flight.getSegments());
        assertEquals(2, flight.getSegments().size());
        assertEquals(segment1, flight.getSegments().get(0));
        assertEquals(segment2, flight.getSegments().get(1));
    }

    @Test
    void testBigInvalidInterval() {
        LocalDateTime now = LocalDateTime.now().plusHours(1);
        LocalDateTime later = now.plusHours(2);
        LocalDateTime later2 = later.plusHours(3); // Less than 2 hours interval
        LocalDateTime later3 = later2.plusHours(2);

        Segment segment1 = new Segment(now, later);
        Segment segment2 = new Segment(later2, later3);
        List<Segment> segments = List.of(segment1, segment2);

        Flight flight = new Flight(segments);

        assertNotNull(flight.getSegments());
        assertEquals(1, flight.getSegments().size());
        assertEquals(segment2, flight.getSegments().get(0));
    }
}
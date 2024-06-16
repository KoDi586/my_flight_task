package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean that represents a flight.
 */
class Flight {
    private final List<Segment> segments;

    Flight(final List<Segment> segs) {
        List<Segment> segmentList = new ArrayList<>(List.of());
        if (segs.isEmpty()) {
            segments = null;
        } else if (segs.size() == 1) {
            segmentList.add(segs.get(0));
            segments = segmentList;
        } else {
            for (int i = 0; i < segs.size() - 1; i++) {
                LocalDateTime firstArrDate = segs.get(i).getArrivalDate();
                LocalDateTime secondDepDate = segs.get(i + 1).getDepartureDate();
                if (firstArrDate.plusHours(2).isAfter(secondDepDate)) {
                    segmentList.add(segs.get(i));
                } else {
                    System.out.println("many 2 hour exception between first arrival datetime " +
                            "and second departure datetime");
                }
            }
            segmentList.add(segs.get(segs.size() - 1));
            segments = segmentList;
        }
    }


    List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        if (segments == null) {
            return null;
        }
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}


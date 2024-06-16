package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("\nList of flights:\n");
        System.out.println(flights.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n----------------\n"))
        );
    }
}

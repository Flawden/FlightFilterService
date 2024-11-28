package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрует полеты, время которых превышает заданный предел.
 */
public class FlightDurationGreaterThanFilter implements Filter<Flight> {
    private final long maxDurationMinutes;

    public FlightDurationGreaterThanFilter(long maxDurationMinutes) {
        this.maxDurationMinutes = maxDurationMinutes;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    long totalDuration = 0;
                    for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                        Segment currentSegment = flight.getSegments().get(i);
                        Segment nextSegment = flight.getSegments().get(i + 1);
                        totalDuration += Duration.between(currentSegment.getDepartureDate(), nextSegment.getArrivalDate()).toMinutes();
                    }
                    totalDuration += Duration.between(flight.getSegments().get(flight.getSegments().size() - 1).getDepartureDate(),
                            flight.getSegments().get(flight.getSegments().size() - 1).getArrivalDate()).toMinutes();
                    return totalDuration > maxDurationMinutes;
                })
                .collect(Collectors.toList());
    }
}
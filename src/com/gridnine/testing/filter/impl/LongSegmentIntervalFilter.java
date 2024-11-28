package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрует полеты, где время между сегментами больше заданного.
 */
public class LongSegmentIntervalFilter implements Filter<Flight> {
    private final long maxIntervalMinutes;

    public LongSegmentIntervalFilter(long maxIntervalMinutes) {
        this.maxIntervalMinutes = maxIntervalMinutes;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                        Segment currentSegment = flight.getSegments().get(i);
                        Segment nextSegment = flight.getSegments().get(i + 1);
                        long interval = Duration.between(currentSegment.getArrivalDate(), nextSegment.getDepartureDate()).toMinutes();
                        if (interval > maxIntervalMinutes) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}

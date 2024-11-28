package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрует полеты, где все сегменты находятся в пределах указанного временного диапазона.
 */
public class FlightInTimeRangeFilter implements Filter<Flight> {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public FlightInTimeRangeFilter(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> !segment.getDepartureDate().isBefore(startTime) &&
                                !segment.getArrivalDate().isAfter(endTime)))
                .collect(Collectors.toList());
    }
}

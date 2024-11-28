package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрует полеты, которые вылетают после указанной даты и времени.
 */
public class FlightsAfterSpecificDateFilter implements Filter<Flight> {
    private final LocalDateTime specificDate;

    public FlightsAfterSpecificDateFilter(LocalDateTime specificDate) {
        this.specificDate = specificDate;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(specificDate)))
                .collect(Collectors.toList());
    }
}

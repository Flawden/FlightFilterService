package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрует полеты, у которых хотя бы один сегмент пересекает текущую дату.
 */
public class FlightCrossesCurrentDateFilter implements Filter<Flight> {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()) &&
                                segment.getArrivalDate().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }
}


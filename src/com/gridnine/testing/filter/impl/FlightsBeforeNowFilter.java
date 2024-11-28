package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрует рейсы, которые вылетают до текущего момента времени.
 * <p>
 * Этот фильтр исключает все рейсы, у которых время вылета меньше текущего времени.
 * Например, если сейчас 12:00, то все рейсы, вылетающие до 12:00, будут исключены.
 */
public class FlightsBeforeNowFilter implements Filter<Flight> {

    /**
     * Фильтрует список рейсов, исключая те, которые вылетают до текущего времени.
     *
     * @param flights Список рейсов для фильтрации.
     * @return Отфильтрованный список рейсов, вылетающих после текущего времени.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getDepartureDate().isBefore(now)))
                .collect(Collectors.toList());
    }
}
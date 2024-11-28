package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрует рейсы, где дата прибытия сегмента раньше даты вылета.
 * <p>
 * Этот фильтр исключает рейсы, в которых хотя бы один сегмент имеет дату прибытия раньше
 * даты вылета. Это может быть ошибкой в расписании, так как в реальных условиях рейс не может
 * прибыть раньше вылета.
 */
public class ArrivalBeforeDepartureFilter implements Filter<Flight> {

    /**
     * Фильтрует список рейсов, исключая те, где дата прибытия сегмента раньше даты вылета.
     *
     * @param flights Список рейсов для фильтрации.
     * @return Отфильтрованный список рейсов, где дата прибытия не раньше даты вылета.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }

}

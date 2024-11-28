package com.gridnine.testing.filter.impl;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Фильтрует рейсы с временем на земле, превышающим два часа.
 * <p>
 * Этот фильтр исключает рейсы, у которых суммарное время на земле (время между прилетом одного сегмента
 * и вылетом следующего) превышает два часа. Время на земле рассчитывается между прилетом одного сегмента
 * и вылетом следующего сегмента в рамках рейса.
 */
public class LongGroundTimeFilter implements Filter<Flight> {

    private static final int MAX_GROUND_TIME_MINUTES = 120;

    /**
     * Фильтрует список рейсов, исключая те, у которых время на земле больше двух часов.
     *
     * @param flights Список рейсов для фильтрации.
     * @return Отфильтрованный список рейсов, где время на земле не превышает двух часов.
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    long totalGroundTime = 0;
                    List<Segment> segments = flight.getSegments();
                    for (int i = 0; i < segments.size() - 1; i++) {
                        Segment currentSegment = segments.get(i);
                        Segment nextSegment = segments.get(i + 1);
                        totalGroundTime += Duration.between(currentSegment.getArrivalDate(), nextSegment.getDepartureDate()).toMinutes();
                    }
                    return totalGroundTime <= MAX_GROUND_TIME_MINUTES;
                })
                .collect(Collectors.toList());
    }
}

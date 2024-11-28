package com.gridnine.testing.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, представляющий сегмент перелета.
 * Сегмент состоит из даты/времени вылета и даты/времени прилета.
 */
public class Segment {
    private final LocalDateTime departureDate;

    private final LocalDateTime arrivalDate;

    /**
     * Конструктор для создания сегмента перелета.
     *
     * @param departureDate дата и время вылета
     * @param arrivalDate   дата и время прилета
     */
    public Segment(LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    /**
     * Получить дату и время вылета.
     *
     * @return дата и время вылета
     */
    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    /**
     * Получить дату и время прилета.
     *
     * @return дата и время прилета
     */
    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Представление объекта сегмента в виде строки в формате [yyyy-MM-dd'T'HH:mm|yyyy-MM-dd'T'HH:mm].
     *
     * @return строковое представление сегмента
     */
    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
                + ']';
    }
}

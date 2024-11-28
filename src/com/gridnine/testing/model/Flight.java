package com.gridnine.testing.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, представляющий перелет.
 * Перелет состоит из нескольких сегментов (переходов от одного аэропорта к другому).
 */
public class Flight {

    private final List<Segment> segments;

    /**
     * Конструктор для создания перелета с переданными сегментами.
     *
     * @param segments список сегментов, из которых состоит перелет
     */
    public Flight(List<Segment> segments) {
        this.segments = segments;
    }

    /**
     * Получить список сегментов перелета.
     *
     * @return список сегментов
     */
    public List<Segment> getSegments() {
        return segments;
    }

    /**
     * Представление объекта перелета в виде строки.
     * Сегменты перелета отображаются как строки с информацией о вылете и прилете.
     *
     * @return строковое представление перелета
     */
    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }

}

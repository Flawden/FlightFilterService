package com.gridnine.testing.util;

import com.gridnine.testing.filter.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для обработки фильтров.
 * Этот класс принимает список фильтров и применяет их к списку объектов.
 */
public class FilterProcessor<T> {

    /**
     * Применяет все фильтры из списка к переданным объектам и возвращает отфильтрованный результат.
     *
     * @param items   список объектов для фильтрации
     * @param filters список фильтров, которые необходимо применить
     * @param <T>     тип объектов, которые фильтруются
     * @return отфильтрованный список объектов
     */
    public List<T> runAllFilters(List<T> items, List<Filter<T>> filters) {
        List<T> filteredItems = new ArrayList<>(items);
        for (Filter<T> filter : filters) {
            filteredItems = filter.filter(filteredItems);
        }
        return filteredItems;
    }
}

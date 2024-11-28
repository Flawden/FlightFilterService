package com.gridnine.testing;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.filter.impl.*;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.util.FilterProcessor;
import com.gridnine.testing.util.FlightBuilder;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Главный класс для запуска программы.
 * В нем создаются перелеты, применяются фильтры и выводится результат на консоль.
 */
public class Main {

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime threeDaysFromNow = now.plusDays(3);
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("### Все перелеты ###");
        flights.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        // Фильтры
        Filter<Flight> filter1 = new FlightsBeforeNowFilter();
        Filter<Flight> filter2 = new ArrivalBeforeDepartureFilter();
        Filter<Flight> filter3 = new LongGroundTimeFilter();
        Filter<Flight> filter4 = new FlightsAfterSpecificDateFilter(now);
        Filter<Flight> filter5 = new FlightDurationGreaterThanFilter(5 * 60);
        Filter<Flight> filter6 = new LongSegmentIntervalFilter(60);
        Filter<Flight> filter7 = new FlightInTimeRangeFilter(threeDaysFromNow, threeDaysFromNow.plusDays(2));
        Filter<Flight> filter8 = new FlightCrossesCurrentDateFilter();
        Filter<Flight> filter9 = new FlightWithPastSegmentFilter();

        // Фильтрация с тремя фильтрами
        System.out.println("### Применяем все три фильтра ###");
        FilterProcessor filterProcessor = new FilterProcessor();
        List<Flight> filteredFlights = filterProcessor.runAllFilters(flights, List.of(filter1, filter2, filter3));
        filteredFlights.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        // Фильтрация с одним фильтром (FlightsBeforeNowFilter)
        System.out.println("### Применяем только фильтр: FlightsBeforeNowFilter ###");
        List<Flight> filteredFlights1 = filterProcessor.runAllFilters(flights, List.of(filter1));
        filteredFlights1.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        // Фильтрация с двумя фильтрами (FlightsBeforeNowFilter и LongGroundTimeFilter)
        System.out.println("### Применяем фильтры: FlightsBeforeNowFilter и LongGroundTimeFilter ###");
        List<Flight> filteredFlights2 = filterProcessor.runAllFilters(flights, List.of(filter1, filter3));
        filteredFlights2.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        // Фильтрация с двумя фильтрами (ArrivalBeforeDepartureFilter и LongGroundTimeFilter)
        System.out.println("### Применяем фильтры: ArrivalBeforeDepartureFilter и LongGroundTimeFilter ###");
        List<Flight> filteredFlights3 = filterProcessor.runAllFilters(flights, List.of(filter2, filter3));
        filteredFlights3.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        System.out.println("### Полеты, вылетающие после текущего времени: ###");
        List<Flight> filteredFlights4 = filter4.filter(flights);
        filteredFlights4.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        System.out.println("### Полеты, время которых больше 5 часов: ###");
        List<Flight> filteredFlights5 = filter5.filter(flights);
        filteredFlights5.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        System.out.println("### Полеты, где время между сегментами больше 60 минут: ###");
        List<Flight> filteredFlights6 = filter6.filter(flights);
        filteredFlights6.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        System.out.println("### Полеты, находящиеся в пределах времени с 3 по 5 день: ###");
        List<Flight> filteredFlights7 = filter7.filter(flights);
        filteredFlights7.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        System.out.println("### Полеты, где хотя бы один сегмент пересекает текущую дату: ###");
        List<Flight> filteredFlights8 = filter8.filter(flights);
        filteredFlights8.forEach(System.out::println);

        System.out.println("\n--------------------------------\n");

        System.out.println("### Полеты с хотя бы одним сегментом вылетевшим в прошлом: ###");
        List<Flight> filteredFlights9 = filter9.filter(flights);
        filteredFlights9.forEach(System.out::println);
    }
}

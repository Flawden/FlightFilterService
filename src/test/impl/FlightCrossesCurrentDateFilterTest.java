package test.impl;

import com.gridnine.testing.filter.impl.FlightCrossesCurrentDateFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import test.annotation.TestClass;
import test.model.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

;

@TestClass
public class FlightCrossesCurrentDateFilterTest implements Test {

    @Override
    public void test() {
        System.out.println("Тест: FlightCrossesCurrentDateFilter");

        List<Flight> flights = createTestFlights();

        FlightCrossesCurrentDateFilter filter = new FlightCrossesCurrentDateFilter();
        List<Flight> result = filter.filter(flights);

        boolean isValid = true;
        for (Flight flight : result) {
            boolean matchesFilter = flight.getSegments().stream()
                    .anyMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()) &&
                            segment.getArrivalDate().isAfter(LocalDateTime.now()));

            if (!matchesFilter) {
                isValid = false;
                System.out.println("Тест не прошел: Найден рейс, не пересекающий текущую дату.");
                break;
            }
        }

        if (isValid) {
            System.out.println("Тест прошел: Все полеты прошли фильтрацию.");
        } else {
            System.out.println("Тест не прошел.");
        }
    }

    private List<Flight> createTestFlights() {
        Segment segment1 = new Segment(
                LocalDateTime.of(2024, 11, 28, 10, 0),
                LocalDateTime.of(2024, 11, 28, 12, 0)
        );
        Segment segment2 = new Segment(
                LocalDateTime.of(2024, 11, 28, 14, 0),
                LocalDateTime.of(2024, 11, 28, 16, 0)
        );
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));

        Segment segment3 = new Segment(
                LocalDateTime.of(2024, 11, 27, 10, 0),
                LocalDateTime.of(2024, 11, 27, 12, 0)
        );
        Segment segment4 = new Segment(
                LocalDateTime.of(2024, 11, 27, 14, 0),
                LocalDateTime.of(2024, 11, 27, 16, 0)
        );
        Flight flight2 = new Flight(Arrays.asList(segment3, segment4));

        return Arrays.asList(flight1, flight2);
    }
}


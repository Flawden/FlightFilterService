package test.impl;

import com.gridnine.testing.filter.impl.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import test.annotation.TestClass;
import test.model.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@TestClass
public class ArrivalBeforeDepartureFilterTest implements Test {

    @Override
    public void test() {
        List<Flight> flights = createTestFlights();

        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();
        List<Flight> result = filter.filter(flights);

        boolean isValid = true;
        for (Flight flight : result) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    isValid = false;
                    System.out.println("Тест не прошел: Найден рейс с ошибочной датой прибытия.");
                    break;
                }
            }
            if (!isValid) {
                break;
            }
        }

        if (isValid) {
            System.out.println("Тест прошел: Все рейсы прошли фильтрацию.");
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
                LocalDateTime.of(2024, 11, 28, 10, 0),
                LocalDateTime.of(2024, 11, 28, 9, 30)
        );
        Flight flight2 = new Flight(List.of(segment3));

        return Arrays.asList(flight1, flight2);
    }
}

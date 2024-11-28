package test.impl;

import com.gridnine.testing.filter.impl.FlightsAfterSpecificDateFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import test.annotation.TestClass;
import test.model.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@TestClass
public class FlightsAfterSpecificDateFilterTest implements Test {

    @Override
    public void test() {
        System.out.println("Тест: FlightsAfterSpecificDateFilter");
        LocalDateTime specificDate = LocalDateTime.of(2024, 11, 28, 12, 0);
        List<Flight> flights = createTestFlights();
        FlightsAfterSpecificDateFilter filter = new FlightsAfterSpecificDateFilter(specificDate);
        List<Flight> result = filter.filter(flights);

        boolean isValid = true;
        for (Flight flight : result) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(specificDate)) {
                    isValid = false;
                    System.out.println("Тест не прошел: Найден сегмент с вылетом до указанной даты.");
                    break;
                }
            }
            if (!isValid) {
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
                LocalDateTime.of(2024, 11, 28, 13, 0),
                LocalDateTime.of(2024, 11, 28, 14, 0)
        );
        Segment segment2 = new Segment(
                LocalDateTime.of(2024, 11, 28, 15, 0),
                LocalDateTime.of(2024, 11, 28, 16, 0)
        );
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));

        Segment segment3 = new Segment(
                LocalDateTime.of(2024, 11, 28, 11, 0),
                LocalDateTime.of(2024, 11, 28, 12, 30)
        );
        Segment segment4 = new Segment(
                LocalDateTime.of(2024, 11, 28, 14, 0),
                LocalDateTime.of(2024, 11, 28, 15, 0)
        );
        Flight flight2 = new Flight(Arrays.asList(segment3, segment4));

        return Arrays.asList(flight1, flight2);
    }
}

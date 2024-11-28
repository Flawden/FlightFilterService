package test.impl;

import com.gridnine.testing.filter.impl.FlightDurationGreaterThanFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import test.annotation.TestClass;
import test.model.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@TestClass
public class FlightDurationGreaterThanFilterTest implements Test {

    @Override
    public void test() {
        System.out.println("Тест: FlightDurationGreaterThanFilter");

        List<Flight> flights = createTestFlights();

        FlightDurationGreaterThanFilter filter = new FlightDurationGreaterThanFilter(300);
        List<Flight> result = filter.filter(flights);

        boolean isValid = true;
        for (Flight flight : result) {
            long totalDuration = calculateTotalDuration(flight);
            if (totalDuration <= 300) {
                isValid = false;
                System.out.println("Тест не прошел: Найден рейс с продолжительностью меньше или равной 300 минут.");
                break;
            }
        }

        if (isValid) {
            System.out.println("Тест прошел: Все полеты прошли фильтрацию.");
        } else {
            System.out.println("Тест не прошел.");
        }
    }

    private long calculateTotalDuration(Flight flight) {
        long totalDuration = 0;
        for (int i = 0; i < flight.getSegments().size() - 1; i++) {
            Segment currentSegment = flight.getSegments().get(i);
            Segment nextSegment = flight.getSegments().get(i + 1);
            totalDuration += Duration.between(currentSegment.getDepartureDate(), nextSegment.getArrivalDate()).toMinutes();
        }
        totalDuration += Duration.between(flight.getSegments().getLast().getDepartureDate(), flight.getSegments().getLast().getArrivalDate()).toMinutes();
        return totalDuration;
    }


    private List<Flight> createTestFlights() {
        Segment segment1 = new Segment(
                LocalDateTime.of(2024, 11, 28, 10, 0),
                LocalDateTime.of(2024, 11, 28, 12, 0)
        );
        Segment segment2 = new Segment(
                LocalDateTime.of(2024, 11, 28, 13, 0),
                LocalDateTime.of(2024, 11, 28, 15, 30)
        );
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));

        Segment segment3 = new Segment(
                LocalDateTime.of(2024, 11, 28, 10, 0),
                LocalDateTime.of(2024, 11, 28, 10, 30)
        );
        Segment segment4 = new Segment(
                LocalDateTime.of(2024, 11, 28, 11, 0),
                LocalDateTime.of(2024, 11, 28, 11, 30)
        );
        Flight flight2 = new Flight(Arrays.asList(segment3, segment4));

        return Arrays.asList(flight1, flight2);
    }
}

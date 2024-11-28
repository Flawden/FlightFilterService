package test.impl;

import com.gridnine.testing.filter.impl.FlightWithPastSegmentFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import test.annotation.TestClass;
import test.model.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@TestClass
public class FlightWithPastSegmentFilterTest implements Test {

    @Override
    public void test() {
        System.out.println("Тест: FlightWithPastSegmentFilter");

        LocalDateTime now = LocalDateTime.now();
        List<Flight> flights = createTestFlights(now);

        FlightWithPastSegmentFilter filter = new FlightWithPastSegmentFilter();
        List<Flight> result = filter.filter(flights);

        boolean isValid = true;
        for (Flight flight : result) {
            boolean hasPastSegment = flight.getSegments().stream()
                    .anyMatch(segment -> segment.getDepartureDate().isBefore(now));
            if (!hasPastSegment) {
                isValid = false;
                System.out.println("Тест не прошел: Рейс не содержит сегмента с вылетом до текущего времени.");
                break;
            }
        }

        if (isValid) {
            System.out.println("Тест прошел: Все полеты прошли фильтрацию.");
        } else {
            System.out.println("Тест не прошел.");
        }
    }

    private List<Flight> createTestFlights(LocalDateTime now) {

        Segment segment1 = new Segment(
                now.minusMinutes(10),
                now.plusMinutes(30)
        );
        Segment segment2 = new Segment(
                now.plusMinutes(40),
                now.plusMinutes(60)
        );
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));

        Segment segment3 = new Segment(
                now.plusMinutes(20),
                now.plusMinutes(40)
        );
        Segment segment4 = new Segment(
                now.plusMinutes(50),
                now.plusMinutes(70)
        );
        Flight flight2 = new Flight(Arrays.asList(segment3, segment4));

        return Arrays.asList(flight1, flight2);
    }
}


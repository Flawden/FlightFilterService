package test.impl;

import com.gridnine.testing.filter.impl.FlightsBeforeNowFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import test.annotation.TestClass;
import test.model.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@TestClass
public class FlightsBeforeNowFilterTest implements Test {

    @Override
    public void test() {
        System.out.println("Тест: FlightsBeforeNowFilter");

        LocalDateTime now = LocalDateTime.now();
        List<Flight> flights = createTestFlights(now);
        FlightsBeforeNowFilter filter = new FlightsBeforeNowFilter();
        List<Flight> result = filter.filter(flights);

        boolean isValid = true;
        for (Flight flight : result) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getDepartureDate().isBefore(now)) {
                    isValid = false;
                    System.out.println("Тест не прошел: Найден сегмент с вылетом до текущего времени.");
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

    private List<Flight> createTestFlights(LocalDateTime now) {
        Segment segment1 = new Segment(
                now.plusMinutes(10),
                now.plusMinutes(30)
        );
        Segment segment2 = new Segment(
                now.plusMinutes(40),
                now.plusMinutes(60)
        );
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));
        Segment segment3 = new Segment(
                now.minusMinutes(10),
                now.plusMinutes(30)
        );
        Segment segment4 = new Segment(
                now.plusMinutes(50),
                now.plusMinutes(70)
        );
        Flight flight2 = new Flight(Arrays.asList(segment3, segment4));

        return Arrays.asList(flight1, flight2);
    }
}


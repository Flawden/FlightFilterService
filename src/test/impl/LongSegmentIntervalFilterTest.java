package test.impl;

import com.gridnine.testing.filter.impl.LongSegmentIntervalFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import test.annotation.TestClass;
import test.model.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@TestClass
public class LongSegmentIntervalFilterTest implements Test {

    private static final long MAX_INTERVAL = 180;

    @Override
    public void test() {
        System.out.println("Тест: LongSegmentIntervalFilter");

        LocalDateTime now = LocalDateTime.now();
        List<Flight> flights = createTestFlights(now);
        LongSegmentIntervalFilter filter = new LongSegmentIntervalFilter(MAX_INTERVAL);
        List<Flight> result = filter.filter(flights);

        boolean isValid = true;
        for (Flight flight : result) {
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                Segment currentSegment = segments.get(i);
                Segment nextSegment = segments.get(i + 1);
                long interval = Duration.between(currentSegment.getArrivalDate(), nextSegment.getDepartureDate()).toMinutes();
                if (interval > MAX_INTERVAL) {
                    isValid = false;
                    System.out.println("Тест не прошел: Интервал между сегментами больше 3 часов.");
                    break;
                }
            }
            if (!isValid) break;
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
                now.plusMinutes(50)
        );
        Segment segment2 = new Segment(
                now.plusMinutes(60),
                now.plusMinutes(100)
        );
        Flight flight1 = new Flight(Arrays.asList(segment1, segment2));

        Segment segment3 = new Segment(
                now.plusMinutes(10),
                now.plusMinutes(100)
        );
        Segment segment4 = new Segment(
                now.plusMinutes(240),
                now.plusMinutes(260)
        );
        Flight flight2 = new Flight(Arrays.asList(segment3, segment4));

        return Arrays.asList(flight1, flight2);
    }
}

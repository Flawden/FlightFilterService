package test.impl;

import com.gridnine.testing.filter.impl.LongGroundTimeFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import test.annotation.TestClass;
import test.model.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@TestClass
public class LongGroundTimeFilterTest implements Test {

    @Override
    public void test() {
        System.out.println("Тест: LongGroundTimeFilter");

        LocalDateTime now = LocalDateTime.now();
        List<Flight> flights = createTestFlights(now);
        LongGroundTimeFilter filter = new LongGroundTimeFilter();
        List<Flight> result = filter.filter(flights);

        boolean isValid = true;
        for (Flight flight : result) {
            long totalGroundTime = 0;
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                Segment currentSegment = segments.get(i);
                Segment nextSegment = segments.get(i + 1);
                totalGroundTime += Duration.between(currentSegment.getArrivalDate(), nextSegment.getDepartureDate()).toMinutes();
            }

            if (totalGroundTime > 120) {
                isValid = false;
                System.out.println("Тест не прошел: Время на земле в рейсе больше двух часов.");
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
                now.plusMinutes(10),
                now.plusMinutes(50)
        );
        Segment segment4 = new Segment(
                now.plusMinutes(200),
                now.plusMinutes(220)
        );
        Flight flight2 = new Flight(Arrays.asList(segment3, segment4));

        return Arrays.asList(flight1, flight2);
    }
}

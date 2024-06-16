package testing;

import com.gridnine.testing.exception.ArrivalTimeIsNotCorrectException;
import com.gridnine.testing.exception.FlightTimeIsNotCorrectException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Factory class to get sample list of flights.
 */
public class FlightBuilder {
    static DateTimeFormatter fmt =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
                //A normal flight with two hour duration
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
                //A normal multi segment flight
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
                //A flight departing in the past
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
                //A flight that departs before it arrives
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
                //A flight with more than two hours ground time
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
                //Another flight with more than two hours ground time
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }

    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            try {
                segments.add(new Segment(dates[i], dates[i + 1]));
            } catch (FlightTimeIsNotCorrectException e) {
                System.out.println("flight exception in new Segment [" + dates[i].format(fmt) + '|' + dates[i + 1].format(fmt)
                        + ']');
            } catch (ArrivalTimeIsNotCorrectException e) {
                System.out.println("arr exception in new Segment [" + dates[i].format(fmt) + '|' + dates[i + 1].format(fmt)
                        + ']');
            }
        }
        return new Flight(segments);
    }
}


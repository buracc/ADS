import model.PassengerWagon;
import model.Wagon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WagonTest {
    @Test
    public void passengerWagonShouldReportCorrectNumberOfSeats() {
        PassengerWagon wagon = new PassengerWagon(0, 13);

        assertEquals(13, wagon.getNumberOfSeats());
    }

    @Test
    public void firstOfThreeWagonsHasTwoAttachedWagons() {
        Wagon first = new PassengerWagon(1, 0);
        Wagon middle = new PassengerWagon(2, 0);
        Wagon last = new PassengerWagon(3, 0);

        first.setNextWagon(middle);
        middle.setNextWagon(last);

        assertEquals(2, first.getNumberOfWagonsAttached());
    }
}

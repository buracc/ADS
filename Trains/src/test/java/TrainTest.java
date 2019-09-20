import model.Locomotive;
import model.PassengerWagon;
import model.Train;
import model.Wagon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrainTest {

    @Test
    public void reportPassengerTrainCorrectly() {
        Train train = new Train(new Locomotive(0, 0), "Haarlem", "Amsterdam");
        Wagon wagon = new PassengerWagon(0, 0);
        train.setFirstWagon(wagon);

        assertTrue(train.isPassengerTrain());
    }

    @Test
    public void returnsCorrectWagons() {
        Train train = new Train(new Locomotive(0,3), "Haarlem", "Amsterdam");
        Wagon first = new PassengerWagon(1, 10);
        Wagon second = new PassengerWagon(2, 2);
        Wagon third = new PassengerWagon(3, 30);
        train.setFirstWagon(first);
        first.setNextWagon(second);
        second.setNextWagon(third);

        train.resetNumberOfWagons();

        assertEquals(3, train.getNumberOfWagons());
    }

}

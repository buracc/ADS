import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class TrainsTest {

    private ArrayList<PassengerWagon> pwList;
    private ArrayList<FreightWagon> fwList;
    private Train firstPassengerTrain;
    private Train secondPassengerTrain;
    private Train firstFreightTrain;
    private Train secondFreightTrain;
    private Wagon w1;
    private Wagon w2;
    private Wagon w3;
    private Wagon w4;

    @BeforeEach
    private void makeListOfPassengerWagons() {
        pwList = new ArrayList<>();
        pwList.add(new PassengerWagon(3, 100));
        pwList.add(new PassengerWagon(24, 100));
        pwList.add(new PassengerWagon(17, 140));
        pwList.add(new PassengerWagon(32, 150));
        pwList.add(new PassengerWagon(38, 140));
        pwList.add(new PassengerWagon(11, 100));
    }

    @BeforeEach
    private void makeListOfFreightWagons() {
        fwList = new ArrayList<>();
        fwList.add(new FreightWagon(2, 1000));
        fwList.add(new FreightWagon(4, 300));
        fwList.add(new FreightWagon(10, 440));
        fwList.add(new FreightWagon(3, 750));
        fwList.add(new FreightWagon(8, 410));
        fwList.add(new FreightWagon(11, 500));
    }

    private void makeTrains() {
        Locomotive thomas = new Locomotive(2453, 7);
        Locomotive gordon = new Locomotive(5277, 8);
        Locomotive emily = new Locomotive(4383, 11);
        Locomotive rebecca = new Locomotive(2275, 4);

        firstPassengerTrain = new Train(thomas, "Amsterdam", "Haarlem");
        for (Wagon w : pwList) {
            Shunter.hookWagonOnTrainRear(firstPassengerTrain, w);
        }
        secondPassengerTrain = new Train(gordon, "Joburg", "Cape Town");

        firstFreightTrain = new Train(emily,"Kyoto", "Tokyo");
        for (Wagon w : fwList){
            Shunter.hookWagonOnTrainRear(firstFreightTrain,w);
        }
        secondFreightTrain = new Train(rebecca,"Shanghai", "Seoul");
    }

    private void makeWagons(){
        w1 = new PassengerWagon(1,100);
        w2 = new PassengerWagon(1,100);
        w3 = new PassengerWagon(1,100);
        w4 = new PassengerWagon(1,100);
        w1.setNextWagon(w2);
        w2.setNextWagon(w3);
        w3.setNextWagon(w4);
    }

    /** Start **/

    @Test
    public void correctIdOfLast(){
        makeTrains();
        assertEquals(11,firstPassengerTrain.getFirstWagon().getLastWagonAttached().getWagonId());
    }
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

    @Test
    public void reportPassengerTrainCorrectly() {
        Train train = new Train(new Locomotive(0, 0), "Haarlem", "Amsterdam");
        Wagon wagon = new PassengerWagon(0, 0);
        train.setFirstWagon(wagon);

        assertTrue(train.isPassengerTrain());
    }

    @Test
    public void returnsCorrectWagons() {
        Train train = new Train(new Locomotive(0, 3), "Haarlem", "Amsterdam");
        Wagon first = new PassengerWagon(1, 10);
        Wagon second = new PassengerWagon(2, 2);
        Wagon third = new PassengerWagon(3, 30);
        train.setFirstWagon(first);
        first.setNextWagon(second);
        second.setNextWagon(third);

        train.resetNumberOfWagons();

        assertEquals(3, train.getNumberOfWagons());
    }

    @Test
    public void checkDetachOneWagon(){
        makeTrains();
        Shunter.detachOneWagon(firstPassengerTrain, pwList.get(2));
        assertEquals(5,firstPassengerTrain.getNumberOfWagons());
    }

    @Test
    public void checkWagonOnWagon(){
        makeWagons();
        Shunter.hookWagonOnWagon(w1,w3);
        assertTrue(w1.getNextWagon().equals(w3));
        assertFalse(w1.getNextWagon().equals(w2));
    }

    @Test
    public void checkLastWagonAttached(){
        makeTrains();
        assertEquals(pwList.get(5), firstPassengerTrain.getFirstWagon().getLastWagonAttached());
    }

    @Test
    public void checkWagonOnPosition(){
        makeTrains();
        assertEquals(pwList.get(3),firstPassengerTrain.getWagonOnPosition(4));
        Shunter.moveOneWagon(firstPassengerTrain,secondPassengerTrain,pwList.get(3));
        assertEquals(pwList.get(4),firstPassengerTrain.getWagonOnPosition(4));
    }

    @Test
    public void checkWagonsAttached(){
        makeWagons();
        assertEquals(3,w1.getNumberOfWagonsAttached());
        w1.setNextWagon(null);
        Shunter.hookWagonOnWagon(w1,pwList.get(5));
        assertEquals(1, w1.getNumberOfWagonsAttached());
    }

    @Test
    public void checkTotalMaxWeight(){
        makeTrains();
        assertEquals(3400, firstFreightTrain.getTotalMaxWeight());
        Shunter.moveOneWagon(firstFreightTrain, secondFreightTrain, fwList.get(2));
        assertEquals(440, secondFreightTrain.getTotalMaxWeight());
        assertEquals(2960, firstFreightTrain.getTotalMaxWeight());
    }

    @Test
    public void checkTrainAfterReset(){
        makeTrains();
        assertEquals(6, firstPassengerTrain.getNumberOfWagons());
        Shunter.detachOneWagon(firstPassengerTrain, pwList.get(2));
        Shunter.detachOneWagon(firstPassengerTrain, pwList.get(4));
        assertEquals(4, firstPassengerTrain.getNumberOfWagons());
    }

    /** End **/

    @Test
    public void checkNumberOfWagonsOnTrain() {
        makeTrains();
        assertEquals(6, firstPassengerTrain.getNumberOfWagons(), "Train should have 6 wagons");
        System.out.println(firstPassengerTrain);
    }

    @Test
    public void checkNumberOfSeatsOnTrain() {
        makeTrains();
        assertEquals( 730, firstPassengerTrain.getNumberOfSeats());
        Shunter.moveOneWagon(firstFreightTrain, secondPassengerTrain, pwList.get(5));
        assertEquals(100, secondPassengerTrain.getNumberOfSeats());
        assertEquals(630, firstPassengerTrain.getNumberOfSeats());
    }

    @Test
    public void checkPositionOfWagons() {
        makeTrains();
        int position = 1;
        for (PassengerWagon pw : pwList) {
            assertEquals( position, firstPassengerTrain.getPositionOfWagon(pw.getWagonId()));
            position++;
        }
    }

    @Test
    public void checkHookOneWagonOnTrainFront() {
        makeTrains();
        Shunter.hookWagonOnTrainFront(firstPassengerTrain, new PassengerWagon(21, 140));
        assertEquals( 7, firstPassengerTrain.getNumberOfWagons(), "Train should have 7 wagons");
        assertEquals( 1, firstPassengerTrain.getPositionOfWagon(21));

    }

    @Test
    public void checkHookRowWagonsOnTrainRearFalse() {
        makeTrains();
        Wagon w1 = new PassengerWagon(11, 100);
        Shunter.hookWagonOnWagon(w1, new PassengerWagon(43, 140));
        Shunter.hookWagonOnTrainRear(firstPassengerTrain, w1);
        assertEquals(6, firstPassengerTrain.getNumberOfWagons(), "Train should have still have 6 wagons, capacity reached");
        assertEquals( -1, firstPassengerTrain.getPositionOfWagon(43));
    }

    @Test
    public void checkMoveOneWagon() {
        makeTrains();
        Shunter.moveOneWagon(firstPassengerTrain, secondPassengerTrain, pwList.get(3));
        assertEquals(5, firstPassengerTrain.getNumberOfWagons(), "Train should have 5 wagons");
        assertEquals( -1, firstPassengerTrain.getPositionOfWagon(32));
        assertEquals(1, secondPassengerTrain.getNumberOfWagons(), "Train should have 1 wagon");
        assertEquals( 1, secondPassengerTrain.getPositionOfWagon(32));

    }

    @Test
    public void checkMoveRowOfWagons() {
        makeTrains();
        Wagon w1 = new PassengerWagon(11, 100);
        Shunter.hookWagonOnWagon(w1, new PassengerWagon(43, 140));
        Shunter.hookWagonOnTrainRear(secondPassengerTrain, w1);
        Shunter.moveAllFromTrain(firstPassengerTrain, secondPassengerTrain, pwList.get(2));
        assertEquals(2, firstPassengerTrain.getNumberOfWagons(), "Train should have 2 wagons");
        assertEquals( 2, firstPassengerTrain.getPositionOfWagon(24));
        assertEquals(6, secondPassengerTrain.getNumberOfWagons(), "Train should have 6 wagons");
        assertEquals( 4, secondPassengerTrain.getPositionOfWagon(32));
    }

}

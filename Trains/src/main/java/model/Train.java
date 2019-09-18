package model;

import java.util.Iterator;

public class Train implements Iterable<Wagon> {
    private Locomotive engine;
    private Wagon firstWagon;
    private String destination;
    private String origin;
    private int numberOfWagons;
    private TrainIterator iterator;


    public Train(Locomotive engine, String origin, String destination) {
        this.engine = engine;
        this.destination = destination;
        this.origin = origin;
    }

    public Wagon getFirstWagon() {
        return firstWagon;
    }

    public void setFirstWagon(Wagon firstWagon) {
        this.firstWagon = firstWagon;
    }

    public void resetNumberOfWagons() {
       /*  when wagons are hooked to or detached from a train,
         the number of wagons of the train should be reset
         this method does the calculation */
        if (hasNoWagons()) {
            numberOfWagons = 0;
            return;
        }

        numberOfWagons = 1;
        numberOfWagonsRecursive(getFirstWagon());
    }

    private Wagon numberOfWagonsRecursive(Wagon wagon) {
        if (!wagon.hasNextWagon()) {
            return wagon;
        }

        numberOfWagons++;
        return numberOfWagonsRecursive(wagon.getNextWagon());
    }

    public int getNumberOfWagons() {
        return numberOfWagons;
    }

    /* three helper methods that are usefull in other methods */

    public boolean hasNoWagons() {
        return (firstWagon == null);
    }

    public boolean isPassengerTrain() {
        return firstWagon instanceof PassengerWagon;
    }

    public boolean isFreightTrain() {
        return firstWagon instanceof FreightWagon;
    }

    public int getPositionOfWagon(int wagonId) {
        // find a wagon on a train by id, return the position (first wagon had position 1)
        // if not found, than return -1
        int counter = 1;
        return posOfWagonRecursive(firstWagon, wagonId, counter);
    }

    public int posOfWagonRecursive(Wagon wagon, int wagonId, int position) {
        if (wagon.getWagonId() == wagonId) {
            return position;
        }

        if (!wagon.hasNextWagon()) {
            return -1;
        }

        return posOfWagonRecursive(wagon.getNextWagon(), wagonId, ++position);
    }


    public Wagon getWagonOnPosition(int position) {
        /* find the wagon on a given position on the train
         position of wagons start at 1 (firstWagon of train)
         use exceptions to handle a position that does not exist */
        int counter = 1;
        return wagonOnPosRecursive(firstWagon, position, counter);
    }

    public Wagon wagonOnPosRecursive(Wagon wagon, int position, int counter) throws IndexOutOfBoundsException {
        try {
            if (counter == position) {
                return wagon;
            }

            return wagonOnPosRecursive(wagon.getNextWagon(), position, ++counter);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Something failed...");
            return null;
        }
    }

    public int getNumberOfSeats() {
        /* give the total number of seats on a passenger train
         for freight trains the result should be 0 */
        if (isFreightTrain()) {
            return 0;
        }


        int sum = 0;

        for (Wagon w : this) {
            PassengerWagon passengerWagon = (PassengerWagon) w;

            sum += passengerWagon.getNumberOfSeats();
        }

//        if (!w.hasNextWagon()) {
//            sum += w.getNumberOfSeats();
//        }
//
//        while (w.hasNextWagon()) {
//            sum += w.getNumberOfSeats();
//            w = (PassengerWagon) w.getNextWagon();
//            if (!w.hasNextWagon()) {
//                sum += w.getNumberOfSeats();
//            }
//        }

        return sum;
    }

    public int getTotalMaxWeight() {
        /* give the total maximum weight of a freight train
         for passenger trains the result should be 0 */
        if (isPassengerTrain()) {
            return 0;
        }

        int sum = 0;

        for (Wagon w : this) {
            FreightWagon freightWagon = (FreightWagon) w;

            sum += freightWagon.getMaxWeight();
        }

//        if (!freightWagon.hasNextWagon()) {
//            sum += freightWagon.getMaxWeight();
//        }
//
//        while (freightWagon.hasNextWagon()) {
//            sum += freightWagon.getMaxWeight();
//            freightWagon = (FreightWagon) freightWagon.getNextWagon();
//            if (!freightWagon.hasNextWagon()) {
//                sum += freightWagon.getMaxWeight();
//            }
//        }

        return sum;
    }

    public Locomotive getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(engine.toString());
        Wagon next = this.getFirstWagon();
        while (next != null) {
            result.append(next.toString());
            next = next.getNextWagon();
        }
        result.append(String.format(" with %d wagons and %d seats from %s to %s", numberOfWagons, getNumberOfSeats(), origin, destination));
        return result.toString();
    }

    @Override
    public Iterator<Wagon> iterator() {
        return this.iterator = new TrainIterator(firstWagon);
    }
}

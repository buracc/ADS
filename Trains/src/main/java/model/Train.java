package model;

import java.util.Iterator;

/**
 * @author Burak Inan
 * @author Safak Inan
 */

public class Train implements Iterable<Wagon> {
    private Locomotive engine;
    private Wagon firstWagon;
    private String destination;
    private String origin;
    private int numberOfWagons;

    /**
     * Constructs a Train with a Locomotive, origin and destination.
     * @param engine Locomotive with number and Wagon capacity.
     * @param origin origin location.
     * @param destination destination.
     */
    public Train(Locomotive engine, String origin, String destination) {
        this.engine = engine;
        this.destination = destination;
        this.origin = origin;
    }

    /**
     * Gets the first wagon on the Train.
     * @return first Wagon.
     */
    public Wagon getFirstWagon() {
        return firstWagon;
    }

    /**
     * Sets the first wagon on the Train.
     * @param firstWagon the Wagon to set as first.
     */
    public void setFirstWagon(Wagon firstWagon) {
        this.firstWagon = firstWagon;
    }

    /**
     * Re-calculates the amount of Wagons attached to this Train. Usually called after (de-)attachment of Wagons.
     */
    public void resetNumberOfWagons() {
        if (hasNoWagons()) {
            numberOfWagons = 0;
            return;
        }

        numberOfWagons = 1;
        numberOfWagonsRecursive(getFirstWagon());
    }

    /**
     * Recursive method which handles iteration through all existing Wagons.
     * @param wagon current Wagon.
     * @return the next Wagon.
     */
    private Wagon numberOfWagonsRecursive(Wagon wagon) {
        if (!wagon.hasNextWagon()) {
            return wagon;
        }

        numberOfWagons++;
        return numberOfWagonsRecursive(wagon.getNextWagon());
    }

    /**
     * Gets the amount of Wagons attached to this Train.
     * @return the amount of Wagons.
     */
    public int getNumberOfWagons() {
        return numberOfWagons;
    }

    /**
     * Checks if there are no Wagons attached to the Train.
     * @return true if there are no Wagons attached.
     */
    public boolean hasNoWagons() {
        return (firstWagon == null);
    }

    /**
     * Checks if Train has only PassengerWagons.
     * @return true if Train is a Passenger Train
     */
    public boolean isPassengerTrain() {
        return firstWagon instanceof PassengerWagon;
    }

    /**
     * Checks if Train has only FreightWagons.
     * @return true if Train is a Freight Train
     */
    public boolean isFreightTrain() {
        return firstWagon instanceof FreightWagon;
    }

    /**
     * Gets the position of the Wagon with given id.
     * @param wagonId ID number of the Wagon.
     * @return position of the Wagon, if found.
     */
    public int getPositionOfWagon(int wagonId) {
        int counter = 1;
        return posOfWagonRecursive(firstWagon, wagonId, counter);
    }

    /**
     * Recursive method which handles iterating through all Wagons, to find the position of given Wagon.
     * @param wagon current Wagon in the iteration.
     * @param wagonId ID number of Wagon to find.
     * @param position current position in the iteration.
     * @return position of the Wagon if found. If not found, returns -1.
     */
    public int posOfWagonRecursive(Wagon wagon, int wagonId, int position) {
        if (wagon.getWagonId() == wagonId) {
            return position;
        }

        if (!wagon.hasNextWagon()) {
            return -1;
        }

        return posOfWagonRecursive(wagon.getNextWagon(), wagonId, ++position);
    }

    /**
     * Finds the Wagon on a given position. Throws an exception if position does not exist.
     * @param position the position to look for.
     * @return Wagon found on the position.
     */
    public Wagon getWagonOnPosition(int position) {
        int counter = 1;
        return wagonOnPosRecursive(firstWagon, position, counter);
    }

    /**
     * Recursive method that handles iterating through all Wagons, to find the Wagon on given position.
     * @param wagon current Wagon.
     * @param position position to look for.
     * @param counter index of current position.
     * @return Wagon on found position.
     * @throws IndexOutOfBoundsException if position does not exist.
     */
    public Wagon wagonOnPosRecursive(Wagon wagon, int position, int counter) throws IndexOutOfBoundsException {
            if (counter == position) {
                return wagon;
            }

            if (!wagon.hasNextWagon()) {
                throw new IndexOutOfBoundsException("Position does not exist on train.");
            }

            return wagonOnPosRecursive(wagon.getNextWagon(), position, ++counter);
    }

    /**
     * Counts the total amount of seats of all Wagons combined on this Train.
     * @return the amount of seats.
     */
    public int getNumberOfSeats() {
        if (isFreightTrain()) {
            return 0;
        }


        int sum = 0;

        for (Wagon w : this) {
            PassengerWagon passengerWagon = (PassengerWagon) w;

            sum += passengerWagon.getNumberOfSeats();
        }

        return sum;
    }

    /**
     * Counts the total max weight of this Train.
     * @return the total max weight.
     */
    public int getTotalMaxWeight() {
        if (isPassengerTrain()) {
            return 0;
        }

        int sum = 0;

        for (Wagon w : this) {
            FreightWagon freightWagon = (FreightWagon) w;

            sum += freightWagon.getMaxWeight();
        }

        return sum;
    }

    /**
     * Returns the Locomotive.
     * @return the Locomotive.
     */
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

    /**
     * Returns a new instance of the TrainIterator class.
     * @return a new instance of the TrainIterator class.
     */
    @Override
    public Iterator<Wagon> iterator() {
        return new TrainIterator(firstWagon);
    }
}

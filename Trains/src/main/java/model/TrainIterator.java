package model;

import java.util.Iterator;

/**
 * @author Burak Inan
 * @author Safak Inan
 */

public class TrainIterator implements Iterator<Wagon> {

    private Wagon current;

    /**
     * Constructs a new Iterator for a Train.
     * @param current the current Wagon.
     */
    public TrainIterator(Wagon current) {
        this.current = current;
    }

    /**
     * Returns true if there are more Wagons available in the iteration.
     * @return true if there are more Wagons in this iteration.
     */
    @Override
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Returns the next Wagon in this iteration.
     * @return the next Wagon in this iteration.
     */
    @Override
    public Wagon next() {
        if (!hasNext()) {
            return current;
        }

        Wagon temp = current;
        current = temp.getNextWagon();
        return temp;
    }
}
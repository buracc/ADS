package model;

/**
 * @author Burak Inan
 * @author Safak Inan
 */

public abstract class Wagon {
    private int wagonId;
    private Wagon previousWagon;
    private Wagon nextWagon;

    /**
     * Constructs a Wagon with an ID.
     * @param wagonId ID number of the Wagon.
     */
    public Wagon(int wagonId) {
        this.wagonId = wagonId;
    }

    /**
     * Gets the last Wagon attached to this Wagon.
     * @return the last Wagon attached.
     */
    public Wagon getLastWagonAttached() {
        if (!this.hasNextWagon()) {
            return this;
        } else {
            return this.getNextWagon().getLastWagonAttached();
        }
    }

    /**
     * Links a new next Wagon to this Wagon.
     * @param nextWagon the Wagon to link this Wagon to.
     */
    public void setNextWagon(Wagon nextWagon) {
        if (nextWagon == null) {
            this.nextWagon = null;
        } else {
            this.nextWagon = nextWagon;
            this.nextWagon.setPreviousWagon(this);
        }
    }

    /**
     * Gets the link to the previous Wagon attached to this Wagon.
     * @return the previous Wagon.
     */
    public Wagon getPreviousWagon() {
        return previousWagon;
    }

    /**
     * Links a new next Wagon to this Wagon.
     * @param previousWagon the Wagon to link this Wagon to.
     */
    public void setPreviousWagon(Wagon previousWagon) {
        this.previousWagon = previousWagon;
    }

    /**
     * Gets the link to the next Wagon attached to this Wagon.
     * @return the next Wagon.
     */
    public Wagon getNextWagon() {
        return nextWagon;
    }

    /**
     * Gets the ID number of the Wagon.
     * @return the Wagon ID.
     */
    public int getWagonId() {
        return wagonId;
    }

    /**
     * Recursively counts the number of Wagons attached to this Wagon.
     * @return the number of Wagons attached.
     */
    public int getNumberOfWagonsAttached() {
        int counter = 0;
        return numberAttachedRecursive(counter);
    }

    /**
     * Recursively iterates through all Wagons and counts them.
     * @param counter the current count of Wagons.
     * @return the current amount of Wagons.
     */
    public int numberAttachedRecursive(int counter) {
        if (!this.hasNextWagon()) {
            return counter;
        }

        return this.getNextWagon().numberAttachedRecursive(++counter);
    }

    /**
     * Checks if there's a link to a next Wagon on this Wagon.
     * @return true if a next Wagon is attached.
     */
    public boolean hasNextWagon() {
        return !(nextWagon == null);
    }

    /**
     * Checks if there's a link to a previous Wagon on this Wagon.
     * @return true if a previous Wagon is attached.
     */
    public boolean hasPreviousWagon() {
        return !(previousWagon == null);
    }

    @Override
    public String toString() {
        return String.format("[Wagon %d]", wagonId);
    }
}

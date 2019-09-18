package model;

public abstract class Wagon {
    private int wagonId;
    private Wagon previousWagon;
    private Wagon nextWagon;

    public Wagon(int wagonId) {
        this.wagonId = wagonId;
    }

    public Wagon getLastWagonAttached() {
        // find the last wagon of the row of wagons attached to this wagon
        // if no wagons are attached return this wagon
        Wagon w = this;
        if (!w.hasNextWagon()) {
            return w;
        } else {
            return w.getNextWagon().getLastWagonAttached();
        }
    }

    public void setNextWagon(Wagon nextWagon) {
        // when setting the next wagon, set this wagon to be previous wagon of next wagon
        if (nextWagon == null) {
            this.nextWagon = null;
        } else {
            this.nextWagon = nextWagon;
            this.nextWagon.setPreviousWagon(this);
        }
    }

    public Wagon getPreviousWagon() {
        return previousWagon;
    }

    public void setPreviousWagon(Wagon previousWagon) {
        this.previousWagon = previousWagon;
    }

    public Wagon getNextWagon() {
        return nextWagon;
    }

    public int getWagonId() {
        return wagonId;
    }

    public int getNumberOfWagonsAttached() {
        int counter = 0;
        return numberAttachedRecursive(counter);
    }

    public int numberAttachedRecursive(int counter) {
        if (!this.hasNextWagon()) {
            return counter;
        }

        return this.getNextWagon().numberAttachedRecursive(++counter);
    }

    public boolean hasNextWagon() {
        return !(nextWagon == null);
    }

    public boolean hasPreviousWagon() {
        return !(previousWagon == null);
    }

    @Override
    public String toString() {
        return String.format("[Wagon %d]", wagonId);
    }
}

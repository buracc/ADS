package model;

import java.util.Iterator;

public class TrainIterator implements Iterator<Wagon> {

    private Wagon first;
    private Wagon current;

    public TrainIterator(Wagon first) {
        this.first = first;
        current = this.first;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

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
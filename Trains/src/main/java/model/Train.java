package model;

import java.util.Iterator;

public class Train implements Iterable<Wagon> {
    private Locomotive engine;
    private Wagon firstWagon;
    private String destination;
    private String origin;
    private int numberOfWagons;


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
        return new TrainIterator(firstWagon);
    }
}

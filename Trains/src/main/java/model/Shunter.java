package model;

public class Shunter {

    private static boolean isSuitableWagon(Train train, Wagon wagon) {
        if (train.hasNoWagons()) {
            return true;
        }

        return isSuitableWagon(train.getFirstWagon(), wagon);
    }

    private static boolean isSuitableWagon(Wagon one, Wagon two) {
        return (one instanceof PassengerWagon && two instanceof PassengerWagon)
                || (one instanceof FreightWagon && two instanceof FreightWagon);
    }

    private static boolean hasPlaceForWagons(Train train, Wagon wagon) {
        int number = train.getEngine().getMaxWagons() - train.getNumberOfWagons();
        return number > wagon.getNumberOfWagonsAttached();
    }

    private static boolean hasPlaceForOneWagon(Train train, Wagon wagon) {
        return hasPlaceForWagons(train, wagon);
    }

    public static boolean hookWagonOnTrainRear(Train train, Wagon wagon) {
        if (train.hasNoWagons()) {
            train.setFirstWagon(wagon);
            train.resetNumberOfWagons();
            return true;
        }

        if (isSuitableWagon(train, wagon)
                && hasPlaceForWagons(train, wagon)) {
            train.getFirstWagon().getLastWagonAttached().setNextWagon(wagon);
            train.resetNumberOfWagons();
            return true;
        }

        return false;
    }

    public static boolean hookWagonOnTrainFront(Train train, Wagon wagon) {
        if (train.hasNoWagons()) {
            train.setFirstWagon(wagon);
            train.resetNumberOfWagons();
            return true;
        }

        if (hasPlaceForWagons(train, wagon)
                && isSuitableWagon(train, wagon)) {
            Wagon oldFirst = train.getFirstWagon();
            train.setFirstWagon(wagon);
            if (hookWagonOnWagon(wagon, oldFirst)) {
                train.resetNumberOfWagons();
                return true;
            }
        }

        return false;
    }

    public static boolean hookWagonOnWagon(Wagon first, Wagon second) {
        if (isSuitableWagon(first, second)) {
            first.setNextWagon(second);
            return true;
        }

        return false;
    }


    public static boolean detachAllFromTrain(Train train, Wagon wagon) {
        if (wagonExistsOnTrain(train, wagon)) {
            if (wagon.equals(train.getFirstWagon())) {
                train.setFirstWagon(null);
                return true;
            }

            Wagon previous = wagon.getPreviousWagon();
            previous.setNextWagon(null);
            wagon.setPreviousWagon(null);
            train.resetNumberOfWagons();
            return true;
        }

        return false;
    }

    public static boolean detachOneWagon(Train train, Wagon wagon) {
        if (wagonExistsOnTrain(train, wagon)) {
            if (wagon.equals(train.getFirstWagon())) {
                train.setFirstWagon(wagon.getNextWagon());
                wagon.setNextWagon(null);
                train.resetNumberOfWagons();
                return true;
            }

            Wagon previous = wagon.getPreviousWagon();
            Wagon next = wagon.getNextWagon();
            if (next == null) {
                previous.setNextWagon(null);
                wagon.setPreviousWagon(null);
                train.resetNumberOfWagons();
                return true;
            }

            if (hookWagonOnWagon(previous, next)) {
                wagon.setPreviousWagon(null);
                wagon.setNextWagon(null);
                train.resetNumberOfWagons();
                return true;
            }
        }

        return false;
    }

    public static boolean moveAllFromTrain(Train from, Train to, Wagon wagon) {
        if (wagonExistsOnTrain(from, wagon)
                && isSuitableWagon(to, wagon)
                && hasPlaceForWagons(to, wagon)
                && detachAllFromTrain(from, wagon)
                && hookWagonOnTrainRear(to, wagon)) {
            from.resetNumberOfWagons();
            to.resetNumberOfWagons();
            return true;
        }

        return false;
    }

    public static boolean moveOneWagon(Train from, Train to, Wagon wagon) {
        if (wagonExistsOnTrain(from, wagon)
                && isSuitableWagon(to, wagon)
                && hasPlaceForOneWagon(to, wagon)
                && detachOneWagon(from, wagon)
                && hookWagonOnTrainRear(to, wagon)) {
            from.resetNumberOfWagons();
            to.resetNumberOfWagons();
            return true;
        }

        return false;
    }

    /*
    Extra helper method
     */

    private static boolean wagonExistsOnTrain(Train train, Wagon wagon) {
        return train.getWagonOnPosition(train.getPositionOfWagon(wagon.getWagonId())) != null;
    }
}

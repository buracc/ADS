package model;

/**
 * @author Burak Inan
 * @author Safak Inan
 */

public class Shunter {

    /**
     * Checks if target Wagon is a suitable Wagon for target Train.
     *
     * @param train target Train.
     * @param wagon target Wagon.
     * @return boolean value based on whether the Train can hold the Wagon.
     */
    private static boolean isSuitableWagon(Train train, Wagon wagon) {
        if (train.hasNoWagons()) {
            return true;
        }

        return isSuitableWagon(train.getFirstWagon(), wagon);
    }

    /**
     * Checks if two Wagons are compatible with each other.
     *
     * @param one the first Wagon.
     * @param two the second Wagon.
     * @return boolean value based on whether both Wagons are compatible with each other.
     */
    private static boolean isSuitableWagon(Wagon one, Wagon two) {
        return (one instanceof PassengerWagon && two instanceof PassengerWagon)
                || (one instanceof FreightWagon && two instanceof FreightWagon);
    }

    /**
     * Checks if Train has enough space for more Wagons.
     * @param train target Train.
     * @param wagon given Wagon.
     * @return boolean value based on whether there's space on the Train or not.
     */
    private static boolean hasPlaceForWagons(Train train, Wagon wagon) {
        int number = train.getEngine().getMaxWagons() - train.getNumberOfWagons();
        return number > wagon.getNumberOfWagonsAttached();
    }

    /**
     * Checks the Train for space for one more Wagon.
     * @param train target Train.
     * @param wagon given Wagon.
     * @return boolean value based on if there's one more empty space on the Train.
     */
    private static boolean hasPlaceForOneWagon(Train train, Wagon wagon) {
        return hasPlaceForWagons(train, wagon);
    }

    /**
     * Hooks a Wagon on the rear of a Train.
     * @param train target Train.
     * @param wagon given Wagon.
     * @return boolean value based on if the action was successfully completed.
     */
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

    /**
     * Hooks a Wagon at the front of a Train.
     * @param train target Train.
     * @param wagon given Wagon.
     * @return boolean value based on if the action was successfully completed.
     */
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

    /**
     * Hooks a Wagon onto another Wagon.
     * @param first first Wagon.
     * @param second second Wagon.
     * @return boolean value based on if the action was successfully completed.
     */
    public static boolean hookWagonOnWagon(Wagon first, Wagon second) {
        if (isSuitableWagon(first, second)) {
            first.setNextWagon(second);
            return true;
        }

        return false;
    }

    /**
     * Detaches a Wagon and all Wagons hooked onto this Wagon from a Train.
     * @param train target Train.
     * @param wagon Wagon to detach.
     * @return boolean value based on if the action was successfully completed.
     */
    public static boolean detachAllFromTrain(Train train, Wagon wagon) {
        if (wagonExistsOnTrain(train, wagon)) {
            if (wagon.equals(train.getFirstWagon())) {
                train.setFirstWagon(null);
                train.resetNumberOfWagons();
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

    /**
     * Detaches one Wagon from a Train, not including all Wagons that are attached to this Wagon.
     * @param train target Train.
     * @param wagon Wagon to detach.
     * @return boolean value based on if the action was successfully completed.
     */
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

    /**
     * Moves a Wagon and all Wagons attached to this Train, to another Train.
     * @param from Train from which the Wagons will be detached off.
     * @param to Train which will get the new Wagons.
     * @param wagon Wagon to move.
     * @return boolean value based on if the action was successfully completed.
     */
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

    /**
     * Moves one Wagon excluding all Wagons that are detached to this Wagon from this Train, to another Train.
     * @param from Train from which the Wagon will be detached off.
     * @param to Train which will get the new Wagon.
     * @param wagon Wagon to detach.
     * @return boolean value based on if the action was successfully completed.
     */
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

    /**
     * Checks if given Wagon exists on a target Train.
     * @param train target Train.
     * @param wagon target Wagon.
     * @return boolean value based on whether the Wagon exists on the Train.
     */
    private static boolean wagonExistsOnTrain(Train train, Wagon wagon) {
        return train.getWagonOnPosition(train.getPositionOfWagon(wagon.getWagonId())) != null;
    }
}

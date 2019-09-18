package model;

public class Shunter {


    /* four helper methods than are used in other methods in this class to do checks */
    private static boolean isSuitableWagon(Train train, Wagon wagon) {
        // trains can only exist of passenger wagons or of freight wagons
        if (train.hasNoWagons()) {
            return true;
        }

        return isSuitableWagon(train.getFirstWagon(), wagon);
    }

    private static boolean isSuitableWagon(Wagon one, Wagon two) {
        // passenger wagons can only be hooked onto passenger wagons
        return (one instanceof PassengerWagon && two instanceof PassengerWagon) ||
                (one instanceof FreightWagon && two instanceof FreightWagon);
    }

    private static boolean hasPlaceForWagons(Train train, Wagon wagon) {
        // the engine of a train has a maximum capacity, this method checks for a row of wagons
        int number = train.getEngine().getMaxWagons() - train.getNumberOfWagons();
        return number > wagon.getNumberOfWagonsAttached();
    }

    private static boolean hasPlaceForOneWagon(Train train, Wagon wagon) {
        // the engine of a train has a maximum capacity, this method checks for one wagon
        wagon.setNextWagon(null);
        wagon.setPreviousWagon(null);
        int number = train.getEngine().getMaxWagons() - train.getNumberOfWagons();
        if (wagon.getNumberOfWagonsAttached() == 0) {
            return number > 1;
        }

        return false;
    }

    public static boolean hookWagonOnTrainRear(Train train, Wagon wagon) {
         /* check if Locomotive can pull new number of Wagons
         check if wagon is correct kind of wagon for train
         find the last wagon of the train
         hook the wagon on the last wagon (see Wagon class)
         adjust number of Wagons of Train */
        if (train.hasNoWagons()) {
            train.setFirstWagon(wagon);
            train.resetNumberOfWagons();
            return true;
        }

        if (isSuitableWagon(train, wagon) && hasPlaceForWagons(train, wagon)) {
            train.getFirstWagon().getLastWagonAttached().setNextWagon(wagon);
            train.resetNumberOfWagons();
            return true;
        }

        return false;
    }

    public static boolean hookWagonOnTrainFront(Train train, Wagon wagon) {
        /* check if Locomotive can pull new number of Wagons
         check if wagon is correct kind of wagon for train
         if Train has no wagons hookOn to Locomotive
         if Train has wagons hookOn to Locomotive and hook firstWagon of Train to lastWagon attached to the wagon
         adjust number of Wagons of Train */
        if (hasPlaceForWagons(train, wagon) && isSuitableWagon(train, wagon)) {
            if (train.hasNoWagons()) {
                train.setFirstWagon(wagon);
                train.resetNumberOfWagons();
                return true;
            }

            Wagon w = train.getFirstWagon();
            wagon.setNextWagon(w);
            train.setFirstWagon(wagon);
            train.resetNumberOfWagons();
            return true;
        }

        return false;
    }

    public static boolean hookWagonOnWagon(Wagon first, Wagon second) {
        /* check if wagons are of the same kind (suitable)
         * if so make second wagon next wagon of first */
        if (isSuitableWagon(first, second)) {
            first.setNextWagon(second);
            return true;
        }

        return false;
    }


    public static boolean detachAllFromTrain(Train train, Wagon wagon) {
        /* check if wagon is on the train
         detach the wagon from its previousWagon with all its successor
         recalculate the number of wagons of the train */
        if (train.getWagonOnPosition(train.getPositionOfWagon(wagon.getWagonId())) != null) {
            if (train.getFirstWagon() == wagon) {
                train.setFirstWagon(null);
                return true;
            }

            Wagon w = wagon.getPreviousWagon();
            w.setNextWagon(null);
            return true;
        }

        return false;
    }

    public static boolean detachOneWagon(Train train, Wagon wagon) {
        /* check if wagon is on the train
         detach the wagon from its previousWagon and hook the nextWagon to the previousWagon
         so, in fact remove the one wagon from the train
        */
        Wagon next;
        Wagon previous;
        if (train.getWagonOnPosition(train.getPositionOfWagon(wagon.getWagonId())) != null) {
            previous = wagon.getPreviousWagon();
            next = wagon.getNextWagon();
            previous.setNextWagon(next);
            train.resetNumberOfWagons();
            return true;
        }

        return false;
    }

    public static boolean moveAllFromTrain(Train from, Train to, Wagon wagon) {
        /* check if wagon is on train from
         check if wagon is correct for train and if engine can handle new wagons
         detach Wagon and all successors from train from and hook at the rear of train to
         remember to adjust number of wagons of trains */

        if (from.getWagonOnPosition(from.getPositionOfWagon(wagon.getWagonId())) != null) {
            detachAllFromTrain(from, wagon);
            from.resetNumberOfWagons();
            if ((isSuitableWagon(to, wagon) || to.hasNoWagons()) && hasPlaceForWagons(to, wagon)) {
                hookWagonOnTrainRear(to, wagon);
                to.resetNumberOfWagons();
                return true;
            }
        }

        return false;
    }

    public static boolean moveOneWagon(Train from, Train to, Wagon wagon) {
        // detach only one wagon from train from and hook on rear of train to
        // do necessary checks and adjustments to trains and wagon
        detachOneWagon(from, wagon);
        from.resetNumberOfWagons();
        if ((to.hasNoWagons() || isSuitableWagon(to, wagon)) && hasPlaceForOneWagon(to, wagon)) {
            hookWagonOnTrainRear(to, wagon);
            to.resetNumberOfWagons();
            return true;
        }

        return false;
    }
}

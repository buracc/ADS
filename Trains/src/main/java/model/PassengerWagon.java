package model;

/**
 * @author Burak Inan
 * @author Safak Inan
 */

public class PassengerWagon extends Wagon{

    private int numberOfSeats;

    /**
     * Constructs a PassengerWagon with an ID and number of seats.
     * @param wagonId ID number of the Wagon.
     * @param numberOfSeats Number of seats inside the Wagon.
     */
    public PassengerWagon(int wagonId, int numberOfSeats) {
        super(wagonId);
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}

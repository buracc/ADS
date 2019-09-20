package model;

/**
 * @author Burak Inan
 * @author Safak Inan
 */

public class FreightWagon extends Wagon{

    private int maxWeight;

    /**
     * Constructs a FreightWagon with an ID and Maximum weight.
     *
     * @param wagonId ID number of the Wagon.
     * @param maxWeight maximum weight of the Wagon.
     */
    public FreightWagon(int wagonId, int maxWeight) {
        super(wagonId);
        this.maxWeight = maxWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }
}

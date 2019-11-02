
import java.time.LocalTime;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityCashier extends FIFOCashier {
    private int maxNumPriorityItems;

    /**
     * Constructor for Cashier with PriorityQueue data structure.
     * Given in the waiting queue is the Comparator, which the queue uses to sort the Customers with a higher priority
     * at the beginning of the queue
     * @param name name of the Cashier
     * @param maxNumPriorityItems the number of items a Customer "gains" priority on
     */
    public PriorityCashier(String name, int maxNumPriorityItems) {
        super(name);
        this.maxNumPriorityItems = maxNumPriorityItems;
        waitingQueue = new PriorityQueue<>(Customer::compareTo);
    }

    @Override
    public void reStart(LocalTime currentTime) {
        super.reStart(currentTime);
    }

    @Override
    public void doTheWorkUntil(LocalTime targetTime) {
        super.doTheWorkUntil(targetTime);
    }

    @Override
    public int expectedCheckOutTime(int numberOfItems) {
        return super.expectedCheckOutTime(numberOfItems);
    }

    /**
     * Calculates the expected waiting time for the given Customer.
     * Checks if the given Customer has priority in the queue
     * @param customer the given Customer to calculate the expected waiting time for
     * @return the total waiting time of the given Customer
     */
    @Override
    public int expectedWaitingTime(Customer customer) {

        int totalWaitingTime = 0;
        for (Customer c : waitingQueue) {
            if (customer.getNumberOfItems() <= 5){
                if (c.getNumberOfItems() > 5){
                    break;
                }
            }
            totalWaitingTime += expectedCheckOutTime(c.getNumberOfItems());
        }

        totalWaitingTime += getCurrentWaitingTime();
        customer.setActualWaitingTime(totalWaitingTime);
        return totalWaitingTime;
    }

    @Override
    public void add(Customer customer) {
        super.add(customer);
    }
}

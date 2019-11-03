
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
        super.waitingQueue = new PriorityQueue<>((c1, c2) -> {
            if (c1.getNumberOfItems() <= maxNumPriorityItems && c2.getNumberOfItems() <= maxNumPriorityItems) {
                return c1.compareTo(c2);
            }

            if (c1.getNumberOfItems() > maxNumPriorityItems && c2.getNumberOfItems() > maxNumPriorityItems) {
                return c1.compareTo(c2);
            }

            if (c1.getNumberOfItems() <= maxNumPriorityItems && c2.getNumberOfItems() > maxNumPriorityItems) {
                return -1;
            }

            if (c1.getNumberOfItems() > maxNumPriorityItems && c2.getNumberOfItems() <= maxNumPriorityItems) {
                return 1;
            }

            return 0;
        });
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
        int totalWaitingTime = getCurrentWaitingTime();

        for (Customer c : super.waitingQueue) {
            if (c == customer){
                break;
            }
            if (customer.getNumberOfItems() <= maxNumPriorityItems){
                if (c.getNumberOfItems() > maxNumPriorityItems){
                    break;
                }
            }


            totalWaitingTime += expectedCheckOutTime(c.getNumberOfItems());
            //Waiting time must be redone on customers with lower priority
            
        }

        return totalWaitingTime;
    }

    @Override
    public void add(Customer customer) {
        super.add(customer);
    }
}

import java.time.Duration;
import java.time.LocalTime;
import java.util.LinkedList;

public class FIFOCashier extends Cashier {
    private static final int CUSTOMER_CHECKOUT_TIME = 20;
    private static final int ITEM_CHECKOUT_TIME = 2;

    private Customer currentCustomer;
    private int currentWaitingTime;

    /**
     * Constructor for Cashier with FIFO data structure, consisting of a LinkedList
     *
     * @param name the name of this Cashier
     */
    public FIFOCashier(String name) {
        super(name);
        super.waitingQueue = new LinkedList<>();
    }

    /**
     * Restart the state of the simulation of the cashier,
     * resets the current Customer and current time of current Customer as well
     *
     * @param currentTime the current time which will be set to
     */
    @Override
    public void reStart(LocalTime currentTime) {
        super.reStart(currentTime);
        this.currentCustomer = null;
        this.currentWaitingTime = 0;
    }

    /**
     * Adds a given Customer to the queue of the Cashier,
     * checks if Cashier is busy with a current Customer and adds 1 to max queue length.
     * <p>
     * If the given Customer has no items, skip him over and do not add him to the queue
     *
     * @param customer the given Customer
     */
    @Override
    public void add(Customer customer) {
        super.add(customer);
        customer.setActualCheckOutTime(expectedCheckOutTime(customer.getNumberOfItems()));

        if (waitingQueue.size() >= maxQueueLength) {
            if (currentCustomer != null) {
                maxQueueLength = waitingQueue.size() + 1;
                return;
            }

            maxQueueLength = waitingQueue.size();
        }
    }

    /**
     * Calculates the expected checkout time of a Customer,
     * depending on the number of items the given Customer has
     *
     * @param numberOfItems number of items of the Customer
     * @return the expected checkout time in seconds
     */
    @Override
    public int expectedCheckOutTime(int numberOfItems) {
        if (numberOfItems != 0) {
            return CUSTOMER_CHECKOUT_TIME + (numberOfItems * ITEM_CHECKOUT_TIME);
        }

        return 0;
    }

    /**
     * Calculates the expected waiting time of a Customer,
     * depending on the expected waiting time for every Customer in the waiting queue
     * and the current Customer being helped by the Cashier
     *
     * @param customer the given Customer to calculate the expected waiting time for
     * @return the expected waiting time in seconds
     */
    @Override
    public int expectedWaitingTime(Customer customer) {
        int totalWaitingTime = getCurrentWaitingTime();
        for (Customer c : waitingQueue) {
            if (c == customer){
                break;
            }

            totalWaitingTime += expectedCheckOutTime(c.getNumberOfItems());
        }

        return totalWaitingTime;
    }

    /**
     * Does the work of the Cashier and calculates the time that passed, until the given target time.
     * If there are no Customers left, the Cashier is then idle and waits until his time is up
     *
     * @param targetTime the time this Cashier works until
     */
    @Override
    public void doTheWorkUntil(LocalTime targetTime) {
        while (!currentTime.equals(targetTime)) {
            if (currentCustomer == null && waitingQueue.size() == 0) {
                setTotalIdleTime(++totalIdleTime);
                setCurrentTime(currentTime.plusSeconds(1));
                continue;
            }

            if (currentCustomer != null) {
                --currentWaitingTime;
                setCurrentTime(currentTime.plusSeconds(1));

                if (currentWaitingTime == 0) {
                    currentCustomer = null;
                    finishedCustomer();
                }

                continue;
            }

            currentCustomer = waitingQueue.poll();
            currentCustomer.setActualWaitingTime((int) Duration.between(currentCustomer.getQueuedAt(), currentTime).toSeconds());
            //Current customer ALWAYS has a 0 waiting time
            currentWaitingTime = expectedCheckOutTime(currentCustomer.getNumberOfItems());
        }
    }

    public int getCurrentWaitingTime() {
        return currentWaitingTime;
    }
}

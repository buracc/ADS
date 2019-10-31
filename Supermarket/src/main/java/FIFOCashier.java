import java.time.LocalTime;
import java.util.LinkedList;

public class FIFOCashier extends Cashier {
    public static final int CUSTOMER_CHECKOUT_TIME = 20;
    public static final int ITEM_CHECKOUT_TIME = 2;

    private Customer currentCustomer;
    private int currentWaitingTime;

    public FIFOCashier(String name) {
        super(name);
        super.waitingQueue = new LinkedList<>();
    }

    @Override
    public void reStart(LocalTime currentTime) {
        super.reStart(currentTime);
        this.currentCustomer = null;
        this.currentWaitingTime = 0;
    }

    @Override
    public void add(Customer customer) {
        if (customer.getItems().size() == 0) {
            return;
        }

        waitingQueue.add(customer);
        customer.setCheckOutCashier(this);

        if (waitingQueue.size() >= maxQueueLength) {
            if (currentCustomer != null) {
                maxQueueLength = waitingQueue.size() + 1;
                return;
            }

            maxQueueLength = waitingQueue.size();
        }
    }

    @Override
    public int expectedCheckOutTime(int numberOfItems) {
        if (numberOfItems != 0) {
            return CUSTOMER_CHECKOUT_TIME + (numberOfItems * ITEM_CHECKOUT_TIME);
        }

        return 0;
    }

    @Override
    public int expectedWaitingTime(Customer customer) {
        int totalWaitingTime = 0;
        for (Customer c : waitingQueue) {
            totalWaitingTime += expectedCheckOutTime(c.getNumberOfItems()) + c.getActualCheckOutTime();
        }

        totalWaitingTime += currentWaitingTime;
        customer.setActualWaitingTime(totalWaitingTime);
        return totalWaitingTime;
    }

    @Override
    public void doTheWorkUntil(LocalTime targetTime) {
        while (!currentTime.equals(targetTime)) {
            if (currentCustomer == null && waitingQueue.size() == 0) {
                setTotalIdleTime(++totalIdleTime);
                setCurrentTime(currentTime.plusSeconds(1));
                continue;
            }

            if (currentCustomer != null) {
                currentCustomer.setActualCheckOutTime(--currentWaitingTime);
                setCurrentTime(currentTime.plusSeconds(1));
                continue;
            }

            currentCustomer = waitingQueue.remove();
            currentWaitingTime = expectedCheckOutTime(currentCustomer.getNumberOfItems());
        }
    }
}

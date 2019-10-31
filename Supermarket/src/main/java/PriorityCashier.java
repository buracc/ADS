
import java.time.LocalTime;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityCashier extends FIFOCashier {
    private int maxNumPriorityItems;
    private int currentWaitingTime;

    public PriorityCashier(String name, int maxNumPriorityItems) {
        super(name);
        this.maxNumPriorityItems = maxNumPriorityItems;
        super.waitingQueue = new PriorityQueue<>((c1, c2) -> {
            if (c1.getItems().size() <= this.maxNumPriorityItems && c2.getItems().size() > this.maxNumPriorityItems) {
                return 1;
            }

            if (c1.getItems().size() > this.maxNumPriorityItems && c2.getItems().size() <= this.maxNumPriorityItems) {
                return -1;
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

    @Override
    public int expectedWaitingTime(Customer customer) {
        if (waitingQueue.isEmpty()) {
            return 0;
        }

        Queue<Customer> tempQueue = new PriorityQueue<>(waitingQueue);
        tempQueue.add(customer);
        System.out.println(tempQueue);

        int totalWaitingTime = 0;
        for (Customer c : tempQueue) {
            totalWaitingTime += expectedCheckOutTime(c.getNumberOfItems()) + c.getActualCheckOutTime();
        }

        totalWaitingTime += currentWaitingTime;
        customer.setActualWaitingTime(totalWaitingTime);
        return totalWaitingTime;
    }

    @Override
    public void add(Customer customer) {
        super.add(customer);
    }
}

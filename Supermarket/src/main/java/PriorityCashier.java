
import java.time.LocalTime;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityCashier extends FIFOCashier {
    private int maxNumPriorityItems;

    public PriorityCashier(String name, int maxNumPriorityItems) {
        super(name);
        this.maxNumPriorityItems = maxNumPriorityItems;
        waitingQueue = new PriorityQueue<>((c1, c2) -> {
            if (c1.getNumberOfItems() <= this.maxNumPriorityItems && c2.getNumberOfItems() > this.maxNumPriorityItems) {
                return -1;
            }

            if (c1.getNumberOfItems() > this.maxNumPriorityItems && c2.getNumberOfItems() <= this.maxNumPriorityItems) {
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
        System.out.println(waitingQueue);
        return totalWaitingTime;
    }

    @Override
    public void add(Customer customer) {
        super.add(customer);
    }
}

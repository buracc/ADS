import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class FIFOCashier extends Cashier {
    private int checkoutTimePerCustomer;
    private int checkoutTimePerItem;

    private Customer current;
    private int currentWaitingTime = 0;

    public FIFOCashier(String name) {
        super(name);
        super.waitingQueue = new LinkedList<>();
        current = waitingQueue.peek();
    }

    @Override
    public int expectedCheckOutTime(int numberOfItems) {
        if (numberOfItems != 0){
            return 20 + (numberOfItems*2);
        }
        return 0;
    }

    @Override
    public int expectedWaitingTime(Customer customer) {
        int benis = 0;
        for (Customer c: waitingQueue) {
            benis += expectedCheckOutTime(c.getNumberOfItems()) + c.getActualCheckOutTime();
        }
        benis += currentWaitingTime;
        customer.setActualWaitingTime(benis);
        return benis;
    }

    @Override
    public void doTheWorkUntil(LocalTime targetTime) {
        current = waitingQueue.peek();
        int checkoutTimeCurrent = expectedCheckOutTime(current.getNumberOfItems());
        waitingQueue.remove();
        /*while(!super.currentTime.equals(targetTime)) {
            for (Customer c : super.waitingQueue) {
                super.setCurrentTime(super.getCurrentTime().plusSeconds(c.getActualCheckOutTime()));
                super.waitingQueue.remove();
                if (super.currentTime.equals(targetTime)){
                    break;
                }
            }
            super.setTotalIdleTime(++totalIdleTime);
            super.setCurrentTime(super.getCurrentTime().plusSeconds(1));
        }*/

        while (!currentTime.equals(targetTime)){
            if (checkoutTimeCurrent != 0){
                setCurrentTime(currentTime.plusSeconds(1));
                current.setActualCheckOutTime(--checkoutTimeCurrent);
                currentWaitingTime = checkoutTimeCurrent;
            }else{
                setTotalIdleTime(++totalIdleTime);
                setCurrentTime(currentTime.plusSeconds(1));
            }

        }

    }
}

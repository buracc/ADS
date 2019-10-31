import java.time.LocalTime;
import java.util.ArrayDeque;

public class FIFOCashier extends Cashier {

    protected FIFOCashier(String name) {
        super(name);
        super.waitingQueue = new ArrayDeque<>();
    }

    @Override
    public int expectedCheckOutTime(int numberOfItems) {
        return 0;
    }

    @Override
    public int expectedWaitingTime(Customer customer) {
        return 0;
    }

    @Override
    public void doTheWorkUntil(LocalTime targetTime) {

    }
}

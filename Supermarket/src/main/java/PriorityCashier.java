import java.util.PriorityQueue;

public class PriorityCashier extends FIFOCashier{

    protected PriorityCashier(String name) {
        super(name);
        super.waitingQueue = new PriorityQueue<>();
    }
}

import java.util.PriorityQueue;

public class PriorityCashier extends FIFOCashier{

    public PriorityCashier(String name) {
        super(name);
        super.waitingQueue = new PriorityQueue<>();
    }
}

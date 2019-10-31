
import java.util.PriorityQueue;

public class PriorityCashier extends FIFOCashier{
    private int maxNumPriorityItems;

    public PriorityCashier(String name, int maxNumPriorityItems) {
        super(name);
        super.waitingQueue = new PriorityQueue<>();
        this.maxNumPriorityItems = maxNumPriorityItems;
    }



}

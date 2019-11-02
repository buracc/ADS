import java.time.LocalTime;
import java.util.Iterator;

public class SupermarketMain {
    public static void main(String[] args) {

         Product prod1 = new Product("A001", "Any-1", 1.0);

        Customer customer1 = new Customer(1, LocalTime.NOON, "1213");
        Customer customer2 = new Customer(2, LocalTime.NOON, "1111");
        Customer customer3 = new Customer(3, LocalTime.NOON.plusSeconds(8), "dded");
        Customer customer4 = new Customer(4, LocalTime.NOON.plusSeconds(5), "dded");
        Customer customer5 = new Customer(5, LocalTime.NOON.plusSeconds(1), "dded");
        Customer customer6 = new Customer(6, LocalTime.NOON, "dded");
        Customer customer7 = new Customer(7, LocalTime.NOON, "dded");

         customer1.getItems().add(new Purchase(prod1, 5));
         customer2.getItems().add(new Purchase(prod1, 1));
         customer3.getItems().add(new Purchase(prod1, 7));
         customer4.getItems().add(new Purchase(prod1, 6));
         customer5.getItems().add(new Purchase(prod1, 4));
         customer6.getItems().add(new Purchase(prod1, 2));
         customer7.getItems().add(new Purchase(prod1, 3));

        Cashier c = new PriorityCashier("prio",5);
        print(customer1, c);
        print(customer2, c);
        print(customer3, c);
        print(customer4, c);
        print(customer5, c);
        print(customer6, c);
        print(customer7, c);

        while (!c.waitingQueue.isEmpty()) {
            System.out.println(c.waitingQueue.poll().getId());
        }

//        // load the simulation configuration with open and closing times
//        // and products and customers
//        Supermarket supermarket =
//                Supermarket.importFromXML("jambi250_8.xml");
//        supermarket.printCustomerStatistics();
//
//        // configure the cashiers for the base scenario
//        supermarket.getCashiers().clear();
//        //  (uncomment these lines once the FIFOCashier class has been implemented)
//        supermarket.getCashiers().add(new FIFOCashier("FIFO"));
//
//        // simulate the configuration and print the result
//        supermarket.simulateCashiers();
//        supermarket.printSimulationResults();
//
//        // configure the cashiers for the priority scenario
//        supermarket.getCashiers().clear();
//        //  (uncomment these lines once the PriorityCashier class has been implemented)
//        supermarket.getCashiers().add(new PriorityCashier("PRIO",5));
//
//        // simulate the configuration and print the result
//        supermarket.simulateCashiers();
//        supermarket.printSimulationResults();
//
//        // configure the cashiers for the self-service scenario
//        supermarket.getCashiers().clear();
//        //  (uncomment these lines once the FIFO- and PriorityCashier class have been implemented)
//        supermarket.getCashiers().add(new FIFOCashier("FIFO"));
//        supermarket.getCashiers().add(new FIFOCashier("PRIO"));
//
//        // simulate the configuration and print the result
//        supermarket.simulateCashiers();
//        supermarket.printSimulationResults();
    }

    private static void print(Customer customer7, Cashier c) {
        c.add(customer7);
        Iterator<Customer> it = c.waitingQueue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            System.out.println(it.next().getId() + " items: " + it.next().getNumberOfItems() + " queued at: " + it.next().getQueuedAt());
        }
        System.out.println("----------");
    }
}

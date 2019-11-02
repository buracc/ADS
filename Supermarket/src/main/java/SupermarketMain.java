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
        Customer customer7 = new Customer(7, LocalTime.NOON.plusSeconds(90), "dded");

        customer1.getItems().add(new Purchase(prod1, 5));
        customer2.getItems().add(new Purchase(prod1, 1));
        customer3.getItems().add(new Purchase(prod1, 7));
        customer4.getItems().add(new Purchase(prod1, 6));
        customer5.getItems().add(new Purchase(prod1, 4));
        customer6.getItems().add(new Purchase(prod1, 2));
        customer7.getItems().add(new Purchase(prod1, 3));

        Cashier c = new PriorityCashier("prio", 5);
        c.add(customer1);
        while (!c.waitingQueue.isEmpty()) {
            Customer bog = c.waitingQueue.poll();
            System.out.println(bog.getId() + " items: " + bog.getNumberOfItems() + " queued at: " + bog.getQueuedAt());
        }
        System.out.println("----------------------");

        c.add(customer1);
        c.add(customer2);
        while (!c.waitingQueue.isEmpty()) {
            Customer bog = c.waitingQueue.poll();
            System.out.println(bog.getId() + " items: " + bog.getNumberOfItems() + " queued at: " + bog.getQueuedAt());
        }
        System.out.println("----------------------");

        c.add(customer1);
        c.add(customer2);
        c.add(customer3);
        while (!c.waitingQueue.isEmpty()) {
            Customer bog = c.waitingQueue.poll();
            System.out.println(bog.getId() + " items: " + bog.getNumberOfItems() + " queued at: " + bog.getQueuedAt());
        }
        System.out.println("----------------------");

        c.add(customer1);
        c.add(customer2);
        c.add(customer3);
        c.add(customer4);
        while (!c.waitingQueue.isEmpty()) {
            Customer bog = c.waitingQueue.poll();
            System.out.println(bog.getId() + " items: " + bog.getNumberOfItems() + " queued at: " + bog.getQueuedAt());
        }
        System.out.println("----------------------");

        c.add(customer1);
        c.add(customer2);
        c.add(customer3);
        c.add(customer4);
        c.add(customer5);
        while (!c.waitingQueue.isEmpty()) {
            Customer bog = c.waitingQueue.poll();
            System.out.println(bog.getId() + " items: " + bog.getNumberOfItems() + " queued at: " + bog.getQueuedAt());
        }
        System.out.println("----------------------");

        c.add(customer1);
        c.add(customer2);
        c.add(customer3);
        c.add(customer4);
        c.add(customer5);
        c.add(customer6);
        while (!c.waitingQueue.isEmpty()) {
            Customer bog = c.waitingQueue.poll();
            System.out.println(bog.getId() + " items: " + bog.getNumberOfItems() + " queued at: " + bog.getQueuedAt());
        }
        System.out.println("----------------------");

        c.add(customer1);
        c.add(customer2);
        c.add(customer3);
        c.add(customer4);
        c.add(customer5);
        c.add(customer6);
        c.add(customer7);
        while (!c.waitingQueue.isEmpty()) {
            Customer bog = c.waitingQueue.poll();
            System.out.println(bog.getId() + " items: " + bog.getNumberOfItems() + " queued at: " + bog.getQueuedAt());
        }
        System.out.println("----------------------");


//        while (!c.waitingQueue.isEmpty()) {
//            System.out.println(c.waitingQueue.poll().getId());
//        }

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
}

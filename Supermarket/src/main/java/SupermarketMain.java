import java.time.LocalTime;

public class SupermarketMain {
    public static void main(String[] args) {

         Product prod1 = new Product("A001", "Any-1", 1.0);
         Product prod2 = new Product("A002", "Any-2", 2.0);
         Product prod3 = new Product("A003", "Any-3", 3.0);
         // 1 (5),
        // 5 (4),
        // 6 (2),
        // 7 (3),
        // 4 (6),
        // 3 (7),
        // 2 (1)

         Customer customer1 = new Customer(LocalTime.NOON.plusSeconds(10), "1213");
         Customer customer2 = new Customer(LocalTime.NOON.plusSeconds(30), "1111");
         Customer customer3 = new Customer(LocalTime.NOON.plusSeconds(20), "dded");
         Customer customer4 = new Customer(LocalTime.NOON.plusSeconds(20), "dded");
         Customer customer5 = new Customer(LocalTime.NOON.plusSeconds(20), "dded");
         Customer customer6 = new Customer(LocalTime.NOON.plusSeconds(20), "dded");
         Customer customer7 = new Customer(LocalTime.NOON.plusSeconds(20), "dded");

//        Customer customer1 = new Customer(LocalTime.NOON, "1213");
//        Customer customer2 = new Customer(LocalTime.NOON, "1111");
//        Customer customer3 = new Customer(LocalTime.NOON, "dded");
//        Customer customer4 = new Customer(LocalTime.NOON, "dded");
//        Customer customer5 = new Customer(LocalTime.NOON, "dded");
//        Customer customer6 = new Customer(LocalTime.NOON, "dded");
//        Customer customer7 = new Customer(LocalTime.NOON, "dded");

         customer1.getItems().add(new Purchase(prod1, 5));
         customer2.getItems().add(new Purchase(prod1, 1));
         customer3.getItems().add(new Purchase(prod1, 7));
         customer4.getItems().add(new Purchase(prod1, 6));
         customer5.getItems().add(new Purchase(prod1, 4));
         customer6.getItems().add(new Purchase(prod1, 2));
         customer7.getItems().add(new Purchase(prod1, 3));

        Cashier c = new PriorityCashier("benis",5);
        c.add(customer1);
        c.add(customer2);
        c.add(customer3);
        c.add(customer4);
        c.add(customer5);
        c.add(customer6);
        c.add(customer7);

        while (!c.waitingQueue.isEmpty()) {
            System.out.println(c.waitingQueue.poll().getNumberOfItems());
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
}

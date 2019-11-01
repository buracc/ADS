import java.time.LocalTime;

public class SupermarketMain {
    public static void main(String[] args) {

         Product prod1 = new Product("A001", "Any-1", 1.0);
         Product prod2 = new Product("A002", "Any-2", 2.0);
         Product prod3 = new Product("A003", "Any-3", 3.0);

         Customer customer1 = new Customer(LocalTime.NOON.plusSeconds(10), "1213");
         Customer customer2 = new Customer(LocalTime.NOON.plusSeconds(30), "1111");
         Customer customer3 = new Customer(LocalTime.NOON.plusSeconds(20), "dded");

         customer1.getItems().add(new Purchase(prod1, 5));
         customer2.getItems().add(new Purchase(prod1, 1));
         customer3.getItems().add(new Purchase(prod1, 7));

        Cashier c = new PriorityCashier("benis",5);
        c.add(customer3);
        c.add(customer2);
        c.add(customer1);

        while (!c.waitingQueue.isEmpty()) {
            System.out.println(c.waitingQueue.poll().getNumberOfItems());
        }
        /*// load the simulation configuration with open and closing times
        // and products and customers
        Supermarket supermarket =
                Supermarket.importFromXML("jambi250_8.xml");
        supermarket.printCustomerStatistics();

        // configure the cashiers for the base scenario
        supermarket.getCashiers().clear();
        // TODO Add a FIFO cashiers
        //  (uncomment these lines once the FIFOCashier class has been implemented)
        supermarket.getCashiers().add(new FIFOCashier("FIFO"));

        // simulate the configuration and print the result
        supermarket.simulateCashiers();
        supermarket.printSimulationResults();

        // configure the cashiers for the priority scenario
        supermarket.getCashiers().clear();
        // TODO Add a PRIO cashier respecting 5 priority items
        //  (uncomment these lines once the PriorityCashier class has been implemented)
        supermarket.getCashiers().add(new PriorityCashier("PRIO",5));

        // simulate the configuration and print the result
        supermarket.simulateCashiers();
        supermarket.printSimulationResults();

        // configure the cashiers for the self-service scenario
        supermarket.getCashiers().clear();
        // TODO Add 1 FIFO cashiers and 1 PRIO cashier
        //  (uncomment these lines once the FIFO- and PriorityCashier class have been implemented)
        supermarket.getCashiers().add(new FIFOCashier("FIFO"));
        supermarket.getCashiers().add(new FIFOCashier("PRIO"));

        // simulate the configuration and print the result
        supermarket.simulateCashiers();
        supermarket.printSimulationResults();*/
    }
}

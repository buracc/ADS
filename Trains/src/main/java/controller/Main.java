package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //passenger train
        Train passenger = new Train(new Locomotive(1337, 10), "Weed", "Cocaine");

        List<Wagon> wagonList = new ArrayList<>();
        wagonList.add(new PassengerWagon(5, 100));
        wagonList.add(new PassengerWagon(24, 100));
        wagonList.add(new PassengerWagon(17, 140));
        wagonList.add(new PassengerWagon(32, 150));
        wagonList.add(new PassengerWagon(38, 140));

        for (Wagon w : wagonList) {
            Shunter.hookWagonOnTrainRear(passenger, w);
        }

        System.out.println("------------------");
        System.out.println(passenger);

        Train passenger2 = new Train(new Locomotive(420, 10), "Weed", "Cocaine");

        List<Wagon> wagonList2 = new ArrayList<>();
        wagonList2.add(new PassengerWagon(3, 100));
        wagonList2.add(new PassengerWagon(24, 100));
        wagonList2.add(new PassengerWagon(17, 140));
        wagonList2.add(new PassengerWagon(32, 150));
        wagonList2.add(new PassengerWagon(38, 140));

        for (Wagon w : wagonList2) {
            Shunter.hookWagonOnTrainFront(passenger2, w);
        }

        System.out.println(passenger2);

        System.out.println("------------------");

        Shunter.detachOneWagon(passenger2, wagonList2.get(0));
        System.out.println(passenger2);

        System.out.println("------------------");

        Shunter.moveOneWagon(passenger, passenger2, wagonList.get(1));
        System.out.println(passenger);
        System.out.println(passenger2);

        System.out.println("------------------");

        passenger.getWagonOnPosition(10);

    }
}

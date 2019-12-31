package controller;

import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstPath;
import model.TransportGraph;

public class TransportGraphLauncher {

    /**
     * TODO:
     * Assignment A:
     * 5. Tabel met alle connections
     *
     */

    public static void main(String[] args) {
        // Assignment A
//        String[] redLine = {"red", "metro", "A", "B", "C", "D"};
//        String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
//        String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
//        String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

        // Assignment B
        String[] redLine = {"red", "metro", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
        String[] blueLine = {"blue", "metro", "Trojelaan", "Coltrane Cirkel", "Meridiaan", "Robijnpark"};
        String[] purpleLine = {"purple", "metro", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
        String[] greenLine = {"green", "metro", "Ymeerdijk", "Trojelaan", "Steigerplein", "Swingstraat", "Bachgracht", "Nobelplein"};
        String[] yellowLine = {"yellow", "bus", "Grote sluis", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote sluis"};

        TransportGraph transportGraph = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(purpleLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();

        //Uncomment to test the builder:
        System.out.println(transportGraph.getNumberOfStations());
        System.out.println(transportGraph.getNumberEdges());
        System.out.println(transportGraph);

        //Uncommented to test the DepthFirstPath algorithm
//        DepthFirstPath dfpTest = new DepthFirstPath(transportGraph, "Haven", "Centrum");
//        dfpTest.search();
//        System.out.println(dfpTest);
//        dfpTest.printNodesInVisitedOrder();
//        System.out.println();

        //Uncommented to test the BreadthFirstPath algorithm
        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraph, "Haven", "Centrum");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();

    }
}

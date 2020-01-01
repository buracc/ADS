package controller;

import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstPath;
import graphalgorithms.DijkstraShortestPath;
import model.Connection;
import model.Station;
import model.TransportGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransportGraphLauncher {

    /**
     * TODO:
     * Assignment A:
     * 5. Tabel met alle connections
     *
     */

    public static void main(String[] args) throws Exception {
        // Assignment A

        String[] redLineA = {"red", "metro", "A", "B", "C", "D"};
        String[] blueLineA = {"blue", "metro", "E", "B", "F", "G"};
        String[] greenLineA = {"green", "metro", "H", "I", "C", "G", "J"};
        String[] yellowLineA = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

        List<String[]> linesA = new ArrayList<>();
        linesA.add(redLineA);
        linesA.add(blueLineA);
        linesA.add(greenLineA);
        linesA.add(yellowLineA);

        TransportGraph transportGraphA = new TransportGraph.Builder()
                .addLine(redLineA)
                .addLine(blueLineA)
                .addLine(greenLineA)
                .addLine(yellowLineA)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();

        //Uncomment to test the builder:
        System.out.println(transportGraphA.getNumberOfStations());
        System.out.println(transportGraphA.getNumberEdges());
        System.out.println(transportGraphA);

        System.out.println("## DFS ##");
        DepthFirstPath dfpTest = new DepthFirstPath(transportGraphA, "E", "J");
        dfpTest.search();
        System.out.println(dfpTest);
        dfpTest.printNodesInVisitedOrder();
        System.out.println();

        for (String[] lines : linesA) {
            for (int i = 2; i < lines.length; i++) {
                String from = lines[i];

                for (int j = 2; j < lines.length; j++) {
                    String to = lines[j];
                    if (to.equals(from)) {
                        continue;
                    }

                    DepthFirstPath dfs = new DepthFirstPath(transportGraphA, from, to);
                    dfs.search();
                    System.out.println(dfs);
                    dfs.printNodesInVisitedOrder();
                    System.out.println();
                }
            }
        }

        System.out.println("## BFS ##");
        BreadthFirstPath bfsTest = new BreadthFirstPath(transportGraphA, "E", "J");
        bfsTest.search();
        System.out.println(bfsTest);
        bfsTest.printNodesInVisitedOrder();
        System.out.println();

        for (String[] lines : linesA) {
            for (int i = 2; i < lines.length; i++) {
                String from = lines[i];

                for (int j = 2; j < lines.length; j++) {
                    String to = lines[j];
                    if (to.equals(from)) {
                        continue;
                    }

                    BreadthFirstPath bfs = new BreadthFirstPath(transportGraphA, from, to);
                    bfs.search();
                    System.out.println(bfs);
                    bfs.printNodesInVisitedOrder();
                    System.out.println();
                }
            }
        }

        // Assignment B
        String[] redLineB = {"red", "metro", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
        String[] blueLineB = {"blue", "metro", "Trojelaan", "Coltrane Cirkel", "Meridiaan", "Robijnpark", "Violetplantsoen"};
        String[] purpleLineB = {"purple", "metro", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
        String[] greenLineB = {"green", "metro", "Ymeerdijk", "Trojelaan", "Steigerplein", "Swingstraat", "Bachgracht", "Nobelplein"};
        String[] yellowLineB = {"yellow", "bus", "Grote sluis", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote sluis"};

        double[] redWeights = {4.5, 4.7, 6.1, 3.5, 5.4, 5.6};
        double[] blueWeights = {6.0, 5.3, 5.1, 3.3};
        double[] purpleWeights = {6.2, 5.2, 3.8, 3.6};
        double[] greenWeights = {5.0, 3.7, 6.9, 3.9, 3.4};
        double[] yellowWeights = {26, 19, 37, 25, 22, 28};

        TransportGraph transportGraphB = new TransportGraph.Builder()
                .addLine(redLineB)
                .addLine(blueLineB)
                .addLine(purpleLineB)
                .addLine(greenLineB)
                .addLine(yellowLineB)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .addWeight(redLineB, redWeights)
                .addWeight(blueLineB, blueWeights)
                .addWeight(purpleLineB, purpleWeights)
                .addWeight(greenLineB, greenWeights)
                .addWeight(yellowLineB, yellowWeights)
                .build();

        System.out.println("## DFS ##");
        DepthFirstPath dfpTestB = new DepthFirstPath(transportGraphB, "Ymeerdijk", "Violetplantsoen");
        dfpTestB.search();
        System.out.println(dfpTestB);
        dfpTestB.printNodesInVisitedOrder();
        System.out.println();

        System.out.println("## BFS ##");
        BreadthFirstPath bfsTestB = new BreadthFirstPath(transportGraphB, "Ymeerdijk", "Violetplantsoen");
        bfsTestB.search();
        System.out.println(bfsTestB);
        bfsTestB.printNodesInVisitedOrder();
        System.out.println();

        //Dijkstra
        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(transportGraphB, "Violetplantsoen", "Oostvaarders");
        dijkstraShortestPath.search();
        System.out.println(dijkstraShortestPath);
        dijkstraShortestPath.printNodesInVisitedOrder();
        System.out.println(dijkstraShortestPath.getTotalWeight());
    }
}

package test;

import graphalgorithms.A_Star;
import graphalgorithms.DijkstraShortestPath;
import model.TransportGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class A_StarTest {

    private A_Star ass;
    private TransportGraph transportGraph;

    private String[] redLine = {"red", "metro", "Haven", "Marken", "Steigerplein", "Centrum", "Meridiaan", "Dukdalf", "Oostvaarders"};
    private String[] blueLine = {"blue", "metro", "Trojelaan", "Coltrane Cirkel", "Meridiaan", "Robijnpark", "Violetplantsoen"};
    private String[] purpleLine = {"purple", "metro", "Grote Sluis", "Grootzeil", "Coltrane Cirkel", "Centrum", "Swingstraat"};
    private String[] greenLine = {"green", "metro", "Ymeerdijk", "Trojelaan", "Steigerplein", "Swingstraat", "Bachgracht", "Nobelplein"};
    private String[] yellowLine = {"yellow", "bus", "Grote sluis", "Ymeerdijk", "Haven", "Nobelplein", "Violetplantsoen", "Oostvaarders", "Grote sluis"};

    private double[] redWeights = {4.5, 4.7, 6.1, 3.5, 5.4, 5.6};
    private double[] blueWeights = {6.0, 5.3, 5.1, 3.3};
    private double[] purpleWeights = {6.2, 5.2, 3.8, 3.6};
    private double[] greenWeights = {5.0, 3.7, 6.9, 3.9, 3.4};
    private double[] yellowWeights = {26, 19, 37, 25, 22, 28};

    private int[][] redCoordinates = {
            {14, 1}, {12, 3}, {10, 5}, {8, 8}, {6, 9}, {3, 10}, {0, 11}
    };
    private int[][] blueCoordinates = {
            {9, 3}, {7, 6}, {6, 9}, {6, 12}, {5, 14}
    };
    private int[][] purpleCoordinates = {
            {2, 3}, {4, 6}, {7, 6}, {8, 8}, {10, 9}
    };
    private int[][] greenCoordinates = {
            {9, 0}, {9, 3}, {10, 5}, {10, 9}, {11, 11}, {12, 13}
    };
    private int[][] yellowCoordinates = {
            {2, 3}, {9, 0}, {14, 1}, {12, 13}, {5, 14}, {0, 11}, {2, 3}
    };

    @Before
    public void setUp() throws Exception {
        transportGraph = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(purpleLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .addWeight(redLine, redWeights)
                .addWeight(blueLine, blueWeights)
                .addWeight(purpleLine, purpleWeights)
                .addWeight(greenLine, greenWeights)
                .addWeight(yellowLine, yellowWeights)
                .addLocation(redLine, redCoordinates)
                .addLocation(blueLine, blueCoordinates)
                .addLocation(purpleLine, purpleCoordinates)
                .addLocation(greenLine, greenCoordinates)
                .addLocation(yellowLine, yellowCoordinates)
                .build();

    }

    @Test
    public void testWithTransfer() {
        ass = new A_Star(transportGraph, "Haven", "Swingstraat");
        ass.search();
        String path = "[Haven (14, 1), Marken (12, 3), Steigerplein (10, 5), Swingstraat (10, 9)]";
        System.out.println(ass.toString());
        assertTrue(ass.toString().contains(path));
        assertEquals(ass.getTransfers(), 1);
    }

    @Test
    public void testWithoutTransfer() {
        ass = new A_Star(transportGraph, "Haven", "Marken");
        ass.search();
        assertFalse(ass.getNodesInPath().size() > 2);
        String path = "[Haven (14, 1), Marken (12, 3)]";
        assertEquals(path, ass.getNodesInPath().toString());
    }

    @Test
    public void testNodesVisited() {
        ass = new A_Star(transportGraph, "Haven" , "Haven");
        ass.search();
        assertEquals(1, ass.getNodesInPath().size());
        assertEquals(0, ass.getTransfers());
    }

    @Test
    public void testTotalWeight(){
        ass = new A_Star(transportGraph, "Haven" , "Ymeerdijk");
        ass.search();
        double totalWeight = 4.5+4.7+3.7+5.0;
        assertEquals(totalWeight, ass.getTotalWeight(), 0.0001);
    }
}
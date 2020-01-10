package test;

import graphalgorithms.DijkstraShortestPath;
import model.TransportGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DijkstraShortestPathTest {

    private DijkstraShortestPath dss;
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
                .build();

    }

    @Test
    public void testWithTransfer() {
        dss = new DijkstraShortestPath(transportGraph, "Haven", "Swingstraat");
        dss.search();
        String path = "[Haven, Marken, Steigerplein, Swingstraat]";
        assertTrue(dss.toString().contains(path));
        assertEquals(dss.getTransfers(), 1);
    }

    @Test
    public void testWithoutTransfer() {
        dss = new DijkstraShortestPath(transportGraph, "Haven", "Marken");
        dss.search();
        assertFalse(dss.getNodesInPath().size() > 2);
        String path = "[Haven, Marken]";
        assertEquals(path, dss.getNodesInPath().toString());
    }

    @Test
    public void testNodesVisited() {
        dss = new DijkstraShortestPath(transportGraph, "Haven" , "Haven");
        dss.search();
        assertEquals(1, dss.getNodesInPath().size());
        assertEquals(0, dss.getTransfers());
    }

    @Test
    public void testTotalWeight(){
        dss = new DijkstraShortestPath(transportGraph, "Haven" , "Ymeerdijk");
        dss.search();
        double totalWeight = 4.5+4.7+3.7+5.0;
        assertEquals(totalWeight, dss.getTotalWeight(), 0.0001);
    }
}
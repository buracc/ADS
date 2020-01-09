package test;

import graphalgorithms.BreadthFirstPath;
import graphalgorithms.DepthFirstPath;
import model.TransportGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepthFirstPathTest {

    private DepthFirstPath dfs;
    private TransportGraph transportGraph;
    private String[] redLine = {"red", "metro", "A", "B", "C", "D"};
    private String[] blueLine = {"blue", "metro", "E", "B", "F", "G"};
    private String[] greenLine = {"green", "metro", "H", "I", "C", "G", "J"};
    private String[] yellowLine = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

    @Before
    public void setUp() {
        transportGraph = new TransportGraph.Builder()
                .addLine(redLine)
                .addLine(blueLine)
                .addLine(greenLine)
                .addLine(yellowLine)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();
    }

    @Test
    public void testWithTransfer() {
        dfs = new DepthFirstPath(transportGraph, "A", "J");
        dfs.search();
        String path = "[A, B, C, D, G, J]";
        assertTrue(dfs.toString().contains(path));
        assertEquals(dfs.getTransfers(), 2);
    }

    @Test
    public void testWithoutTransfer() {
        dfs = new DepthFirstPath(transportGraph, "A", "B");
        dfs.search();
        assertFalse(dfs.getNodesInPath().size() > 2);
        String path = "[A, B]";
        assertEquals(path, dfs.getNodesInPath().toString());
        assertEquals(path, dfs.getNodesVisited().toString());
    }

    @Test
    public void testNodesVisited() {
        dfs = new DepthFirstPath(transportGraph, "A" , "A");
        dfs.search();
        assertEquals(1, dfs.getNodesInPath().size());
        assertEquals(0, dfs.getTransfers());
    }
}
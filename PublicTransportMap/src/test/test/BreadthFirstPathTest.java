package test;

import graphalgorithms.BreadthFirstPath;
import model.TransportGraph;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BreadthFirstPathTest {

    private BreadthFirstPath bfs;
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
        bfs = new BreadthFirstPath(transportGraph, "A", "J");
        bfs.search();
        String path = "[A, G, J]";
        assertTrue(bfs.toString().contains(path));
        assertEquals(bfs.getTransfers(), 1);
    }

    @Test
    public void testWithoutTransfer() {
        bfs = new BreadthFirstPath(transportGraph, "A", "B");
        bfs.search();
        assertFalse(bfs.getNodesInPath().size() > 2);
        String path = "[A, B]";
        assertEquals(path, bfs.getNodesInPath().toString());
        assertEquals(path, bfs.getNodesVisited().toString());
    }

    @Test
    public void testNodesVisited() {
        bfs = new BreadthFirstPath(transportGraph, "A" , "A");
        bfs.search();
        assertEquals(1, bfs.getNodesInPath().size());
        assertEquals(0, bfs.getTransfers());
    }
}
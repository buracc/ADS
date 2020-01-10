package test;

import model.TransportGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class TransportGraphTest {

    private String[] redLineA = {"red", "metro", "A", "B", "C", "D"};
    private String[] blueLineA = {"blue", "metro", "E", "B", "F", "G"};
    private String[] greenLineA = {"green", "metro", "H", "I", "C", "G", "J"};
    private String[] yellowLineA = {"yellow", "bus", "A", "E", "H", "D", "G", "A"};

    private List<String[]> linesA;
    private TransportGraph transportGraph;

    @Before
    public void setUp() throws Exception {
        transportGraph = new TransportGraph.Builder()
                .addLine(redLineA)
                .addLine(blueLineA)
                .addLine(greenLineA)
                .addLine(yellowLineA)
                .buildStationSet()
                .addLinesToStations()
                .buildConnections()
                .build();
        linesA = new ArrayList<>();
        linesA.add(redLineA);
        linesA.add(blueLineA);
        linesA.add(greenLineA);
        linesA.add(yellowLineA);
    }

    @Test
    public void checkBuild(){
        linesA.forEach(l ->{
            for (int i = 2; i < l.length; i++){
                assertEquals(l[i], transportGraph.getStation(l[i]).getStationName());
            }
        });
    }

    @Test
    public void checkAmount(){
        int stations = 10;
        int edges = 15*2;
        assertEquals(stations, transportGraph.getNumberOfStations());
        assertEquals(edges, transportGraph.getNumberEdges());
    }
}

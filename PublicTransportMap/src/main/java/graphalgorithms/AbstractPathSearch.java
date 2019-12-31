package graphalgorithms;

import model.Line;
import model.Station;
import model.TransportGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Abstract class that contains methods and attributes shared by the DepthFirstPath en BreadthFirstPath classes
 */
public abstract class AbstractPathSearch {

    protected boolean[] marked;
    protected int[] edgeTo;
    protected int transfers = 0;
    protected List<Station> nodesVisited;
    protected List<Station> nodesInPath;
    protected LinkedList<Integer> verticesInPath;
    protected TransportGraph graph;
    protected final int startIndex;
    protected final int endIndex;


    public AbstractPathSearch(TransportGraph graph, String start, String end) {
        startIndex = graph.getIndexOfStationByName(start);
        endIndex = graph.getIndexOfStationByName(end);
        this.graph = graph;
        nodesVisited = new ArrayList<>();
        marked = new boolean[graph.getNumberOfStations()];
        edgeTo = new int[graph.getNumberOfStations()];
        nodesInPath = new ArrayList<>();
        verticesInPath = new LinkedList<>();

    }

    public abstract void search();

    /**
     * @param vertex Determines whether a path exists to the station as an index from the start station
     * @return
     */
    public boolean hasPathTo(int vertex) {
        return marked[vertex];
    }


    /**
     * Method to build the path to the vertex, index of a Station.
     * First the LinkedList verticesInPath, containing the indexes of the stations, should be build, used as a stack
     * Then the list nodesInPath containing the actual stations is build.
     * Also the number of transfers is counted.
     *
     * @param vertex The station (vertex) as an index
     */
    public void pathTo(int vertex) {
        if (!hasPathTo(vertex)) {
            return;
        }

        Stack<Station> path = new Stack<>();
        for (int x = vertex; x != startIndex; x = edgeTo[x]) {
            Station current = graph.getStation(x);
            path.push(current);
        }

        path.push(graph.getStation(startIndex));

        Line commonLine = null;
        Station prev = null;

        while (!path.isEmpty()) {
            Station station = path.pop();
            nodesInPath.add(station);
            verticesInPath.add(graph.getIndexOfStationByName(station.getStationName()));

            if (prev != null) {
                if (commonLine == null) {
                    commonLine = station.getCommonLine(prev);
                }

                if (!commonLine.getStationsOnLine().contains(station)) {
                    countTransfers();
                    commonLine = prev.getCommonLine(station);
                }
            }

            prev = station;
        }
    }

    /**
     * Method to count the number of transfers in a path of vertices.
     * Uses the line information of the connections between stations.
     * If to consecutive connections are on different lines there was a transfer.
     */
    public void countTransfers() {
        transfers++;
    }


    /**
     * Method to print all the nodes that are visited by the search algorithm implemented in one of the subclasses.
     */
    public void printNodesInVisitedOrder() {
        System.out.print("Nodes in visited order: ");
        for (Station vertex : nodesVisited) {
            System.out.print(vertex.getStationName() + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder(String.format("Path from %s to %s: ", graph.getStation(startIndex), graph.getStation(endIndex)));
        resultString.append(nodesInPath).append(" with " + transfers).append(" transfers");
        return resultString.toString();
    }

}

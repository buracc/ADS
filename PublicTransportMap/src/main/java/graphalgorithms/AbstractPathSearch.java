package graphalgorithms;

import model.Connection;
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

    private double totalWeight;

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

    public int getTransfers() {
        return transfers;
    }

    public List<Station> getNodesVisited() {
        return nodesVisited;
    }

    public List<Station> getNodesInPath() {
        return nodesInPath;
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

        Line commonLine = null;
        Stack<Station> path = new Stack<>();
        for (int x = vertex; x != startIndex; x = edgeTo[x]) {
            Station current = graph.getStation(x);
            Station next = graph.getStation(edgeTo[x]);

            path.push(current);

            Connection connection = graph.getConnection(x, edgeTo[x]);
            if (connection != null) {
                totalWeight += connection.getWeight();
            }

            if (commonLine == null) {
                commonLine = current.getCommonLine(next);
                continue;
            }

            if (!commonLine.getStationsOnLine().contains(next)) {
                countTransfers();
                commonLine = current.getCommonLine(next);
            }
        }

        path.push(graph.getStation(startIndex));

        while (!path.isEmpty()) {
            Station current = path.pop();
            nodesInPath.add(current);
            verticesInPath.add(graph.getIndexOfStationByName(current.getStationName()));
        }
    }

    public int pathSize() {
        return nodesInPath.size();
    }

    /**
     * Method to count the number of transfers in a path of vertices.
     * Uses the line information of the connections between stations.
     * If to consecutive connections are on different lines there was a transfer.
     */
    public void countTransfers() {
        transfers++;
    }

    public double getTotalWeight() {
        return totalWeight;
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

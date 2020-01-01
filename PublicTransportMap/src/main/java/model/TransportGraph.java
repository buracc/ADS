package model;

import java.util.*;
import java.util.stream.Collectors;

public class TransportGraph {

    private int numberOfStations;
    private int numberOfConnections;
    private List<Station> stationList;
    private Map<String, Integer> stationIndices;
    private List<Integer>[] adjacencyLists;
    private Connection[][] connections;

    public TransportGraph(int size) {
        this.numberOfStations = size;
        stationList = new ArrayList<>(size);
        stationIndices = new HashMap<>();
        connections = new Connection[size][size];
        adjacencyLists = (List<Integer>[]) new List[size];
        for (int vertex = 0; vertex < size; vertex++) {
            adjacencyLists[vertex] = new LinkedList<>();
        }
    }

    /**
     * @param vertex Station to be added to the stationList
     *               The method also adds the station with it's index to the map stationIndices
     */
    public void addVertex(Station vertex) {
        stationList.add(vertex);
        stationIndices.put(vertex.getStationName(), stationList.lastIndexOf(vertex));
    }


    /**
     * Method to add an edge to a adjancencyList. The indexes of the vertices are used to define the edge.
     * Method also increments the number of edges, that is number of Connections.
     * The grap is bidirected, so edge(to, from) should also be added.
     *
     * @param from
     * @param to
     */
    private void addEdge(int from, int to) {
        numberOfConnections++;
        if (from >= 0 && to >= 0) {
            adjacencyLists[from].add(to);
        }
    }


    /**
     * Method to add an edge in the form of a connection between stations.
     * The method also adds the edge as an edge of indices by calling addEdge(int from, int to).
     * The method adds the connection to the connections 2D-array.
     * The method also builds the reverse connection, Connection(To, From) and adds this to the connections 2D-array.
     *
     * @param connection The edge as a connection between stations
     */
    public void addEdge(Connection connection) {
        Station from = connection.getFrom();
        Station to = connection.getTo();

        int indexFrom = getIndexOfStationByName(from.getStationName());
        int indexTo = getIndexOfStationByName(to.getStationName());

        if (indexFrom >= 0 && indexTo >= 0) {
            connections[indexFrom][indexTo] = connection;
            addEdge(indexFrom, indexTo);
        }
    }

    public List<Integer> getAdjacentVertices(int index) {
        return adjacencyLists[index];
    }

    public Connection getConnection(int from, int to) {
        if (from < 0 || to < 0) {
            return null;
        }

        return connections[from][to];
    }

    public Connection getConnection(String from, String to) {
        int fromIndex = getIndexOfStationByName(from);
        int toIndex = getIndexOfStationByName(to);
        return getConnection(fromIndex, toIndex);
    }

    public int getIndexOfStationByName(String stationName) {
        if (!stationIndices.containsKey(stationName)) {
            System.out.println(("Station: " + stationName + " does not exist"));
            return -1;
        }

        return stationIndices.get(stationName);
    }

    private List<Connection> getAllConnections() {
        List<Connection> connections = new ArrayList<>();
        for (Connection[] connection : this.connections) {
            for (int j = 0; j < connection.length; j++) {
                if (connection[j] != null) {
                    connections.add(connection[j]);
                }
            }
        }

        return connections;
    }

    public List<Connection> getAdjacentConnections(Station from) {
        return getAllConnections().stream()
                .filter(x -> x.getFrom().equals(from))
                .collect(Collectors.toList());
    }

    public void printConnectionDetails() {
        for (Connection connection : getAllConnections()) {
            System.out.println(connection);
        }
    }

    public Station getStation(String name) {
        for (Station s : stationList) {
            if (s.getStationName().equals(name)) {
                return s;
            }
        }

        return null;
    }

    public Station getStation(int index) {
        return stationList.get(index);
    }

    public int getNumberOfStations() {
        return numberOfStations;
    }

    public List<Station> getStationList() {
        return stationList;
    }

    public int getNumberEdges() {
        return numberOfConnections;
    }

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append(String.format("Graph with %d vertices and %d edges: \n", numberOfStations, numberOfConnections));
        for (int indexVertex = 0; indexVertex < numberOfStations; indexVertex++) {
            resultString.append(stationList.get(indexVertex) + ": ");
            int loopsize = adjacencyLists[indexVertex].size() - 1;
            for (int indexAdjacent = 0; indexAdjacent < loopsize; indexAdjacent++) {
                resultString.append(stationList.get(adjacencyLists[indexVertex].get(indexAdjacent)).getStationName() + "-");
            }
            resultString.append(stationList.get(adjacencyLists[indexVertex].get(loopsize)).getStationName() + "\n");
        }
        return resultString.toString();
    }


    /**
     * A Builder helper class to build a TransportGraph by adding lines and building sets of stations and connections from these lines.
     * Then build the graph from these sets.
     */
    public static class Builder {
        private Set<Station> stationSet;
        private List<Line> lineList;
        private Set<Connection> connectionSet;

        public Builder() {
            lineList = new ArrayList<>();
            stationSet = new HashSet<>();
            connectionSet = new HashSet<>();
        }

        /**
         * Method to add a line to the list of lines and add stations to the line.
         *
         * @param lineDefinition String array that defines the line. The array should start with the name of the line,
         *                       followed by the type of the line and the stations on the line in order.
         * @return
         */
        public Builder addLine(String[] lineDefinition) {
            String name = lineDefinition[0];
            String type = lineDefinition[1];

            Line line = new Line(name, type);

            for (int i = 2; i < lineDefinition.length; i++) {
                Station station = new Station(lineDefinition[i]);
                stationSet.add(station);
                line.addStation(station);
            }

            lineList.add(line);
            return this;
        }

        public Builder addWeight(String[] lineDefinition, double[] weights) throws Exception {
            String lineName = lineDefinition[0];
            String lineType = lineDefinition[1];
            int index = 0;

            if (weights.length > (lineDefinition.length - 2)) {
                throw new Exception("Weights array is not compatible with lines array.");
            }

            Line line = getLine(lineName, lineType);

            if (line == null) {
                throw new Exception("Line does not exist!");
            }

            Station tempPrev = null;
            for (Station station : line.getStationsOnLine()) {
                if (tempPrev == null) {
                    tempPrev = station;
                    continue;
                }

                Station prevStation = tempPrev;
                Optional<Connection> connection = connectionSet.stream()
                        .filter(x -> x.getFrom().equals(prevStation) && x.getTo().equals(station))
                        .findFirst();
                Optional<Connection> reverseConnection = connectionSet.stream()
                        .filter(x -> x.getFrom().equals(station) && x.getTo().equals(prevStation))
                        .findFirst();

                if (connection.isPresent() && reverseConnection.isPresent()) {
                    Connection conn = connection.get();
                    Connection revConn = reverseConnection.get();
                    conn.setWeight(weights[index]);
                    revConn.setWeight(weights[index++]);
                }

                tempPrev = station;
            }

            return this;
        }

        private Line getLine(String name, String type) {
            if (lineList.isEmpty()) {
                return null;
            }

            for (Line line : lineList) {
                if (line.getName().equals(name) && line.getType().equals(type)) {
                    return line;
                }
            }

            return null;
        }

        /**
         * Method that reads all the lines and their stations to build a set of stations.
         * Stations that are on more than one line will only appear once in the set.
         *
         * @return
         */
        public Builder buildStationSet() {
            lineList.forEach(line -> stationSet.addAll(line.getStationsOnLine()));
            return this;
        }


        /**
         * For every station on the set of station add the lines of that station to the lineList in the station
         *
         * @return
         */

        public Builder addLinesToStations() {
            for (Station station : stationSet) {
                for (Line line : lineList) {
                    if (line.getStationsOnLine().contains(station) && !station.hasLine(line)) {
                        station.addLine(line);
                    }
                }
            }

            return this;
        }

        /**
         * Method that uses the list of Lines to build connections from the consecutive stations in the stationList of a line.
         *
         * @return
         */
        public Builder buildConnections() {
            for (Line line : lineList) {
                Station prev = null;
                for (Station station : line.getStationsOnLine()) {
                    if (prev == null) {
                        prev = station;
                        continue;
                    }

                    Connection connection = new Connection(prev, station, 0.0, line);
                    Connection connection2 = new Connection(station, prev, 0.0, line);
                    connectionSet.add(connection);
                    connectionSet.add(connection2);

                    prev = station;
                }
            }

            return this;
        }


        /**
         * Method that builds the graph.
         * All stations of the stationSet are addes as vertices to the graph.
         * All connections of the connectionSet are addes as edges to the graph.
         *
         * @return
         */
        public TransportGraph build() {
            TransportGraph graph = new TransportGraph(stationSet.size());
            for (Station s : this.stationSet) {
                graph.addVertex(s);
            }

            for (Connection c : this.connectionSet) {
                graph.addEdge(c);
            }

            return graph;
        }

    }
}

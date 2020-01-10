package graphalgorithms;

import model.Connection;
import model.Location;
import model.Station;
import model.TransportGraph;

public class A_Star extends DijkstraShortestPath {

    // Stores the euclidean (straight line) distances from vertexes, to the end vertex
    private final double[] euclideanFrom;

    public A_Star(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        euclideanFrom = new double[graph.getNumberOfStations()];

        for (int i = 0; i < graph.getNumberOfStations(); i++) {
            if (i == graph.getIndexOfStationByName(end)) {
                continue;
            }
            Station station = graph.getStation(i);
            Location location = station.getLocation();
            euclideanFrom[i] = location.travelTime(graph.getStation(endIndex).getLocation());
        }
    }

    public void relax(TransportGraph graph, int currentVertex) {
        Station station = graph.getStation(currentVertex);
        nodesVisited.add(station);
        for (Connection connection : graph.getAdjacentConnections(station)) {
            int nextVertex = graph.getIndexOfStationByName(connection.getTo().getStationName());

            if (nodesVisited.contains(connection.getTo())) {
                continue;
            }

            double totalTravelCost = distTo[currentVertex] + connection.getWeight() + getTransferPenalty(currentVertex, nextVertex) + euclideanFrom[nextVertex];

            if (distTo[nextVertex] > totalTravelCost) {
                distTo[nextVertex] = totalTravelCost;
                edgeTo[nextVertex] = currentVertex;
                edgeToType[nextVertex] = connection.getLine();

                if (pq.contains(nextVertex)) {
                    pq.decreaseKey(nextVertex, distTo[nextVertex]);
                } else {
                    pq.insert(nextVertex, distTo[nextVertex]);
                }
            }
        }
    }
}

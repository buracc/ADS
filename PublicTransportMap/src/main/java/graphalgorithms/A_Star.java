package graphalgorithms;

import model.Connection;
import model.Location;
import model.Station;
import model.TransportGraph;

public class A_Star extends DijkstraShortestPath {

    public A_Star(TransportGraph graph, String start, String end) {
        super(graph, start, end);
    }

    @Override
    public void relax(TransportGraph graph, int currentVertex) {
        Station station = graph.getStation(currentVertex);
        Location currentLocation = station.getLocation();
        nodesVisited.add(station);

        for (Connection connection : graph.getAdjacentConnections(station)) {
            int nextVertex = graph.getIndexOfStationByName(connection.getTo().getStationName());
            Station nextStation = graph.getStation(nextVertex);
            Location nextLocation = nextStation.getLocation();
            edgeToType[nextVertex] = connection.getLine();

            if (distTo[nextVertex] > (distTo[currentVertex] + connection.getWeight() + getTransferPenalty(currentVertex, nextVertex))) {
                distTo[nextVertex] = distTo[currentVertex] + connection.getWeight() + getTransferPenalty(currentVertex, nextVertex) + currentLocation.travelTime(nextLocation);
                edgeTo[nextVertex] = currentVertex;

                if (pq.contains(nextVertex)) {
                    pq.changeKey(nextVertex, distTo[nextVertex]);
                } else {
                    pq.insert(nextVertex, distTo[nextVertex]);
                }
            }
        }
    }

}

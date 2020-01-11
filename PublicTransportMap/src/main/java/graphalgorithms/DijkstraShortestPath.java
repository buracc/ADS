package graphalgorithms;

import model.*;

public class DijkstraShortestPath extends AbstractPathSearch {

    protected final double[] distTo;
    protected final IndexMinPQ<Double> pq;

    public DijkstraShortestPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        pq = new IndexMinPQ<>(graph.getNumberOfStations());
        distTo = new double[graph.getNumberOfStations()];

        for (int i = 0; i < graph.getNumberOfStations(); i++)
            distTo[i] = Double.POSITIVE_INFINITY;

        distTo[startIndex] = 0.0;
        pq.insert(startIndex, 0.0);
    }

    public void relax(TransportGraph graph, int currentVertex) {
        Station station = graph.getStation(currentVertex);
        nodesVisited.add(station);

        for (Connection connection : graph.getAdjacentConnections(station)) {
            int nextVertex = graph.getIndexOfStationByName(connection.getTo().getStationName());

            double totalTravelCost = distTo[currentVertex] + connection.getWeight() + getTransferPenalty(currentVertex, nextVertex);

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

    @Override
    public void search() {
        while (!pq.isEmpty()) {
            int index = pq.delMin();
            if (index == endIndex) {
                break;
            }

            relax(graph, index);
        }

        pathTo(endIndex);
    }

    @Override
    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }
}

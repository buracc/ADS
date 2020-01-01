package graphalgorithms;

import model.*;

public class DijkstraShortestPath extends AbstractPathSearch {

    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraShortestPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        pq = new IndexMinPQ<>(graph.getNumberOfStations());
        distTo = new double[graph.getNumberOfStations()];

        for (int i = 0; i < graph.getNumberOfStations(); i++)
            distTo[i] = Double.POSITIVE_INFINITY;

        distTo[startIndex] = 0.0;
        pq.insert(startIndex, 0.0);
    }

    private void relax(TransportGraph graph, int v) {
        Station station = graph.getStation(v);

        for (Connection connection : graph.getAdjacentConnections(station)) {
            int w = graph.getIndexOfStationByName(connection.getTo().getStationName());
            nodesVisited.add(station);

            if (distTo[w] > (distTo[v] + connection.getWeight())) {
                distTo[w] = (distTo[v] + connection.getWeight());
                edgeTo[w] = v;

                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    @Override
    public void search() {
        while (!pq.isEmpty()) {
            relax(graph, pq.delMin());
        }

        pathTo(endIndex);
    }

    @Override
    public boolean hasPathTo(int vertex) {
        return distTo[vertex] < Double.POSITIVE_INFINITY;
    }
}

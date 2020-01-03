package graphalgorithms;

import model.*;

public class DijkstraShortestPath extends AbstractPathSearch {

    protected final double[] distTo;
    protected final IndexMinPQ<Double> pq;
    protected final Line[] edgeToType;

    public DijkstraShortestPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        pq = new IndexMinPQ<>(graph.getNumberOfStations());
        distTo = new double[graph.getNumberOfStations()];
        edgeToType = new Line[graph.getNumberOfStations()];

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
            edgeToType[nextVertex] = connection.getLine();
            double totalTravelCost = distTo[currentVertex] + connection.getWeight() + getTransferPenalty(currentVertex, nextVertex);

            if (distTo[nextVertex] > totalTravelCost) {
                distTo[nextVertex] = totalTravelCost;
                edgeTo[nextVertex] = currentVertex;

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

    protected int getTransferPenalty(int from, int to) {
        final int metroPenalty = 6;
        final int busPenalty = 3;

        Line currentLine = edgeToType[from];
        Line newLine = graph.getConnection(from, to).getLine();

        if (currentLine == null || newLine == null) {
            return 0;
        }

        String currentLineType = currentLine.getType();
        String newLineType = newLine.getType();

        switch (currentLineType) {
            case "metro":
                if (newLineType.equals("metro")) {
                    return metroPenalty;
                }

                if (newLineType.equals("bus")) {
                    return busPenalty;
                }

                break;

            case "bus":
                return busPenalty;
        }

        return 0;
    }
}

package graphalgorithms;

import model.*;

public class DijkstraShortestPath extends AbstractPathSearch {

    private final double[] distTo;
    private final IndexMinPQ<Double> pq;
    private final Line[] edgeToType;

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

    private void relax(TransportGraph graph, int v) {
        Station station = graph.getStation(v);

        for (Connection connection : graph.getAdjacentConnections(station)) {
            int w = graph.getIndexOfStationByName(connection.getTo().getStationName());
            nodesVisited.add(station);
            edgeToType[w] = connection.getLine();

            if (distTo[w] > (distTo[v] + connection.getWeight() + getTransferPenalty(v, w))) {
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

    private int getTransferPenalty(int from, int to) {
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

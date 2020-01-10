package graphalgorithms;

import model.TransportGraph;

public class DepthFirstPath extends AbstractPathSearch {

    public DepthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
    }

    @Override
    public void search() {
        System.out.println(graph.getStation(startIndex));
        System.out.println(graph.getStation(endIndex));

        recursiveSearch(startIndex);

        System.out.println(nodesVisited);
        pathTo(endIndex);
    }

    private void recursiveSearch(int vertex) {

        marked[vertex] = true;
        nodesVisited.add(graph.getStation(vertex));

        for (int next : graph.getAdjacentVertices(vertex)) {
            edgeTo[next] = vertex;
            if (!marked[next]) {
                recursiveSearch(next);
            }
        }
    }
}

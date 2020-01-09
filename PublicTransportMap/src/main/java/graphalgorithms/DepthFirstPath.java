package graphalgorithms;

import model.TransportGraph;

public class DepthFirstPath extends AbstractPathSearch {

    public DepthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
    }

    @Override
    public void search() {
        nodesVisited.add(graph.getStation(startIndex));
        recursiveSearch(startIndex);
        pathTo(endIndex);
    }

    private void recursiveSearch(int vertex) {
        marked[vertex] = true;

        for (int w : graph.getAdjacentVertices(vertex)) {
            if (!marked[w]) {
                nodesVisited.add(graph.getStation(w));
                edgeTo[w] = vertex;
                marked[w] = true;
                if (w == endIndex){
                    break;
                }
                recursiveSearch(w);
            }
        }
    }
}

package graphalgorithms;

import model.TransportGraph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPath extends AbstractPathSearch {

    public BreadthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
        marked = new boolean[graph.getNumberOfStations()];
        edgeTo = new int[graph.getNumberOfStations()];
    }

    @Override
    public void search() {
        Queue<Integer> queue = new LinkedList<>();
        nodesVisited.add(graph.getStation(startIndex));
        marked[startIndex] = true;
        queue.add(startIndex);

        while (!queue.isEmpty()) {
            int currentIndex = queue.remove();

            for (int w : graph.getAdjacentVertices(currentIndex)) {
                if (!marked[w]) {
                    nodesVisited.add(graph.getStation(w));
                    edgeTo[w] = currentIndex;
                    marked[w] = true;
                    queue.add(w);

                    if (w == endIndex) {
                        break;
                    }
                }
            }
        }

        pathTo(endIndex);
    }

}

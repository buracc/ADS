package graphalgorithms;

import model.TransportGraph;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPath extends AbstractPathSearch {

    public BreadthFirstPath(TransportGraph graph, String start, String end) {
        super(graph, start, end);
     }

    @Override
    public void search() {
        Queue<Integer> queue = new LinkedList<>();
        nodesVisited.add(graph.getStation(startIndex));
        marked[startIndex] = true;
        queue.add(startIndex);

        loop:
        while (!queue.isEmpty()) {
            int currentIndex = queue.remove();

            for (int w : graph.getAdjacentVertices(currentIndex)) {
                if (!marked[w]) {

                    nodesVisited.add(graph.getStation(w));
                    edgeTo[w] = currentIndex;
                    marked[w] = true;
                    if (w == endIndex) {
                        break loop;
                    }
                    queue.add(w);
                }
            }

        }

        pathTo(endIndex);
    }

}

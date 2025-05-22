package ru.leti.wise.task.plugin.graph;

import ru.leti.wise.task.graph.model.Graph;
import ru.leti.wise.task.graph.model.Edge;
import ru.leti.wise.task.graph.model.Vertex;

import java.util.*;
import java.util.stream.Collectors;

public class SpiderGraphProperty implements GraphProperty {

    @Override
    public boolean run(Graph graph) {
        if (graph.isDirect()) {
            return false;
        }

        if (graph.getEdgeCount() != graph.getVertexCount() - 1 || !isConnected(graph)) {
            return false;
        }

        Map<Integer, Integer> degrees = new HashMap<>();
        graph.getVertexList().forEach(v -> degrees.put(v.getId(), 0));
        graph.getEdgeList().forEach(e -> {
            degrees.put(e.getSource(), degrees.get(e.getSource()) + 1);
            degrees.put(e.getTarget(), degrees.get(e.getTarget()) + 1);
        });

        List<Integer> centers = new ArrayList<>();
        degrees.forEach((v, d) -> {
            if (d >= 3) centers.add(v);
        });
        if (centers.size() != 1) return false;
        int center = centers.get(0);

        if (degrees.entrySet().stream()
                .anyMatch(entry -> entry.getKey() != center && (entry.getValue() < 1 || entry.getValue() > 2))) {
            return false;
        }

        return true;
    }

    private boolean isConnected(Graph graph) {
        if (graph.getVertexCount() == 0) return true;

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        Integer start = graph.getVertexList().get(0).getId();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            getNeighbors(graph, v).forEach(neighbor -> {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            });
        }

        return visited.size() == graph.getVertexCount();
    }

    private List<Integer> getNeighbors(Graph graph, int vertex) {
        List<Integer> neighbors = new ArrayList<>();
        graph.getEdgeList().forEach(e -> {
            if (e.getSource() == vertex) neighbors.add(e.getTarget());
            else if (e.getTarget() == vertex) neighbors.add(e.getSource());
        });
        return neighbors;
    }
}

package ru.leti;

import java.util.*;
import ru.leti.wise.task.plugin.graph.GraphCharacteristic;
import ru.leti.wise.task.graph.model.*;

public class BipartitePerfectMatchingCounter implements GraphCharacteristic {

    @Override
    public int run(Graph graph) {
        List<List<Vertex>> bipartition = getBipartition(graph);
        if (bipartition == null) {
            return 0;
        }
        List<Vertex> partA = bipartition.get(0);
        List<Vertex> partB = bipartition.get(1);
        if (partA.size() != partB.size()) {
            return 0;
        }
        int[][] matrix = buildBipartiteAdjacencyMatrix(partA, partB, graph);

        return permanent(matrix);
    }

    public List<List<Vertex>> getBipartition(Graph graph) {
        List<Vertex> vertices = graph.getVertexList();
        Map<Integer, Vertex> idToVertex = new HashMap<>();
        for (Vertex v : vertices) {
            idToVertex.put(v.getId(), v);
        }

        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (Vertex v : vertices) {
            adjList.put(v.getId(), new ArrayList<>());
        }

        for (Edge e : graph.getEdgeList()) {
            adjList.get(e.getSource()).add(e.getTarget());
            adjList.get(e.getTarget()).add(e.getSource());
        }

        Map<Integer, Integer> color = new HashMap<>();
        for (Vertex start : vertices) {
            int startId = start.getId();
            if (!color.containsKey(startId)) {
                Queue<Integer> queue = new LinkedList<>();
                queue.add(startId);
                color.put(startId, 1);

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    int currentColor = color.get(current);

                    for (int neighbor : adjList.get(current)) {
                        if (!color.containsKey(neighbor)) {
                            color.put(neighbor, 3 - currentColor);
                            queue.add(neighbor);
                        } else if (color.get(neighbor) == currentColor) {
                            return null;
                        }
                    }
                }
            }
        }

        List<Vertex> partA = new ArrayList<>();
        List<Vertex> partB = new ArrayList<>();
        for (int id : color.keySet()) {
            if (color.get(id) == 1) {
                partA.add(idToVertex.get(id));
            } else {
                partB.add(idToVertex.get(id));
            }
        }

        return Arrays.asList(partA, partB);
    }

    public int[][] buildBipartiteAdjacencyMatrix(List<Vertex> partA, List<Vertex> partB, Graph graph) {
        int n = partA.size();
        int m = partB.size();
        int[][] matrix = new int[n][m];

        Map<Integer, Integer> idToIndexA = new HashMap<>();
        Map<Integer, Integer> idToIndexB = new HashMap<>();

        for (int i = 0; i < n; i++) {
            idToIndexA.put(partA.get(i).getId(), i);
        }
        for (int i = 0; i < m; i++) {
            idToIndexB.put(partB.get(i).getId(), i);
        }

        for (Edge edge : graph.getEdgeList()) {
            int from = edge.getSource();
            int to = edge.getTarget();
            if (idToIndexA.containsKey(from) && idToIndexB.containsKey(to)) {
                matrix[idToIndexA.get(from)][idToIndexB.get(to)] = 1;
            }
        }
        return matrix;
    }

    public int permanent(int[][] matrix) {
        int n = matrix.length;
        int total = 0;
        int subsets = 1 << n;

        for (int S = 0; S < subsets; S++) {
            int[] rowSums = new int[n];

            for (int j = 0; j < n; j++) {
                if ((S & (1 << j)) != 0) {
                    for (int i = 0; i < n; i++) {
                        rowSums[i] += matrix[i][j];
                    }
                }
            }

            int prod = 1;
            for (int i = 0; i < n; i++) {
                prod *= rowSums[i];
            }

            int bitCount = Integer.bitCount(S);
            if (bitCount % 2 == 0) {
                total += prod;
            } else {
                total -= prod;
            }
        }

        return (n % 2 == 0) ? total : -total;
    }
}
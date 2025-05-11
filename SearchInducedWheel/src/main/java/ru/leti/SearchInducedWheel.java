package ru.leti;

import ru.leti.wise.task.graph.model.Edge;
import ru.leti.wise.task.plugin.graph.GraphProperty;
import ru.leti.wise.task.graph.model.Graph;
import ru.leti.wise.task.graph.model.Vertex;

import java.util.ArrayList;
import java.util.List;

public class SearchInducedWheel implements GraphProperty {

    private Graph graph;
    private ArrayList<Integer> usedId;

    private int getDegree(Vertex vertex){
        int degree = 0;
        List<Edge> edges = graph.getEdgeList();
        for (Edge edge : edges) {
            if (edge.getSource() == vertex.getId()){
                degree++;
            }
            if (edge.getTarget() == vertex.getId()){
                degree++;
            }
        }
        return degree;
    }

    private ArrayList<Integer> getNeighbors(Vertex vertex){
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        for (Edge edge : graph.getEdgeList()){
            if (edge.getSource() == vertex.getId()){
                neighbors.add(edge.getTarget());
            }
            if (edge.getTarget() == vertex.getId()){
                neighbors.add(edge.getSource());
            }
        }
        return neighbors;
    }

    private boolean isCenter(Vertex vertex) {
        if (getDegree(vertex) < 3)
            return false;
        return true;
    }

    private Vertex getVertex(int vertexId){
        for (Vertex vertex : graph.getVertexList()){
            if (vertex.getId() == vertexId){
                return vertex;
            }
        }
        return graph.getVertexList().get(0);
    }

    private boolean dfs(Vertex vertex, ArrayList<Integer> neighbors, Vertex startVertex, Integer prevVertexId) {
        if (usedId.contains(vertex.getId()))
            return true;

        usedId.add(vertex.getId());
        boolean isFindCycles = false;
        List<Integer> currentNeighbors = getNeighbors(vertex);

        for (Integer currentNeighbor : currentNeighbors){
            if (neighbors.contains(currentNeighbor) && !currentNeighbor.equals(prevVertexId)){
                if (!(vertex.equals(startVertex) && usedId.contains(currentNeighbor))) {
                    Vertex newVertex = getVertex(currentNeighbor);
                    isFindCycles |= dfs(newVertex, neighbors, startVertex, vertex.getId());
                }
            }
        }
        return isFindCycles;
    }

    private boolean checkVertex(Vertex vertex){
        if (isCenter(vertex)){
            ArrayList<Integer> neighbors = getNeighbors(vertex);
            this.usedId = new ArrayList<Integer>();
            return dfs(vertex, neighbors, vertex, vertex.getId());
        }
        return false;
    }

    @Override
    public boolean run(Graph graph){
        this.graph = graph;
        for (Vertex vertex : graph.getVertexList()) {
            if (checkVertex(vertex))
                return true;
        }
        return false;
    }
}

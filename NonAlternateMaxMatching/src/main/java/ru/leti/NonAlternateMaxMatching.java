package ru.leti;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ru.leti.wise.task.graph.model.Edge;
import ru.leti.wise.task.graph.model.Graph;
import ru.leti.wise.task.graph.model.Vertex;
import ru.leti.wise.task.plugin.graph.GraphCharacteristic;

public class NonAlternateMaxMatching implements GraphCharacteristic {
    private int graphSize;
    private HashMap<Integer, List<Integer>> adj;
    private HashMap<Integer, Integer> match, p, base;
    private HashSet<Integer> used, blossom;
    private Deque<Integer> queue;
    private int matches;

    @Override
    public int run(Graph graph) {
        this.graphSize = graph.getVertexCount();
        this.adj = createAdjList(graph);
        this.match = new HashMap<>();
        for (int i : adj.keySet()) {
            this.match.put(i, -1);
        }
        this.p = new HashMap<>();
        this.base = new HashMap<>();
        this.used = new HashSet<>();
        this.blossom = new HashSet<>();
        this.queue = new ArrayDeque<>();
        this.matches = this.solve(); // Blossom Algorithm
        return this.matches;
    }

    private HashMap<Integer, List<Integer>> createAdjList(Graph graph) {
        HashMap<Integer, List<Integer>> adjList = new HashMap<>(graphSize);
        for (Vertex vertex : graph.getVertexList()) {
            int vertexId = vertex.getId();
            adjList.put(vertexId, new ArrayList<>());
        }
        for (Edge elem : graph.getEdgeList()) {
            int source = elem.getSource();
            int target = elem.getTarget();
            adjList.get(source).add(target);
            adjList.get(target).add(source);
        }
        return adjList;
    }

    private int lca(int a, int b) {
        HashSet<Integer> usedPath = new HashSet<>();
        while (true) {
            a = base.get(a);
            usedPath.add(a);
            if (match.get(a) < 0)
                break;
            a = p.get(match.get(a));
        }
        while (true) {
            b = base.get(b);
            if (usedPath.contains(b))
                return b;
            b = p.get(match.get(b));
        }
    }

    private void markPath(int v, int b, int x) {
        while (base.get(v) != b) {
            int mv = match.get(v);
            blossom.add(base.get(v));
            blossom.add(base.get(mv));
            p.put(v, x);
            x = mv;
            v = p.get(x);
        }
    }

    private boolean findPath(int root) {
        for (int i : adj.keySet()) {
            p.put(i, -1);
        }
        for (int i : adj.keySet()) {
            base.put(i, i);
        }
        used.clear();
        queue.clear();

        used.add(root);
        queue.add(root);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int u : adj.get(v)) {
                if (base.get(v).equals(base.get(u)) || match.get(v) == u) {
                    continue;
                }
                // Blossom found
                if (u == root || (match.get(u) >= 0 && p.get(match.get(u)) >= 0)) {
                    int curbase = lca(v, u);
                    blossom.clear();
                    markPath(v, curbase, u);
                    markPath(u, curbase, v);
                    for (int i : adj.keySet()) {
                        if (blossom.contains(base.get(i))) {
                            base.put(i, curbase);
                            if (!used.contains(i)) {
                                used.add(i);
                                queue.add(i);
                            }
                        }
                    }
                } else if (p.get(u) < 0) {
                    // Extend the alternating tree
                    p.put(u, v);
                    // Found augmenting path
                    if (match.get(u) < 0) {
                        int cur = u;
                        while (cur >= 0) {
                            int prev = p.get(cur);
                            int next = (prev >= 0 ? match.get(prev) : -1);
                            match.put(cur, prev);
                            match.put(prev, cur);
                            cur = next;
                        }
                        return true;
                    }
                    // Continue BFS from the matched partner
                    int mu = match.get(u);
                    if (!used.contains(mu)) {
                        used.add(mu);
                        queue.add(mu);
                    }
                }
            }
        }
        return false;
    }

    private int solve() {
        int res = 0;
        for (int v : this.adj.keySet()) {
            if (match.get(v) < 0) {
                if (findPath(v)) {
                    res++;
                }
            }
        }
        return res;
    }

    public HashMap<Integer, Integer> getMatch() {
        return match;
    }

}
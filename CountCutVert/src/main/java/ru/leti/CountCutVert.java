package ru.leti;

import ru.leti.wise.task.plugin.graph.GraphCharacteristic;
import ru.leti.wise.task.graph.model.Graph;

import java.util.HashMap;
import java.util.Map;

public class CountCutVert implements GraphCharacteristic {
    Map<Integer, Boolean> visited;
    Map<Integer, Boolean> isCutVert;
    Map<Integer, Integer> timeIn;
    Map<Integer, Integer> low;
    int timer = 0;
    private void dfs(Graph graph, int v, int parent)
    {
        visited.put(v, true);
        timeIn.put(v, timer);
        low.put(v, timer);
        timer++;
        int child_counter = 0;

        var edges = graph.getEdgeList();
        for (var e : edges)
        {
            int to = -1;
            if (e.getSource() == v) to = e.getTarget();
            else if (e.getTarget() == v) to = e.getSource();

            if (to == v) continue;

            if (to == parent) continue;

            if (visited.get(to))
            {
                low.put(v, Math.min(low.get(v), timeIn.get(to)));
            }
            else
            {
                dfs(graph, to, v);
                low.put(v, Math.min(low.get(v), low.get(to)));
                if (low.get(to) >= timeIn.get(v) && parent != -1)
                {
                    isCutVert.put(v, true);
                }
                child_counter++;
            }
        }

        if (parent == -1 && child_counter > 1) isCutVert.put(v, true);
    }
    @Override
    public int run(Graph graph)
    {
        visited = new HashMap<>();
        isCutVert = new HashMap<>();
        low = new HashMap<>();
        timeIn = new HashMap<>();

        for (var v: graph.getVertexList())
        {
            visited.put(v.getId(), false);
            low.put(v.getId(), -1);
            timeIn.put(v.getId(), -1);
            isCutVert.put(v.getId(), false);
        }

        for (var v: graph.getVertexList())
        {
            if (!visited.get(v.getId()))
            {
                dfs(graph, v.getId(), -1);
            }
        }

        int ans = 0;
        for (var v: isCutVert.values())
        {
            if (v) ans++;
        }

        return ans;
    }
}

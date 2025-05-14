package ru.leti;

import ru.leti.wise.task.graph.util.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NonAlternateMaxMatchingTest {

    private final NonAlternateMaxMatching matcher = new NonAlternateMaxMatching();

    @Test
    public void P10() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/P10.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void SingleVertex() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/single_vertex.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void SingleEdge() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/single_edge.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void TwoDisjointEdges() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/two_disjoint_edges.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void P6() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/P6.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void EvenCycleC6() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/even_cycle_C6.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void CompleteBipartiteK33() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/complete_bipartite_K3_3.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void StarGraphS4() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/star_graph_S6.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void OddCycleC5() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/odd_cycle_C5.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void OddCycleWithChord() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/odd_cycle_C5.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void BowTieGraph() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/bow_tie_graph.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void Peterson() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/peterson.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void NestedCyclesBipartiteGraph() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/nested_cycles_bipartite_graph.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void Square() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/square.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void C6Dioganal() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/c6_dioganal.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void Leader4() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/leader4.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void Q3() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/Q3.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void RingOf4Square() throws FileNotFoundException {
        var g = FileLoader.loadGraphFromJson("src/test/resources/ring_of_4_square.json");
        int result = matcher.run(g);
        assertThat(result).isEqualTo(8);
    }

}

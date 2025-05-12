package ru.leti;

import ru.leti.wise.task.graph.util.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchInducedWheelTest {

    private final SearchInducedWheel checker = new SearchInducedWheel();

    @Test
    public void Standartgraph() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/standart_graph.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void StandartGraphWithoutEdjes() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/standart_graph_without_edjes.json");
        assertThat(checker.run(graph)).isFalse();
    }

    @Test
    public void StandartGraphWithExtraEdjes() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/standart_graph_with_extra_edjes.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void StandartGraphWithoutEdjesBetweenNeighbour() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/standart_graph_without_edjes_between_neighbour.json");
        assertThat(checker.run(graph)).isFalse();
    }

    @Test
    public void GraphWithOneMoreEdjes() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/graph_with_one_more_edjes.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void HelmGraphH8() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/helm_graph_H8.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void Octahedron() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/octahedron.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void G6() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/G6.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void C5K2() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/C5_and_K2.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void IsohendralGraph() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/isohedral.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void Octahedral() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/octahedral.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void W6() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/W6.json");
        assertThat(checker.run(graph)).isTrue();
    }

    @Test
    public void SingleVertex() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/single_vertex.json");
        assertThat(checker.run(graph)).isFalse();
    }

    @Test
    public void SingleEdge() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/single_edge.json");
        assertThat(checker.run(graph)).isFalse();
    }

    @Test
    public void TwoDisjointEdges() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/two_disjoint_edges.json");
        assertThat(checker.run(graph)).isFalse();
    }

    @Test
    public void Triangle() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/triangle.json");
        assertThat(checker.run(graph)).isFalse();
    }

    @Test
    public void Square() throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/square.json");
        assertThat(checker.run(graph)).isFalse();
    }

}

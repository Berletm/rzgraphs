package ru.leti;

import ru.leti.wise.task.graph.util.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SearchInducedWheelTest {

    private final SearchInducedWheel checker = new SearchInducedWheel();

    @Test
    public void Standartgraph() throws FileNotFoundException {
        var wheel = FileLoader.loadGraphFromJson("src/test/resources/standart_graph.json");
        assertThat(checker.run(wheel)).isTrue();
    }

    @Test
    public void StandartGraphWithoutEdjes() throws FileNotFoundException {
        var line = FileLoader.loadGraphFromJson("src/test/resources/standart_graph_without_edjes.json");
        assertThat(checker.run(line)).isFalse();
    }

    @Test
    public void StandartGraphWithExtraEdjes() throws FileNotFoundException {
        var wheel4 = FileLoader.loadGraphFromJson("src/test/resources/standart_graph_with_extra_edjes.json");
        assertThat(checker.run(wheel4)).isTrue();
    }

    @Test
    public void StandartGraphWithoutEdjesBetweenNeighbour() throws FileNotFoundException {
        var tri = FileLoader.loadGraphFromJson("src/test/resources/standart_graph_without_edjes_between_neighbour.json");
        assertThat(checker.run(tri)).isFalse();
    }

    @Test
    public void GraphWithOneMoreEdjes() throws FileNotFoundException {
        var tri = FileLoader.loadGraphFromJson("src/test/resources/graph_with_one_more_edjes.json");
        assertThat(checker.run(tri)).isTrue();
    }

    @Test
    public void HelmGraphH8() throws FileNotFoundException {
        var tri = FileLoader.loadGraphFromJson("src/test/resources/helm_graph_H8.json");
        assertThat(checker.run(tri)).isTrue();
    }

    @Test
    public void Octahedron() throws FileNotFoundException {
        var tri = FileLoader.loadGraphFromJson("src/test/resources/octahedron.json");
        assertThat(checker.run(tri)).isTrue();
    }

    @Test
    public void G6() throws FileNotFoundException {
        var tri = FileLoader.loadGraphFromJson("src/test/resources/G6.json");
        assertThat(checker.run(tri)).isTrue();
    }

    @Test
    public void C5K2() throws FileNotFoundException {
        var tri = FileLoader.loadGraphFromJson("src/test/resources/C5_and_K2.json");
        assertThat(checker.run(tri)).isTrue();
    }

    @Test
    public void IsohendralGraph() throws FileNotFoundException {
        var tri = FileLoader.loadGraphFromJson("src/test/resources/isohedral.json");
        assertThat(checker.run(tri)).isTrue();
    }

    @Test
    public void Octahedral() throws FileNotFoundException {
        var tri = FileLoader.loadGraphFromJson("src/test/resources/octahedral.json");
        assertThat(checker.run(tri)).isTrue();
    }

    @Test
    public void W6() throws FileNotFoundException {
        var tri = FileLoader.loadGraphFromJson("src/test/resources/W6.json");
        assertThat(checker.run(tri)).isTrue();
    }

}

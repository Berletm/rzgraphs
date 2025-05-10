package ru.leti;

import java.io.FileNotFoundException;
import ru.leti.wise.task.graph.util.FileLoader;
import ru.leti.wise.task.graph.model.Graph;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.SoftAssertions;


class CountCutVertTest {
    @Test
    public void countCutVertTest() throws FileNotFoundException {
        SoftAssertions softly = new SoftAssertions();
        CountCutVert countCutVert = new CountCutVert();

        String path = "src/test/resources/";
        Graph graph;


        graph = FileLoader.loadGraphFromJson(path + "C4_0.json");
        softly.assertThat(countCutVert.run(graph) == 0).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "C4_with_tail_1.json");
        softly.assertThat(countCutVert.run(graph) == 1).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "K1_add_0.json");
        softly.assertThat(countCutVert.run(graph) == 0).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "K4_0.json");
        softly.assertThat(countCutVert.run(graph) == 0).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "K4_with_two_tails_2.json");
        softly.assertThat(countCutVert.run(graph) == 2).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "P2_0.json");
        softly.assertThat(countCutVert.run(graph) == 0).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "P3_1.json");
        softly.assertThat(countCutVert.run(graph) == 1).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "P3_root_1.json");
        softly.assertThat(countCutVert.run(graph) == 1).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "P4_2.json");
        softly.assertThat(countCutVert.run(graph) == 2).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "P4_root_2.json");
        softly.assertThat(countCutVert.run(graph) == 2).isTrue();

        graph = FileLoader.loadGraphFromJson(path + "T4_1.json");
        softly.assertThat(countCutVert.run(graph) == 1).isTrue();

        softly.assertAll();
    }
}
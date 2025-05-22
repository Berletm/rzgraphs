package ru.leti.wise.task.plugin.graph;

import ru.leti.wise.task.graph.util.FileLoader;
import ru.leti.wise.task.graph.model.Graph;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SpiderGraphPropertyTest {
	@Test
	public void spiderGraphPropertyTest() throws FileNotFoundException {
    	SpiderGraphProperty spiderGraphProperty = new SpiderGraphProperty();
		Graph graph;

		graph = FileLoader.loadGraphFromJson("src/test/resources/one_vertex.json");
		assertThat(spiderGraphProperty.run(graph)).isFalse();
    	
    	graph = FileLoader.loadGraphFromJson("src/test/resources/hex_spider_graph.json");
    	assertThat(spiderGraphProperty.run(graph)).isTrue();

		graph = FileLoader.loadGraphFromJson("src/test/resources/disconnected_graph.json");
    	assertThat(spiderGraphProperty.run(graph)).isFalse();

		graph = FileLoader.loadGraphFromJson("src/test/resources/graph_with_loop.json");
    	assertThat(spiderGraphProperty.run(graph)).isFalse();

		graph = FileLoader.loadGraphFromJson("src/test/resources/different_leg_size_graph.json");
    	assertThat(spiderGraphProperty.run(graph)).isTrue();

		graph = FileLoader.loadGraphFromJson("src/test/resources/triangle_spider_graph.json");
    	assertThat(spiderGraphProperty.run(graph)).isTrue();

		graph = FileLoader.loadGraphFromJson("src/test/resources/two_vertices.json");
    	assertThat(spiderGraphProperty.run(graph)).isFalse();

		graph = FileLoader.loadGraphFromJson("src/test/resources/three_vertices_bamboo.json");
    	assertThat(spiderGraphProperty.run(graph)).isFalse();

		graph = FileLoader.loadGraphFromJson("src/test/resources/K3.json");
    	assertThat(spiderGraphProperty.run(graph)).isFalse();

		graph = FileLoader.loadGraphFromJson("src/test/resources/oct_spider_graph.json");
    	assertThat(spiderGraphProperty.run(graph)).isTrue();

		graph = FileLoader.loadGraphFromJson("src/test/resources/directed_graph.json");
    	assertThat(spiderGraphProperty.run(graph)).isFalse();
	}
}

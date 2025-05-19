import ru.leti.BipartitePerfectMatchingCounter;
import ru.leti.wise.task.graph.util.FileLoader;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BipartitePerfectMatchingCounterTest {

    private final BipartitePerfectMatchingCounter counter = new BipartitePerfectMatchingCounter();

    private int runOn(String resource) throws FileNotFoundException {
        var graph = FileLoader.loadGraphFromJson("src/test/resources/" + resource);
        return counter.run(graph);
    }

    @Test
    void emptyGraph() throws FileNotFoundException {
        assertThat(runOn("empty.json") == 1).isTrue();
    }

    @Test
    void singleEdge() throws FileNotFoundException {
        assertThat(runOn("single_edge.json") == 1).isTrue();
    }

    @Test
    void twoDisjoint() throws FileNotFoundException {
        assertThat(runOn("two_edges.json") == 1).isTrue();
    }

    @Test
    void pathLength3() throws FileNotFoundException {
        assertThat(runOn("path3.json") == 1).isTrue();
    }

    @Test
    void cycle4() throws FileNotFoundException {
        assertThat(runOn("cycle4.json") == 2).isTrue();
    }

    @Test
    void completeK3_3() throws FileNotFoundException {
        assertThat(runOn("k3_3.json") == 6).isTrue();
    }

    @Test
    void completeK2_2() throws FileNotFoundException {
        assertThat(runOn("k2_2.json") == 2).isTrue();
    }

    @Test
    void nonBipartite() throws FileNotFoundException {
        assertThat(runOn("non_bipartite.json") == 0).isTrue();
    }

    @Test
    void unbalanced() throws FileNotFoundException {
        assertThat(runOn("unbalanced3_2.json") == 0).isTrue();
    }

    @Test
    void disconnected() throws FileNotFoundException {
        assertThat(runOn("disconnected.json") == 2).isTrue();
    }

    @Test
    void c6EvenCycle() throws FileNotFoundException {
        assertThat(runOn("c6_even_cycle.json") == 2).isTrue();
    }

    @Test
    void noEdges() throws FileNotFoundException {
        assertThat(runOn("no_edges.json") == 0).isTrue();
    }

    @Test
    void crossComponentInvalid() throws FileNotFoundException {
        assertThat(runOn("cross_component_invalid.json") == 0).isTrue();
    }

    @Test
    void partialMatchPossible() throws FileNotFoundException {
        assertThat(runOn("partial_match_possible.json") == 0).isTrue();
    }

    @Test
    void pathWithExtraLeaf() throws FileNotFoundException {
        assertThat(runOn("path_with_extra_leaf.json") == 0).isTrue();
    }

    @Test
    void oneUnconnectedVertex() throws FileNotFoundException {
        assertThat(runOn("one_unconnected_vertex.json")).isEqualTo(0);
    }
}

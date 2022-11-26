package test.utils;

import main.contracts.Identifiable;
import main.structures.PriorityQueue;
import main.utils.PriorityQueueViewer;
import main.utils.SortMode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PriorityQueueViewerTest {

    PriorityQueueViewer<Identifiable<Integer>> viewer = null;

    @BeforeEach
    void open() {
        // Criar itens para imprimir
        Identifiable<Integer> item1 = Mockito.mock(Identifiable.class);
        Identifiable<Integer> item2 = Mockito.mock(Identifiable.class);
        Identifiable<Integer> item3 = Mockito.mock(Identifiable.class);
        List<Identifiable<Integer>> items = Arrays.asList(item1, item2, item3);

        // Configurar identificadores
        Mockito.when(item1.getIdentifier()).thenReturn(1);
        Mockito.when(item2.getIdentifier()).thenReturn(2);
        Mockito.when(item3.getIdentifier()).thenReturn(3);

        // Configurar strings
        Mockito.when(item1.toString()).thenReturn("Item #1");
        Mockito.when(item2.toString()).thenReturn("Item #2");
        Mockito.when(item3.toString()).thenReturn("Item #3");

        PriorityQueue<Identifiable<Integer>> queue = new PriorityQueue<>(SortMode.DESCENDING);
        queue.addAll(items);

        this.viewer = new PriorityQueueViewer<>(queue);
    }

    @AfterEach
    void close() {
        this.viewer = null;
    }

    @Test
    void invalidElementHeight() {
        Assertions.assertThatThrownBy(() -> PriorityQueueViewer.elementHeight(0))
                .isInstanceOf(IndexOutOfBoundsException.class);

        Assertions.assertThatThrownBy(() -> PriorityQueueViewer.elementHeight(-1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @ParameterizedTest
    @MethodSource("elementOrdersAndHeight")
    void validElementHeight(int order, int height) {
        Assertions.assertThat(PriorityQueueViewer.elementHeight(order)).isEqualTo(height);
    }

    @Test
    void queueToHeap() {
        String expected = """
                Item #1
                Item #2
                Item #3
                """;

        Assertions.assertThat(this.viewer.toHeap()).isEqualTo(expected);
    }

    @Test
    void queueToTopics() {
        String expected = """
                - Item #1
                   - Item #2
                   - Item #3""";

        Assertions.assertThat(this.viewer.toTopics()).isEqualTo(expected);
    }

    @Test
    void queueToTree() {
        String expected = """
                     [01s]              \s
                [02s]     [03s]    \s""";

        Assertions.assertThat(viewer.toTree()).isEqualTo(expected);
    }

    @Test
    void emptyQueueToHeap() {
        String expected = "";

        this.viewer = new PriorityQueueViewer<>(new PriorityQueue<>());
        Assertions.assertThat(this.viewer.toHeap()).isEqualTo(expected);
    }

    @Test
    void emptyQueueToTopics() {
        String expected = "";

        this.viewer = new PriorityQueueViewer<>(new PriorityQueue<>());
        Assertions.assertThat(this.viewer.toTopics()).isEqualTo(expected);
    }

    @Test
    void emptyQueueToTree() {
        String expected = "";

        this.viewer = new PriorityQueueViewer<>(new PriorityQueue<>());
        Assertions.assertThat(viewer.toTree()).isEqualTo(expected);
    }
    static Stream<Arguments> elementOrdersAndHeight() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(2, 1),
                Arguments.of(3, 1),
                Arguments.of(4, 2),
                Arguments.of(5, 2),
                Arguments.of(6, 2),
                Arguments.of(7, 2),
                Arguments.of(8, 3)
        );
    }
}

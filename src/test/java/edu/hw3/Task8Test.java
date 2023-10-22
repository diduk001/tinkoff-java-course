package edu.hw3;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Тест корректности работы с массивом {1, 2, 3, 4, 5}")
    void integerCorrectnessTest() {
        final List<Integer> list = List.of(1, 2, 3, 4, 5);
        final Task8.BackwardIterator<Integer> backwardIterator = new Task8.BackwardIterator<>(list);

        ArrayList<Integer> iteratingValues = new ArrayList<>();
        while (backwardIterator.hasNext()) {
            iteratingValues.add(backwardIterator.next());
        }

        final ArrayList<Integer> expected = new ArrayList<>(List.of(5, 4, 3, 2, 1));
        assertThat(iteratingValues).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест корректности работы с массивом {\"a\", \"b\", \"c\", \"d\", \"e\"}")
    void stringCorrectnessTest() {
        final List<String> list = List.of("a", "b", "c", "d", "e");
        final Task8.BackwardIterator<String> backwardIterator = new Task8.BackwardIterator<>(list);

        ArrayList<String> iteratingValues = new ArrayList<>();
        while (backwardIterator.hasNext()) {
            iteratingValues.add(backwardIterator.next());
        }

        final ArrayList<String> expected = new ArrayList<>(List.of("e", "d", "c", "b", "a"));
        assertThat(iteratingValues).isEqualTo(expected);
    }

}

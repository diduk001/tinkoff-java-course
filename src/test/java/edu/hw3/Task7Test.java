package edu.hw3;

import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Проверка на корректность для типа String")
    void StringCorrectnessCheck() {
        TreeMap<String, String> tree = new TreeMap<>(new Task7.NullComparator());
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("Проверка на корректность для типа Integer")
    void IntegerCorrectnessCheck() {
        TreeMap<Integer, Integer> tree = new TreeMap<>(new Task7.NullComparator());
        tree.put(null, 1337);
        assertThat(tree.containsKey(null)).isTrue();
    }

}

package edu.hw3;

import edu.hw3.Task7.NullComparator;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Проверка на корректность для типа String")
    void StringCorrectnessCheck() {
        final TreeMap<String, String> tree = new TreeMap<>(new NullComparator());
        tree.put(null, "test");
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("Проверка на корректность для типа Integer")
    void IntegerCorrectnessCheck() {
        final TreeMap<Integer, Integer> tree = new TreeMap<>(new NullComparator());
        tree.put(null, 1337);
        assertThat(tree.containsKey(null)).isTrue();
    }

    @Test
    @DisplayName("Проверка на корректность для двух чисел")
    void IntegerWithoutNullCorrectness() {
        final TreeSet<Integer> treeSet = new TreeSet<>(new NullComparator());
        treeSet.add(1);
        treeSet.add(2);
        final TreeSet<Integer> expected = new TreeSet<>();
        expected.add(1);
        expected.add(2);

        assertThat(treeSet.contains(1)).isTrue();
        assertThat(treeSet.contains(2)).isTrue();
        assertThat(treeSet.size()).isEqualTo(2);
        assertThat(treeSet).isEqualTo(expected);
    }

}

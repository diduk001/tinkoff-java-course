package edu.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

final public class Task3Test {
    final static ArrayList<Object> SAMPLE_1 = new ArrayList<>(List.of("a", "bb", "a", "bb"));
    final static ArrayList<Object> SAMPLE_2 = new ArrayList<>(List.of("this", "and", "that", "and"));
    final static ArrayList<Object> SAMPLE_3 = new ArrayList<>(List.of("код", "код", "код", "bug"));
    final static ArrayList<Object> SAMPLE_4 = new ArrayList<>(List.of(1, 1, 2, 2));

    static Arguments[] sampleTests() {
        return new Arguments[] {
            Arguments.of(SAMPLE_1),
            Arguments.of(SAMPLE_2),
            Arguments.of(SAMPLE_3),
            Arguments.of(SAMPLE_4),
        };
    }

    @ParameterizedTest(name = "Пример из условия #{index}: {0}")
    @MethodSource("sampleTests")
    void sampleTests(ArrayList<Object> list) {
        final HashMap<Object, Integer> result = Task3.freqDict(list);
        HashMap<Object, Integer> expected;
        if (list.equals(SAMPLE_1)) {
            expected = new HashMap<>(Map.of(
                "a", 2,
                "bb", 2
            ));
        } else if (list.equals(SAMPLE_2)) {
            expected = new HashMap<>(Map.of(
                "this", 1,
                "and", 2,
                "that", 1
            ));
        } else if (list.equals(SAMPLE_3)) {
            expected = new HashMap<>(Map.of(
                "код", 3,
                "bug", 1
            ));
        } else if (list.equals(SAMPLE_4)) {
            expected = new HashMap<>(Map.of(
                1, 2,
                2, 2
            ));
        } else {
            throw new IllegalArgumentException("List is not from sample");
        }

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final ArrayList<Object> emptyList = new ArrayList<>();
        final HashMap<Object, Integer> result = Task3.freqDict(emptyList);
        final HashMap<Object, Integer> emptyHashMap = new HashMap<>();
        assertThat(result).isEqualTo(emptyHashMap);
    }

    @Test
    @DisplayName("Произвольный объект")
    void customObjectTest() {
        record customObject(int val) {
        }

        final customObject customObject_1 = new customObject(1);
        final customObject customObject_2 = new customObject(2);
        final customObject customObject_3 = new customObject(3);

        final ArrayList<Object> list = new ArrayList<>(List.of(
            customObject_1,
            customObject_2,
            customObject_2,
            customObject_3,
            customObject_3,
            customObject_3
        ));

        final HashMap<Object, Integer> result = Task3.freqDict(list);
        final HashMap<Object, Integer> expected = new HashMap<>(Map.of(
            customObject_1, 1,
            customObject_2, 2,
            customObject_3, 3
        ));

        assertThat(result).isEqualTo(expected);
    }
}

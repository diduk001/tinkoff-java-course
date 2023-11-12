package edu.hw3;

import edu.hw3.Task2.BracketClusterization;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @ParameterizedTest(name = "Пример из условия #{index}: \"{0}\"")
    @ValueSource(strings = {"()()()", "((()))", "((()))(())()()(()())", "((())())(()(()()))"})
    void sampleTests(String bracketSequence) {
        final List<String> result = BracketClusterization.clusterize(bracketSequence);
        final List<String> expected = switch (bracketSequence) {
            case "()()()" -> List.of("()", "()", "()");
            case "((()))" -> List.of("((()))");
            case "((()))(())()()(()())" -> List.of("((()))", "(())", "()", "()", "(()())");
            case "((())())(()(()()))" -> List.of("((())())", "(()(()()))");
            default -> new ArrayList<>(); // Unexpected value
        };

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyStringTest() {
        final List<String> result = BracketClusterization.clusterize("");
        assertThat(result).isEqualTo(List.of());
    }

    @ParameterizedTest(name = "Несбалансированная строка: {0}")
    @ValueSource(strings = {"(((", ")))", "()(", "())", "(()", ")()"})
    void imbalancedStringTest(String bracketSequence) {
        assertThrows(IllegalArgumentException.class, () ->
            BracketClusterization.clusterize(bracketSequence));
    }
}

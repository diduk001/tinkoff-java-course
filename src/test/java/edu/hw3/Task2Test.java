package edu.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @ParameterizedTest(name = "Пример из условия #{index}: \"{0}\"")
    @ValueSource(strings = {"()()()", "((()))", "((()))(())()()(()())", "((())())(()(()()))"})
    void sampleTests(String bracketSequence) {
        final ArrayList<String> result = Task2.clusterize(bracketSequence);
        final ArrayList<String> expected = switch (bracketSequence) {
            case "()()()" -> new ArrayList<>(Arrays.asList("()", "()", "()"));
            case "((()))" -> new ArrayList<>(List.of("((()))"));
            case "((()))(())()()(()())" -> new ArrayList<>(Arrays.asList("((()))", "(())", "()", "()", "(()())"));
            case "((())())(()(()()))" -> new ArrayList<>(Arrays.asList("((())())", "(()(()()))"));
            default -> new ArrayList<>(); // Unexpected value
        };

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyStringTest() {
        final ArrayList<String> result = Task2.clusterize("");
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @ParameterizedTest(name = "Несбалансированная строка: {0}")
    @ValueSource(strings = {"(((", ")))", "()(", "())", "(()", ")()"})
    void imbalancedStringTest(String bracketSequence) {
        boolean threwException = false;
        try {
            Task2.clusterize(bracketSequence);
        } catch (Exception ignored) {
            threwException = true;
        }
        assertThat(threwException).isTrue();
    }
}

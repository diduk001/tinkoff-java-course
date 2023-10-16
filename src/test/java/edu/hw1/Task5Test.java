package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @ParameterizedTest(name = "Палиндром/предок палиндрома: {0}")
    @ValueSource(ints = {11211230, 13001120, 23336014, 11, 1, 123454321})
    void trueTests(final int palindromeNumber) {
        boolean result = Task5.isPalindromeDescendant(palindromeNumber);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Отрицательное значение")
    void negativeValueTest() {
        boolean result = Task5.isPalindromeDescendant(-1001);
        assertThat(result).isEqualTo(false);
    }
}

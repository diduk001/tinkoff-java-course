package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Первый пример")
    void firstSampleTest() {
        // given
        final Integer sample = 11211230;

        // when
        boolean result = Task5.isPalindromeDescendant(sample);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Второй пример")
    void secondSampleTest() {
        // given
        final Integer sample = 13001120;

        // when
        boolean result = Task5.isPalindromeDescendant(sample);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Третий пример")
    void thirdSampleTest() {
        // given
        final Integer sample = 23336014;

        // when
        boolean result = Task5.isPalindromeDescendant(sample);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Четвёртый пример")
    void fourthSampleTest() {
        // given
        final Integer sample = 11;

        // when
        boolean result = Task5.isPalindromeDescendant(sample);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Единственная цифра")
    void singleDigitStringTest() {
        // given
        final Integer sample = 1;

        // when
        boolean result = Task5.isPalindromeDescendant(sample);

        // then
        assertThat(result).isEqualTo(true);
    }
    @Test
    @DisplayName("Отрицательное число")
    void negativeNumberTest() {
        // given
        final Integer sample = -1001;

        // when
        boolean result = Task5.isPalindromeDescendant(sample);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Палиндром")
    void palindromeTest() {
        // given
        final Integer sample = 123454321;

        // when
        boolean result = Task5.isPalindromeDescendant(sample);

        // then
        assertThat(result).isEqualTo(true);
    }
}

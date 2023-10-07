package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Примеры из условия")
    void sampleTests() {
        boolean result;

        // Первый пример
        final Integer firstSample = 11211230;
        result = Task5.isPalindromeDescendant(firstSample);
        assertThat(result).isEqualTo(true);

        // Второй пример
        final Integer secondSample = 13001120;
        result = Task5.isPalindromeDescendant(secondSample);
        assertThat(result).isEqualTo(true);

        // Третий пример
        final Integer thirdSample = 23336014;
        result = Task5.isPalindromeDescendant(thirdSample);
        assertThat(result).isEqualTo(true);

        // Четвёртый пример
        final Integer fourthSample = 11;
        result = Task5.isPalindromeDescendant(fourthSample);
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Примеры не из условия")
    void nonSampleTests() {
        boolean result;

        // Едиственная цифра
        final Integer singleDigit = 1;
        result = Task5.isPalindromeDescendant(singleDigit);
        assertThat(result).isEqualTo(true);

        // Отрицательное число
        final Integer negativeNumber = -1001;
        result = Task5.isPalindromeDescendant(negativeNumber);
        assertThat(result).isEqualTo(false);

        // Обычный палиндром
        final Integer palindrome = 123454321;
        result = Task5.isPalindromeDescendant(palindrome);
        assertThat(result).isEqualTo(true);
    }
}

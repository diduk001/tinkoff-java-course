package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Примеры из условия")
    void sampleTests() {
        int result;

        // Первый пример
        final Integer firstSample = 4666;
        result = Task2.countDigits(firstSample);
        assertThat(result).isEqualTo(4);

        // Второй пример
        final Integer secondSample = 544;
        result = Task2.countDigits(secondSample);
        assertThat(result).isEqualTo(3);

        // Третий пример
        final Integer thirdSample = 0;
        result = Task2.countDigits(thirdSample);
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Отрицательное число")
    void negativeNumberTest() {
        final Integer negativeNumber = -123;
        int result = Task2.countDigits(negativeNumber);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Большое число")
    void bigNumberTest() {
        final Integer bigNumber = 1234567890;
        int result = Task2.countDigits(bigNumber);
        assertThat(result).isEqualTo(10);
    }
}

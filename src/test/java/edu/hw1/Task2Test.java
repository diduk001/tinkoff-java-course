package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @ParameterizedTest(name = "Пример из условия #{index} - {0}")
    @ValueSource(ints = {4666, 544, 0})
    void sampleTests(final int sampleNumber) {
        int result = Task2.countDigits(sampleNumber);
        int expected = switch (sampleNumber) {
            case 4666 -> 4;
            case 544 -> 3;
            case 0 -> 1;
            default -> 0; // Unexpected value
        };

        assertThat(result).isEqualTo(expected);
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

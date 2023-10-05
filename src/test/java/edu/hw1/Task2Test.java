package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Первый пример")
    void firstSampleTest() {
        // given
        final Integer sample = 4666;

        // when
        int result = Task2.countDigits(sample);

        // then
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Второй пример")
    void secondSampleTest() {
        // given
        final Integer sample = 544;

        // when
        int result = Task2.countDigits(sample);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Третий пример")
    void thirdSampleTest() {
        // given
        final Integer sample = 0;

        // when
        int result = Task2.countDigits(sample);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Отрицательное число")
    void negativeNumberTest() {
        // given
        final Integer sample = -123;

        // when
        int result = Task2.countDigits(sample);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Большое число")
    void negativeSecondsTest() {
        // given
        final Integer sample = 1234567890;

        // when
        int result = Task2.countDigits(sample);

        // then
        assertThat(result).isEqualTo(10);
    }
}

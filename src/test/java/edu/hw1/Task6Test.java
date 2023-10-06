package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Первый пример")
    void firstSampleTest() {
        // given
        final int sample = 6621;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Второй пример")
    void secondSampleTest() {
        // given
        final int sample = 6554;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Третий пример")
    void thirdSampleTest() {
        // given
        final int sample = 1234;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Константа 6174")
    void KaprekarsConstantTest() {
        // given
        final int sample = 6174;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Отрицательный аргумент")
    void negativeNumberTest() {
        // given
        final int sample = -1234;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Трёхзначный аргумент")
    void threeDigitsTest() {
        // given
        final int sample = 123;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Пятизначный аргумент")
    void fiveDigitsTest() {
        // given
        final int sample = 12345;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Тест с 1000")
    void oneThousandTest() {
        // given
        final int sample = 1000;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(5);
    }

    @Test
    @DisplayName("Тест с нулём")
    void zeroTest() {
        // given
        final int sample = 0;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Тест с одной цифрой")
    void oneDigitTest() {
        // given
        final int sample = 7;

        // when
        int result = Task6.countK(sample);

        // then
        assertThat(result).isEqualTo(4);
    }
}

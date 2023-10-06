package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Первый пример")
    void firstSampleTest() {
        // given
        final int sample_n = 8;
        int sample_shift = 1;

        // when
        int result = Task7.rotateRight(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Второй пример")
    void secondSampleTest() {
        // given
        final int sample_n = 16;
        int sample_shift = 1;

        // when
        int result = Task7.rotateLeft(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Третий пример")
    void thirdSampleTest() {
        // given
        final int sample_n = 17;
        int sample_shift = 2;

        // when
        int result = Task7.rotateLeft(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("Сдвиг 8 на 1 влево")
    void RotateLeftTest1() {
        // given
        final int sample_n = 8;
        int sample_shift = 1;

        // when
        int result = Task7.rotateLeft(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("Случайный тест")
    void rotateLeftTest2() {
        // given
        final int sample_n = 0b11010010011000111;
        int sample_shift = 5;

        // when
        int result = Task7.rotateLeft(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(0b01001100011111010);
    }

    @Test
    @DisplayName("Сдвиг 16 на 1 вправо")
    void rotateRightTest1() {
        // given
        final int sample_n = 16;
        int sample_shift = 1;

        // when
        int result = Task7.rotateRight(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(8);
    }

    @Test
    @DisplayName("Сдвиг 17 на 2 вправо")
    void rotateRightTest2() {
        // given
        final int sample_n = 17;
        int sample_shift = 2;

        // when
        int result = Task7.rotateRight(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(0b01100);
    }

    @Test
    @DisplayName("Большой сдвиг")
    void bigShiftTest() {
        // given
        final int sample_n = 2;
        int sample_shift = 100;

        // when
        int result = Task7.rotateRight(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(2);
    }

    @Test
    @DisplayName("n = 0")
    void zeroTest() {
        // given
        final int sample_n = 0;
        int sample_shift = 2;

        // when
        int result = Task7.rotateLeft(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    @DisplayName("Тест с 0b111111...")
    void onesTest() {
        // given
        final int sample_n = 0b111111111;
        int sample_shift = 123;

        // when
        int result = Task7.rotateLeft(sample_n, sample_shift);

        // then
        assertThat(result).isEqualTo(sample_n);
    }
}

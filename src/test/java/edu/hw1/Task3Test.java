package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Первый пример")
    void firstSampleTest() {
        // given
        final int[] sample_a1 = {1, 2, 3, 4};
        final int[] sample_a2 = {0, 6};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Второй пример")
    void secondSampleTest() {
        // given
        final int[] sample_a1 = {3, 1};
        final int[] sample_a2 = {4, 0};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Третий пример")
    void thirdSampleTest() {
        // given
        final int[] sample_a1 = {9, 9, 8};
        final int[] sample_a2 = {8, 9};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Четвёртый пример")
    void fourthSampleTest() {
        // given
        final int[] sample_a1 = {1, 2, 3, 4};
        final int[] sample_a2 = {2, 3};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Пустой массив a1")
    void emptyA1Test() {
        // given
        final int[] sample_a1 = {};
        final int[] sample_a2 = {2, 3};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("Пустой массив a2")
    void emptyA2Test() {
        // given
        final int[] sample_a1 = {1, 2, 3, 4};
        final int[] sample_a2 = {};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Два пустых массива")
    void twoEmptyArraysTest() {
        // given
        final int[] sample_a1 = {};
        final int[] sample_a2 = {};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Два одинаковый массива")
    void equalArraysTest() {
        // given
        final int[] sample_a1 = {1, 2, 3, 4};
        final int[] sample_a2 = {1, 2, 3, 4};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Непересекающиеся массивы")
    void nonIntersectingArraysTest() {
        // given
        final int[] sample_a1 = {1, 2, 3, 4};
        final int[] sample_a2 = {5, 6, 7, 8};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Пересекающиеся массивы")
    void intersectingArraysTest() {
        // given
        final int[] sample_a1 = {1, 2, 3, 4};
        final int[] sample_a2 = {3, 4, 5, 6};

        // when
        boolean result = Task3.isNestable(sample_a1, sample_a2);

        // then
        assertThat(result).isEqualTo(false);
    }
}

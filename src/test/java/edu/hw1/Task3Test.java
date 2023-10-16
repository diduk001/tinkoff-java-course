package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Примеры из условия")
    void sampleTests() {
        boolean result;

        // Первый пример
        final int[] firstSampleArray1 = {1, 2, 3, 4};
        final int[] firstSampleArray2 = {0, 6};
        result = Task3.isNestable(firstSampleArray1, firstSampleArray2);
        assertThat(result).isEqualTo(true);

        // Второй пример
        final int[] secondSampleArray1 = {3, 1};
        final int[] secondSampleArray2 = {4, 0};
        result = Task3.isNestable(secondSampleArray1, secondSampleArray2);
        assertThat(result).isEqualTo(true);

        // Третий пример
        final int[] thirdSampleArray1 = {9, 9, 8};
        final int[] thirdSampleArray2 = {8, 9};
        result = Task3.isNestable(thirdSampleArray1, thirdSampleArray2);
        assertThat(result).isEqualTo(false);

        // Четвёртый пример
        final int[] fourthSampleArray1 = {1, 2, 3, 4};
        final int[] fourthSampleArray2 = {2, 3};
        result = Task3.isNestable(fourthSampleArray1, fourthSampleArray2);
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Пустые массивы")
    void emptyArrayTests() {
        boolean result;

        // Массивы
        final int[] nonemptyArray1 = {1, 2, 3, 4};
        final int[] nonEmptyArray2 = {2, 3};
        final int[] emptyArray = {};

        // Первый массив пуст
        result = Task3.isNestable(emptyArray, nonEmptyArray2);
        assertThat(result).isEqualTo(true);

        // Второй массив пуст
        result = Task3.isNestable(nonemptyArray1, emptyArray);
        assertThat(result).isEqualTo(false);

        // Оба массива пусты
        result = Task3.isNestable(emptyArray, emptyArray);
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("Непустые массивы")
    void nonEmptyArraysTests() {
        boolean result;
        // Массивы для тестов
        final int[] nonEmptyArray1234 = {1, 2, 3, 4};
        final int[] nonEmptyArray3456 = {3, 4, 5, 6};
        final int[] nonEmptyArray5678 = {5, 6, 7, 8};

        // Массивы одинаковы
        result = Task3.isNestable(nonEmptyArray1234, nonEmptyArray1234);
        assertThat(result).isEqualTo(false);

        result = Task3.isNestable(nonEmptyArray3456, nonEmptyArray3456);
        assertThat(result).isEqualTo(false);

        result = Task3.isNestable(nonEmptyArray5678, nonEmptyArray5678);
        assertThat(result).isEqualTo(false);

        // Массивы не пересекаются
        result = Task3.isNestable(nonEmptyArray1234, nonEmptyArray5678);
        assertThat(result).isEqualTo(false);

        // Массивы пересекаются
        result = Task3.isNestable(nonEmptyArray1234, nonEmptyArray3456);
        assertThat(result).isEqualTo(false);

        result = Task3.isNestable(nonEmptyArray3456, nonEmptyArray5678);
        assertThat(result).isEqualTo(false);
    }
}

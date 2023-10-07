package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    @DisplayName("Примеры из условия")
    void sampleTests() {
        int result;

        // Первый пример
        final int firstSampleN = 8;
        int firstSampleShift = 1;
        result = Task7.rotateRight(firstSampleN, firstSampleShift);
        assertThat(result).isEqualTo(4);

        // Второй пример
        final int secondSampleN = 16;
        int secondSampleShift = 1;
        result = Task7.rotateLeft(secondSampleN, secondSampleShift);
        assertThat(result).isEqualTo(1);

        // Третий пример
        final int thirdSampleN = 17;
        int thirdSampleShift = 2;
        result = Task7.rotateLeft(thirdSampleN, thirdSampleShift);
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("Инвертированные примеры из условия")
    void invertedSampleTests() {
        int result;

        // Инвертированный первый пример
        final int firstSampleN = 8;
        int firstSampleShift = 1;
        result = Task7.rotateLeft(firstSampleN, firstSampleShift);
        assertThat(result).isEqualTo(1);

        // Инвертированный второй пример
        final int secondSampleN = 16;
        int secondSampleShift = 1;
        result = Task7.rotateRight(secondSampleN, secondSampleShift);
        assertThat(result).isEqualTo(8);

        // Инвертированный третий пример
        final int thirdSampleN = 17;
        int thirdSampleShift = 2;
        result = Task7.rotateRight(thirdSampleN, thirdSampleShift);
        assertThat(result).isEqualTo(12);
    }

    @Test
    @DisplayName("Другие тесты")
    void miscTests() {
        int result;

        // Случайный тест
        int randomN = 0b11010010011000111;
        result = Task7.rotateLeft(randomN, 5);
        assertThat(result).isEqualTo(0b01001100011111010);

        result = Task7.rotateRight(randomN, 3);
        assertThat(result).isEqualTo(0b11111010010011000);

        // Большой сдвиг
        result = Task7.rotateRight(2, 100);
        assertThat(result).isEqualTo(2);

        // n = 0
        result = Task7.rotateRight(0, 1000);
        assertThat(result).isEqualTo(0);

        // 2**k - 1 (0b111111...)
        final int onesNumber = 0b111111111;
        result = Task7.rotateRight(onesNumber, 1234);
        assertThat(result).isEqualTo(onesNumber);
    }
}

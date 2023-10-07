package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("Примеры из условия")
    void sampleTests() {
        int result;

        // Первый пример
        final int firstSample = 6621;
        result = Task6.countK(firstSample);
        assertThat(result).isEqualTo(5);

        // Второй пример
        final int secondSample = 6554;
        result = Task6.countK(secondSample);
        assertThat(result).isEqualTo(4);

        // Третий пример
        final int thirdSample = 1234;
        result = Task6.countK(thirdSample);
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("Невалидные входные данные")
    void invalidNumberTests() {
        int result;

        // Отрицательный элемент
        final int negativeNumber = -1234;
        result = Task6.countK(negativeNumber);
        assertThat(result).isEqualTo(-1);

        // Нулевой аргумент
        result = Task6.countK(0);
        assertThat(result).isEqualTo(-1);

        // Пятизначный аргумент
        final int fiveDigitNumber = 12345;
        result = Task6.countK(fiveDigitNumber);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Валидные входные данные")
    void validNumberTests() {
        int result;

        // Константа Капрекара
        final int kaprekarConstant = 6174;
        result = Task6.countK(kaprekarConstant);
        assertThat(result).isEqualTo(0);

        // 3-х значное число
        result = Task6.countK(123);
        assertThat(result).isEqualTo(3);

        // 1000
        result = Task6.countK(1000);
        assertThat(result).isEqualTo(5);

        // Одна цифра
        result = Task6.countK(7);
        assertThat(result).isEqualTo(4);
    }
}

package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Примеры из условия")
    void sampleTests() {
        int result;

        // Первый пример
        final String firstSample = "01:00";
        result = Task1.minutesToSeconds(firstSample);
        assertThat(result).isEqualTo(60);

        // Второй пример
        final String secondSample = "13:56";
        result = Task1.minutesToSeconds(secondSample);
        assertThat(result).isEqualTo(836);

        // Третий пример
        final String thirdSample = "10:60";
        result = Task1.minutesToSeconds(thirdSample);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Невалидные входные данные")
    void invalidInputTests() {
        int result;

        // Отрицательное кол-во минут
        final String negativeMinutes = "-10:20";
        result = Task1.minutesToSeconds(negativeMinutes);
        assertThat(result).isEqualTo(-1);

        // Отрицательное кол-во секунд
        final String negativeSeconds = "10:-20";
        result = Task1.minutesToSeconds(negativeSeconds);
        assertThat(result).isEqualTo(-1);

        // Пустая строка
        final String emptyString = "";
        result = Task1.minutesToSeconds(emptyString);
        assertThat(result).isEqualTo(-1);

        // Пустое кол-во минут
        final String emptyMinutes = ":12";
        result = Task1.minutesToSeconds(emptyMinutes);
        assertThat(result).isEqualTo(-1);

        // Пустое кол-во секунд
        final String emptySeconds = "12:";
        result = Task1.minutesToSeconds(emptySeconds);
        assertThat(result).isEqualTo(-1);

        // Невалидная строка
        final String invalidString = "abcd";
        result = Task1.minutesToSeconds(invalidString);
        assertThat(result).isEqualTo(-1);
    }
}

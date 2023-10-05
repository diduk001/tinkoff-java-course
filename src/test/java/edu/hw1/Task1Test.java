package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Первый пример")
    void firstSampleTest() {
        // given
        final String sample = "01:00";

        // when
        int result = Task1.minutesToSeconds(sample);

        // then
        assertThat(result).isEqualTo(60);
    }

    @Test
    @DisplayName("Второй пример")
    void secondSampleTest() {
        // given
        final String sample = "13:56";

        // when
        int result = Task1.minutesToSeconds(sample);

        // then
        assertThat(result).isEqualTo(836);
    }

    @Test
    @DisplayName("Третий пример")
    void thirdSampleTest() {
        // given
        final String sample = "10:60";

        // when
        int result = Task1.minutesToSeconds(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Отрицательное значение минут")
    void negativeMinutesTest() {
        // given
        final String sample = "-10:20";

        // when
        int result = Task1.minutesToSeconds(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Отрицательное значение секунд")
    void negativeSecondsTest() {
        // given
        final String sample = "10:-20";

        // when
        int result = Task1.minutesToSeconds(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyString() {
        // given
        final String sample = "";

        // when
        int result = Task1.minutesToSeconds(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Пустое кол-во минут")
    void emptyMinutes() {
        // given
        final String sample = ":12";

        // when
        int result = Task1.minutesToSeconds(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Пустое кол-во секунд")
    void emptySeconds() {
        // given
        final String sample = "12:";

        // when
        int result = Task1.minutesToSeconds(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    @DisplayName("Невалидная строка")
    void invalidString() {
        // given
        final String sample = "abcd";

        // when
        int result = Task1.minutesToSeconds(sample);

        // then
        assertThat(result).isEqualTo(-1);
    }
}

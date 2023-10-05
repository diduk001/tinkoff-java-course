package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Первый пример")
    void firstSampleTest() {
        // given
        final String sample = "123456";

        // when
        String result = Task4.fixString(sample);

        // then
        assertThat(result).isEqualTo("214365");
    }

    @Test
    @DisplayName("Второй пример")
    void secondSampleTest() {
        // given
        final String sample = "hTsii  s aimex dpus rtni.g";

        // when
        String result = Task4.fixString(sample);

        // then
        assertThat(result).isEqualTo("This is a mixed up string.");
    }

    @Test
    @DisplayName("Третий пример")
    void thirdSampleTest() {
        // given
        final String sample = "badce";

        // when
        String result = Task4.fixString(sample);

        // then
        assertThat(result).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Пустая строка")
    void emptyStringTest() {
        // given
        final String sample = "";

        // when
        String result = Task4.fixString(sample);

        // then
        assertThat(result).isEqualTo("");
    }

    @Test
    @DisplayName("Строка чётной длины")
    void evenLengthStringTest() {
        // given
        final String sample = "1234567890";

        // when
        String result = Task4.fixString(sample);

        // then
        assertThat(result).isEqualTo("2143658709");
    }

    @Test
    @DisplayName("Строка нечётной длины")
    void oddLengthStringTest() {
        // given
        final String sample = "123456789";

        // when
        String result = Task4.fixString(sample);

        // then
        assertThat(result).isEqualTo("214365879");
    }

    @Test
    @DisplayName("Один символ")
    void oneSymbolTest() {
        // given
        final String sample = "1";

        // when
        String result = Task4.fixString(sample);

        // then
        assertThat(result).isEqualTo("1");
    }

    @Test
    @DisplayName("Два символа")
    void equalArraysTest() {
        // given
        final String sample = "21";

        // when
        String result = Task4.fixString(sample);

        // then
        assertThat(result).isEqualTo("12");
    }
}

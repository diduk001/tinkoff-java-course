package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task7Test {
    @ParameterizedTest(name = "Строка подходит под 1-е выражение: \"{0}\"")
    @ValueSource(strings = {"010", "000", "110111", "000000000000", "1100111011011"})
    void matchesFirst(final String string) {
        assertThat(OneZeroRegex.lengthIsNoLessThan3AndThirdSymbolIs0(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 1-е выражение: \"{0}\"")
    @ValueSource(strings = {"123", "320", "Hello", "100123", "011", "001", "11111", "001000000000"})
    void notMatchesFirst(final String string) {
        assertThat(OneZeroRegex.lengthIsNoLessThan3AndThirdSymbolIs0(string)).isFalse();
    }

    @ParameterizedTest(name = "Строка подходит под 2-е выражение: \"{0}\"")
    @ValueSource(strings = {"1", "11", "010", "000", "110111", "000000000000", "1100111011011"})
    void matchesSecond(final String string) {
        assertThat(OneZeroRegex.startsAndEndsWithOneSymbol(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 2-е выражение: \"{0}\"")
    @ValueSource(strings = {"123", "320", "ooo", "1001231", "011", "001", "01111"})
    void notMatchesSecond(final String string) {
        assertThat(OneZeroRegex.startsAndEndsWithOneSymbol(string)).isFalse();
    }

    @ParameterizedTest(name = "Строка подходит под 3-е выражение: \"{0}\"")
    @ValueSource(strings = {"1", "11", "010", "000", "10"})
    void matchesThird(final String string) {
        assertThat(OneZeroRegex.lengthBetween1and3(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 3-е выражение: \"{0}\"")
    @ValueSource(strings = {"123", "3", "1001", "1001231", "111111", "0011", "01111"})
    void notMatchesThird(final String string) {
        assertThat(OneZeroRegex.lengthBetween1and3(string)).isFalse();
    }
}

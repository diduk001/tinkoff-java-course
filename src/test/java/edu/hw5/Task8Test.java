package edu.hw5;

import edu.hw5.Task8.OneZeroRegex;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    @ParameterizedTest(name = "Строка подходит под 1-е выражение: \"{0}\"")
    @ValueSource(strings = {"010", "1", "0", "11011"})
    void matchesFirst(final String string) {
        assertThat(OneZeroRegex.oddLength(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 1-е выражение: \"{0}\"")
    @ValueSource(strings = {"0011", "11", "2", "212", "1113", "000000", "some string"})
    void notMatchesFirst(final String string) {
        assertThat(OneZeroRegex.oddLength(string)).isFalse();
    }

    @ParameterizedTest(name = "Строка подходит под 2-е выражение: \"{0}\"")
    @ValueSource(strings = {"010", "1111", "0", "110110"})
    void matchesSecond(final String string) {
        assertThat(OneZeroRegex.oddLengthZeroOrEvenLengthOne(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 2-е выражение: \"{0}\"")
    @ValueSource(strings = {"00", "1", "11111", "012", "11a1", "some string"})
    void notMatchesSecond(final String string) {
        assertThat(OneZeroRegex.oddLengthZeroOrEvenLengthOne(string)).isFalse();
    }

    @ParameterizedTest(name = "Строка подходит под 3-е выражение: \"{0}\"")
    @ValueSource(strings = {"000", "1110010", "1010101", "110001000101010111"})
    void matchesThird(final String string) {
        assertThat(OneZeroRegex.zeroCountDividesByThree(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 3-е выражение: \"{0}\"")
    @ValueSource(strings = {"00", "10", "11110", "012", "11a1", "some string"})
    void notMatchesThird(final String string) {
        assertThat(OneZeroRegex.zeroCountDividesByThree(string)).isFalse();
    }

    @ParameterizedTest(name = "Строка подходит под 4-е выражение: \"{0}\"")
    @ValueSource(strings = {"0", "1", "110", "1110", "011", "0111", "0101011101"})
    void matchesFourth(final String string) {
        assertThat(OneZeroRegex.no11And111(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 4-е выражение: \"{0}\"")
    @ValueSource(strings = {"11", "111", "012", "11a1", "some string"})
    void notMatchesFourth(final String string) {
        assertThat(OneZeroRegex.no11And111(string)).isFalse();
    }

    @ParameterizedTest(name = "Строка подходит под 5-е выражение: \"{0}\"")
    @ValueSource(strings = {"1", "10", "1111", "1110", "1010101", "11111111111111"})
    void matchesFifth(final String string) {
        assertThat(OneZeroRegex.oddSymbolsAre1(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 5-е выражение: \"{0}\"")
    @ValueSource(strings = {"0", "10100", "110", "012", "11a1", "some string"})
    void notMatchesFifth(final String string) {
        assertThat(OneZeroRegex.oddSymbolsAre1(string)).isFalse();
    }

    @ParameterizedTest(name = "Строка подходит под 7-е выражение: \"{0}\"")
    @ValueSource(strings = {"010", "00", "100", "000001", "00000", "00000000000001"})
    void matchesSixth(final String string) {
        assertThat(OneZeroRegex.notLessThanTwoZeroesAndNoMoreThanOneOne(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 7-е выражение: \"{0}\"")
    @ValueSource(strings = {"11", "0", "01", "1101", "0012", "some string"})
    void notMatchesSixth(final String string) {
        assertThat(OneZeroRegex.notLessThanTwoZeroesAndNoMoreThanOneOne(string)).isFalse();
    }

    @ParameterizedTest(name = "Строка подходит под 6-е выражение: \"{0}\"")
    @ValueSource(strings = {"1", "0", "10", "1000001", "00000", "1010101", "00000000000001"})
    void matchesSeventh(final String string) {
        assertThat(OneZeroRegex.noSequentialOnes(string)).isTrue();
    }

    @ParameterizedTest(name = "Строка не подходит под 6-е выражение: \"{0}\"")
    @ValueSource(strings = {"11", "101100", "0110101", "012", "11a1", "some string"})
    void notMatchesSeventh(final String string) {
        assertThat(OneZeroRegex.noSequentialOnes(string)).isFalse();
    }
}

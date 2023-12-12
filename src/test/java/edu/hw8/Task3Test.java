package edu.hw8;

import edu.hw8.Task3.MultiThreadedCracker;
import edu.hw8.Task3.PasswordCracker;
import edu.hw8.Task3.SingleThreadCracker;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task3Test {
    @ParameterizedTest(name = "Проверка хеширования строки \"{0}\"")
    @ValueSource(strings = {"123456", "qwerty", "password123", "password"})
    void MD5HashTest(String plain) {
        final String result = PasswordCracker.md5Hash(plain);
        final String expected = switch (plain) {
            case "123456" -> "e10adc3949ba59abbe56e057f20f883e";
            case "qwerty" -> "d8578edf8458ce06fbc5bb76a58c5ca4";
            case "password123" -> "482c811da5d5b4bc6d497ffa98491e38";
            case "password" -> "5f4dcc3b5aa765d61d8327deb882cf99";
            default -> throw new IllegalStateException("Unexpected value: " + plain);
        };

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Проверка выбрасывания исключений в SingleThreadCracker")
    void invalidValuesTest() {
        assertThrows(IllegalArgumentException.class, () -> new SingleThreadCracker(-1, 10));
        assertThrows(IllegalArgumentException.class, () -> new SingleThreadCracker(-10, -1));
        assertThrows(IllegalArgumentException.class, () -> new SingleThreadCracker(10, 1));
        assertThrows(IllegalArgumentException.class, () -> new SingleThreadCracker(0, 1));
        assertDoesNotThrow(() -> new SingleThreadCracker(1, 1));
    }

    @ParameterizedTest(name = "Проверка поиска следующей строки для \"{0}\"")
    @ValueSource(strings = {"", "a", "z", "aa", "az", "zz"})
    void nextStringTest(final String str) {
        final String result = SingleThreadCracker.nextByAlphabet(str);
        final String expected = switch (str) {
            case "" -> "";
            case "a" -> "b";
            case "z" -> "00";
            case "aa" -> "ab";
            case "az" -> "b0";
            case "zz" -> "000";
            default -> throw new IllegalStateException("Unexpected value: " + str);
        };
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест одного пароля SingleThreadCracker")
    void singleThreadCrackerSinglePasswordTest() {
        final String result = new SingleThreadCracker(4, 6).crackSingle("81dc9bdb52d04dc20036dbd8313ed055");
        final String expected = "1234";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест SingleThreadCracker со словарём")
    void singleThreadCrackerDictionaryTest() {
        final Map<String, String> dict = Map.of(
            "c4ca4238a0b923820dcc509a6f75849b", "One",
            "c81e728d9d4c2f636f067f89cc14862c", "Two",
            "eccbc87e4b5ce2fe28308fd9f2a7baf3", "Three",
            "a87ff679a2f3e71d9181a67b7542122c", "Four"
        );

        final Map<String, String> result = new SingleThreadCracker(1, 4).crackDict(dict);
        final Map<String, String> expected = Map.of(
            "One", "1",
            "Two", "2",
            "Three", "3",
            "Four", "4"
        );

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест одного пароля MultiThreadedCracker")
    void multiThreadedCrackerSinglePasswordTest() {
        final String result = new MultiThreadedCracker(4, 4, 10).crackSingle("81dc9bdb52d04dc20036dbd8313ed055");
        final String expected = "1234";

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест MultiThreadedCracker со словарём")
    void MultiThreadedCrackerDictionaryTest() {
        final Map<String, String> dict = Map.of(
            "c4ca4238a0b923820dcc509a6f75849b", "One",
            "c81e728d9d4c2f636f067f89cc14862c", "Two",
            "eccbc87e4b5ce2fe28308fd9f2a7baf3", "Three",
            "a87ff679a2f3e71d9181a67b7542122c", "Four"
        );

        final Map<String, String> result = new MultiThreadedCracker(1, 4, 10).crackDict(dict);
        final Map<String, String> expected = Map.of(
            "One", "1",
            "Two", "2",
            "Three", "3",
            "Four", "4"
        );

        assertThat(result).isEqualTo(expected);
    }

}

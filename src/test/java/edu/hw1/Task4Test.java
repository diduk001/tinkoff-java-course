package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("Примеры из условия")
    void sampleTests() {
        String result;

        // Первый пример
        final String firstSample = "123456";
        result = Task4.fixString(firstSample);
        assertThat(result).isEqualTo("214365");

        // Второй пример
        final String secondSample = "hTsii  s aimex dpus rtni.g";
        result = Task4.fixString(secondSample);
        assertThat(result).isEqualTo("This is a mixed up string.");

        // Третий пример
        final String thirdSample = "badce";
        result = Task4.fixString(thirdSample);
        assertThat(result).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Примеры не из условия")
    void nonSampleTests() {
        String result;

        // Пустая строка
        final String emptyString = "";
        result = Task4.fixString(emptyString);
        assertThat(result).isEqualTo(emptyString);

        // Строка длины 1
        final String oneCharacter = "1";
        result = Task4.fixString(oneCharacter);
        assertThat(result).isEqualTo(oneCharacter);

        // Строка длины 2
        final String twoCharacters = "12";
        result = Task4.fixString(twoCharacters);
        assertThat(result).isEqualTo("21");

        // Строка чётной длины
        final String evenLengthString = "1234567890";
        result = Task4.fixString(evenLengthString);
        assertThat(result).isEqualTo("2143658709");

        // Строка нечётной длины
        final String oddLengthString = "123456789";
        result = Task4.fixString(oddLengthString);
        assertThat(result).isEqualTo("214365879");
    }
}

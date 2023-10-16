package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @ParameterizedTest(name = "Пример из условия #{index}: \"{0}\"")
    @ValueSource(strings = {"01:00", "13:56", "10:60"})
    void sampleTests(final String sampleStringRepresentation) {
        int result = Task1.minutesToSeconds(sampleStringRepresentation);
        int expected = switch (sampleStringRepresentation) {
            case "01:00" -> 60;
            case "13:56" -> 836;
            case "10:60" -> -1;
            default -> 0; // Unexpected value
        };

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Невалидные входные данные: \"{0}\"")
    @ValueSource(strings = {"-10:20", "10:-20", "", ":12", "12:", "abcd", "ups:ups"})
    void invalidInputTests(final String stringRepresentation) {
        int result = Task1.minutesToSeconds(stringRepresentation);
        assertThat(result).isEqualTo(-1);
    }
}

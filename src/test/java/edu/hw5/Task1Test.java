package edu.hw5;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    @DisplayName("Тест из примера")
    void sampleTest() {
        final List<String> sample = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );
        final Duration result = AverageDurationFinder.findAverageDuration(sample);
        final Duration expected = Duration.ofHours(3).plusMinutes(40);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Невалидная строка #{index} - {0}")
    @ValueSource(strings = {"a", "a - a", "22-1-1, 8:12 - 22-1-1, 8:13", "2022-03-12, 20:20 - 2022-03-12, 23:50 \n"})
    void invalidStringTest(final String string) {
        assertThrows(IllegalArgumentException.class, () ->
            AverageDurationFinder.findAverageDuration(List.of(string))
        );
    }

    @ParameterizedTest(name = "Невалидное время #{index} - {0}")
    @ValueSource(strings = {"2022-03-12, 20:20 - 2022-03-12, 27:50", "2022-03-12, 20:20 - 2022-03-12, 23:90",
        "2022-17-17, 20:20 - 2022-03-12, 23:50"})
    void invalidTimeTest(final String string) {
        assertThrows(DateTimeParseException.class, () ->
            AverageDurationFinder.findAverageDuration(List.of(string))
        );
    }
}

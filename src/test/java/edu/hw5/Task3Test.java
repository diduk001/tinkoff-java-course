package edu.hw5;

import edu.hw5.DateParser.DateParser;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @ParameterizedTest(name = "Тест из условия #{index} - \"{0}\"")
    @ValueSource(strings = {"2020-10-10", "2020-12-2", "1/3/1976", "1/3/20", "tomorrow", "today",
        "yesterday", "1 day ago", "2234 days ago"})
    void sampleTest(final String string) {
        final DateParser parser = new DateParser();
        final Optional<LocalDate> result = parser.parseDate(string);
        final LocalDate expectedLocalDate = switch (string) {
            case "2020-10-10" -> LocalDate.of(2020, 10, 10);
            case "2020-12-2" -> LocalDate.of(2020, 12, 2);
            case "1/3/1976" -> LocalDate.of(1976, 3, 1);
            case "1/3/20" -> LocalDate.of(2020, 3, 1);
            case "tomorrow" -> LocalDate.now().plusDays(1);
            case "today" -> LocalDate.now();
            case "yesterday", "1 day ago" -> LocalDate.now().minusDays(1);
            case "2234 days ago" -> LocalDate.now().minusDays(2234);
            default -> throw new IllegalArgumentException("Unexpected argument");
        };
        final Optional<LocalDate> expected = Optional.of(expectedLocalDate);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Неподходящая строка #{index} - {0}")
    @ValueSource(strings = {"13/13/13, 13-13-13, 0/123/0, 0/0/123, " +
        "some word, Tomorrow, 1 days ago, 123 day ago, -1 day ago"})
    void invalidString(final String string) {
        final DateParser parser = new DateParser();
        final Optional<LocalDate> result = parser.parseDate(string);
        assertThat(result.isEmpty()).isTrue();
    }
}

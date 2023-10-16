package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @ParameterizedTest(name = "Пример из условия #{index}: {0}")
    @ValueSource(ints = {6621, 6554, 1234})
    void sampleTests(final int sampleNumber) {
        final int result = Task6.countK(sampleNumber);
        final int expected = switch (sampleNumber) {
            case 6621 -> 5;
            case 6554 -> 4;
            case 1234 -> 3;
            default -> -1; // Unexpected value
        };
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Невалидный входные данные: {0}")
    @ValueSource(ints = {-1234, 0, 12345})
    void invalidNumberTests(final int invalidNumber) {
        int result = Task6.countK(invalidNumber);
        assertThat(result).isEqualTo(-1);
    }

    @ParameterizedTest(name = "Пример не из условия: {0}")
    @ValueSource(ints = {6174, 123, 1000, 7})
    void nonSampleTests(final int sampleNumber) {
        final int result = Task6.countK(sampleNumber);
        final int expected = switch (sampleNumber) {
            case 6174 -> 0;
            case 123 -> 3;
            case 1000 -> 5;
            case 7 -> 4;
            default -> -1; // Unexpected value
        };
        assertThat(result).isEqualTo(expected);
    }
}

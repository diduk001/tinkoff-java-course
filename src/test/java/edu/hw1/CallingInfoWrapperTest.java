package edu.hw1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CallingInfoWrapperTest {
    @ParameterizedTest(name = "Пример из условия #{index}: \"{0}\"")
    @ValueSource(strings = {"123456", "hTsii  s aimex dpus rtni.g", "badce"})
    void sampleTests(final String brokenString) {
        final String result = Task4.fixString(brokenString);
        final String expected = switch (brokenString) {
            case "123456" -> "214365";
            case "hTsii  s aimex dpus rtni.g" -> "This is a mixed up string.";
            case "badce" -> "abcde";
            default -> "Unexpected value"; // Unexpected value
        };

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Пример не из условия #{index}: \"{0}\"")
    @ValueSource(strings = {"", "1", "12", "1234567890", "123456789"})
    void nonSampleTests(final String brokenString) {
        final String result = Task4.fixString(brokenString);
        final String expected = switch (brokenString) {
            case "" -> "";
            case "1" -> "1";
            case "12" -> "21";
            case "1234567890" -> "2143658709";
            case "123456789" -> "214365879";
            default -> "Unexpected value"; // Unexpected value
        };

        assertThat(result).isEqualTo(expected);
    }
}

package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @ParameterizedTest(name = "Тест из условия #{index} - {0}")
    @ValueSource(ints = {2, 12, 16})
    void sampleTests(Integer value) {
        String result = Task4.convertToRoman(value);
        String expected = switch (value) {
            case 2 -> "II";
            case 12 -> "XII";
            case 16 -> "XVI";
            default -> ""; // Unexpected case
        };

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Случайное число #{index} - {0}")
    @ValueSource(ints = {5116, 2598, 4517, 289, 4957, 9273, 9659, 3256, 8663, 4618})
    void randomValueTests(Integer value) {
        String result = Task4.convertToRoman(value);
        String expected = switch (value) {
            case 5116 -> "MMMMMCXVI";
            case 2598 -> "MMDXCVIII";
            case 4517 -> "MMMMDXVII";
            case 289 -> "CCLXXXIX";
            case 4957 -> "MMMMCMLVII";
            case 9273 -> "MMMMMMMMMCCLXXIII";
            case 9659 -> "MMMMMMMMMDCLIX";
            case 3256 -> "MMMCCLVI";
            case 8663 -> "MMMMMMMMDCLXIII";
            case 4618 -> "MMMMDCXVIII";
            default -> ""; // Unexpected case
        };

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Невалидное число - {0}")
    @ValueSource(ints = {-101, -1, 0})
    void invalidValueTests(Integer value) {
        boolean threwException = false;
        try {
            Task4.convertToRoman(value);
        } catch (Exception e) {
            threwException = true;
        }
        assertThat(threwException).isTrue();
    }

}

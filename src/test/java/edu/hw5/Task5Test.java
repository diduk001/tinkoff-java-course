package edu.hw5;

import edu.hw5.Task5.CarNumberValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @ParameterizedTest(name = "Подходящий номер #{index} - {0}")
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177", "А000УВ012", "Х437УТ123", "Р654СЕ381"})
    void validNumberTest(final String number) {
        final boolean result = CarNumberValidator.validateCarNumber(number);
        assertThat(result).isTrue();
    }

    @ParameterizedTest(name = "Неподходящий номер #{index} - {0}")
    @ValueSource(strings = {"123АВЕ777", "А123ВГ77", "А123ВЕ7777", "ААА", "123"})
    void invalidNumberTest(final String number) {
        final boolean result = CarNumberValidator.validateCarNumber(number);
        assertThat(result).isFalse();
    }
}

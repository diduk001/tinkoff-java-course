package edu.hw5;

import edu.hw5.Task4.PasswordChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

public class Task4Test {
    @ParameterizedTest(name = "Подходящий пароль #{index} - #{0}")
    @ValueSource(strings = {"~", "!", "@", "#", "$", "%", "^", "&", "*", "|", "~ ! @ # $ % ^ & * |",
        "~p!a@s$s#w%o^r&d*|"})
    void checkValidPassword(final String password) {
        final boolean result = PasswordChecker.checkPassword(password);
        assertThat(result).isTrue();
    }

    @ParameterizedTest(name = "Неподходящий пароль #{index} - #{0}")
    @ValueSource(strings = {"abcdef", "0123456", "0123abcdef", "123.123.123.123", "AaAb_BbBa"})
    void checkInvalidPassword(final String password) {
        final boolean result = PasswordChecker.checkPassword(password);
        assertThat(result).isFalse();
    }

}

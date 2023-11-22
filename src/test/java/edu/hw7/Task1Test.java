package edu.hw7;

import edu.hw7.Task1.ThreadCounter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @ParameterizedTest(name = "Тест счётчика с N={0}")
    @ValueSource(ints = {0, 1, 2, 4, 8, 16, 32, 64, 128, 1024, 2_147_483_647})
    void counterTest(int n) {
        final int result = ThreadCounter.countWithThreads(n);
        assertThat(result).isEqualTo(n);
    }

    @ParameterizedTest(name = "Тест счётчика с невалидным значением N={0}")
    @ValueSource(ints = {-1, -2, 4 - 8, -32, -1024, -2_147_483_647})
    void invalidNTest(int n) {
        assertThrows(IllegalArgumentException.class, () -> ThreadCounter.countWithThreads(n));
    }

}

package edu.hw7;

import edu.hw7.Task2.ParallelStreamFactorial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @ParameterizedTest(name = "Тест счётчика с N={0}")
    @ValueSource(ints = {1, 2, 3, 6, 10, 15, 20})
    void counterTest(int n) {
        final long result = ParallelStreamFactorial.getFactorial(n);
        final long expected = switch (n) {
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 6;
            case 6 -> 720;
            case 10 -> 3_628_800;
            case 15 -> 1_307_674_368_000L;
            case 20 -> 2_432_902_008_176_640_000L;
            default -> throw new IllegalArgumentException("Unexpected value");
        };

        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "Тест счётчика с невалидным значением N={0}")
    @ValueSource(ints = {-1, -2, 4 - 8, -32, -1024, -2_147_483_647})
    void invalidNTest(int n) {
        assertThrows(IllegalArgumentException.class, () -> ParallelStreamFactorial.getFactorial(n));
    }

}

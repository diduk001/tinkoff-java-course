package edu.hw5;

import edu.hw5.Task2.AllFriday13thFinder;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Тест из примера #1: 1925")
    void sampleTest1() {
        final List<LocalDate> result = AllFriday13thFinder.findAllFriday13thInYear(1925);
        final List<LocalDate> expected = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест из примера #2: 2024")
    void sampleTest2() {
        final List<LocalDate> result = AllFriday13thFinder.findAllFriday13thInYear(2024);
        final List<LocalDate> expected = List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)
        );

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест 2005")
    void sampleTest3() {
        final List<LocalDate> result = AllFriday13thFinder.findAllFriday13thInYear(2005);
        final List<LocalDate> expected = List.of(
            LocalDate.of(2005, 5, 13)
        );

        assertThat(result).isEqualTo(expected);
    }
}

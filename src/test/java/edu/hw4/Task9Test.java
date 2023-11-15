package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task9Test {
    @Test
    @DisplayName("Тест задания 9 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Integer result = Main.pawsSum(sampleList);
        final Integer expected = 2 * 4 + 2 * 4 + 2 * 2 + 2 * 8;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = List.of();
        final Integer result = Main.pawsSum(emptyList);
        assertThat(result).isZero();
    }
}

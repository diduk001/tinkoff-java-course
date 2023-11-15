package edu.hw4;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.DORY;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Тест задания 8 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Optional<Animal> result = Main.heaviestAnimalSmallerThanK(sampleList, 200);
        assertThat(result).isEqualTo(Optional.of(DORY));
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = List.of();
        final Optional<Animal> result = Main.heaviestAnimalSmallerThanK(emptyList, 100);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    @DisplayName("Отрицательное значение K")
    void negativeKTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Optional<Animal> result = Main.heaviestAnimalSmallerThanK(sampleList, -10);
        assertThat(result.isPresent()).isFalse();
    }
}

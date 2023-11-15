package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task15Test {
    @Test
    @DisplayName("Тест задания 15 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Integer result = Main.summaryWeightOfAnimalsFromKToLYearsOld(sampleList, 9, 12);
        final Integer expected = 13 + 21 + 18;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Значение K больше L")
    void kValueIsMoreThanL() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        assertThrows(
            IllegalArgumentException.class,
            () -> Main.summaryWeightOfAnimalsFromKToLYearsOld(sampleList, 10, 0)
        );
    }

    @Test
    @DisplayName("Ни одно животное не подходит")
    void noMatchedFittingTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Integer result = Main.summaryWeightOfAnimalsFromKToLYearsOld(sampleList, 100, 200);
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = List.of();
        final Integer result = Main.summaryWeightOfAnimalsFromKToLYearsOld(emptyList, 10, 12);
        assertThat(result).isZero();
    }
}

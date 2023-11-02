package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.BARBOS;
import static edu.hw4.SampleAnimals.BELKA;
import static edu.hw4.SampleAnimals.KLEO;
import static edu.hw4.SampleAnimals.MURKA;
import static edu.hw4.SampleAnimals.MURZIK;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {
    @Test
    @DisplayName("Тест задания 2 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final List<Animal> result = Main.chooseKHeaviest(sampleList, 5);
        final List<Animal> expected = List.of(BELKA, KLEO, BARBOS, MURZIK, MURKA);
        assertThat(result).hasSize(5).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = new ArrayList<>();
        final List<Animal> result = Main.chooseKHeaviest(emptyList, 10);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("K=0")
    void zeroKTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final List<Animal> result = Main.chooseKHeaviest(sampleList, 0);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Отрицательное значение K")
    void negativeKTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        assertThrows(IllegalArgumentException.class, () -> Main.chooseKHeaviest(sampleList, -10));
    }

}

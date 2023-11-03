package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.BARBOS;
import static edu.hw4.SampleAnimals.KLEO;
import static edu.hw4.SampleAnimals.MURKA;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task11Test {
    @Test
    @DisplayName("Тест задания 11 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final List<Animal> result = Main.bigAnimalsThatCanBite(sampleList);
        final List<Animal> expected = List.of(MURKA, BARBOS, KLEO);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = new ArrayList<>();
        final List<Animal> result = Main.bigAnimalsThatCanBite(emptyList);
        assertThat(result).isEmpty();
    }
}

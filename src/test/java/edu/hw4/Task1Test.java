package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.BARBOS;
import static edu.hw4.SampleAnimals.BELKA;
import static edu.hw4.SampleAnimals.DORY;
import static edu.hw4.SampleAnimals.KLEO;
import static edu.hw4.SampleAnimals.MARY_JANE;
import static edu.hw4.SampleAnimals.MURKA;
import static edu.hw4.SampleAnimals.MURZIK;
import static edu.hw4.SampleAnimals.NEMO;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static edu.hw4.SampleAnimals.SPIDEY;
import static edu.hw4.SampleAnimals.TWEETIE;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Тест задания 1 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final List<Animal> result = Main.sortAnimalByHeight(sampleList);
        final List<Animal> expected = List.of(
            DORY, MARY_JANE, SPIDEY, NEMO, TWEETIE, KLEO, MURZIK, MURKA, BELKA, BARBOS
        );
        assertThat(result).hasSize(10).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест задания 1 на перевёрнутом примере")
    void reversedSampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST).reversed();
        final List<Animal> result = Main.sortAnimalByHeight(sampleList);
        final List<Animal> expected = List.of(
            DORY, MARY_JANE, SPIDEY, NEMO, TWEETIE, KLEO, MURZIK, MURKA, BELKA, BARBOS
        );
        assertThat(result).hasSize(10).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = new ArrayList<>();
        final List<Animal> result = Main.sortAnimalByHeight(emptyList);
        assertThat(result).isEmpty();
    }
}

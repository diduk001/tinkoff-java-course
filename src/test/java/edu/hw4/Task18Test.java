package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import static edu.hw4.SampleAnimals.SPIDEY;
import static edu.hw4.SampleAnimals.TWEETIE;
import static org.assertj.core.api.Assertions.assertThat;

public class Task18Test {
    @Test
    @DisplayName("Тест задания 18 на примере")
    void sampleListTest() {
        final List<Animal> sampleListPart1 =
            new ArrayList<>(List.of(MURKA, MURZIK, BARBOS, BELKA, TWEETIE, KLEO, NEMO));
        final List<Animal> sampleListPart2 = new ArrayList<>(List.of(DORY, SPIDEY));
        final List<Animal> sampleListPart3 = new ArrayList<>(List.of(MARY_JANE));
        final Optional<Animal> result =
            Main.heaviestFishFromManyLists(sampleListPart1, sampleListPart2, sampleListPart3);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(DORY);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyListPart1 = new ArrayList<>();
        final List<Animal> emptyListPart2 = new ArrayList<>();
        final List<Animal> emptyListPart3 = new ArrayList<>();

        final Optional<Animal> result = Main.heaviestFishFromManyLists(emptyListPart1, emptyListPart2, emptyListPart3);

        assertThat(result.isPresent()).isFalse();
    }
}

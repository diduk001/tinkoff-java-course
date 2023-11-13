package edu.hw4;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.MURKA;
import static edu.hw4.SampleAnimals.MURZIK;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Тест задания 3 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Map<Animal.Type, Long> result = Main.countAnimalByTypes(sampleList);
        final Map<Animal.Type, Long> expected = Map.of(
            Animal.Type.CAT, 2L,
            Animal.Type.DOG, 2L,
            Animal.Type.BIRD, 2L,
            Animal.Type.FISH, 2L,
            Animal.Type.SPIDER, 2L
        );

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = List.of();
        final Map<Animal.Type, Long> result = Main.countAnimalByTypes(emptyList);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("5 котов")
    void fiveCatsTest() {
        final List<Animal> fiveCats = List.of(
            MURKA, MURZIK, MURKA, MURZIK, MURKA
        );
        final Map<Animal.Type, Long> result = Main.countAnimalByTypes(fiveCats);
        final Map<Animal.Type, Long> expected = Map.of(Animal.Type.CAT, 5L);
        assertThat(result).isEqualTo(expected);
    }
}

package edu.hw4;

import java.util.ArrayList;
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
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final Map<Animal.Type, Integer> result = Main.countAnimalByTypes(sampleList);
        final Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.CAT, 2,
            Animal.Type.DOG, 2,
            Animal.Type.BIRD, 2,
            Animal.Type.FISH, 2,
            Animal.Type.SPIDER, 2
        );

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = new ArrayList<>();
        final Map<Animal.Type, Integer> result = Main.countAnimalByTypes(emptyList);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("5 котов")
    void fiveCatsTest() {
        final List<Animal> fiveCats = List.of(
            MURKA, MURZIK, MURKA, MURZIK, MURKA
        );
        final Map<Animal.Type, Integer> result = Main.countAnimalByTypes(fiveCats);
        final Map<Animal.Type, Integer> expected = Map.of(Animal.Type.CAT, 5);
        assertThat(result).isEqualTo(expected);
    }
}

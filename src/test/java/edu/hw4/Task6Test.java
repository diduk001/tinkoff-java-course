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

public class Task6Test {
    @Test
    @DisplayName("Тест задания 6 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final Map<Animal.Type, Animal> result = Main.heaviestAnimalOfEachType(sampleList);
        final Map<Animal.Type, Animal> expected = Map.of(
            Animal.Type.CAT, SampleAnimals.MURZIK,
            Animal.Type.DOG, SampleAnimals.BELKA,
            Animal.Type.BIRD, SampleAnimals.KLEO,
            Animal.Type.FISH, SampleAnimals.DORY,
            Animal.Type.SPIDER, SampleAnimals.SPIDEY
        );

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Только коты")
    void onlyCatsListTest() {
        final List<Animal> cats = List.of(MURKA, MURZIK);
        final Map<Animal.Type, Animal> result = Main.heaviestAnimalOfEachType(cats);
        final Map<Animal.Type, Animal> expected = Map.of(Animal.Type.CAT, MURZIK);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = new ArrayList<>();
        final Map<Animal.Type, Animal> result = Main.heaviestAnimalOfEachType(emptyList);
        assertThat(result).isEmpty();
    }
}

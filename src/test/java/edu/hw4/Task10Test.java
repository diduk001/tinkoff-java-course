package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task10Test {
    @Test
    @DisplayName("Тест задания 3 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final List<Animal> result = Main.animalsWithAgeIsNotEqualPaws(sampleList);
        assertThat(result).isEqualTo(SAMPLE_ANIMAL_LIST);
    }

    @Test
    @DisplayName("Фильтрация элемента")
    void filterElementTest() {
        List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        sampleList.add(new Animal(Animal.Type.CAT, "Cat", Animal.Sex.M, 4, 10, 10, false));
        final List<Animal> result = Main.animalsWithAgeIsNotEqualPaws(sampleList);
        assertThat(result).isEqualTo(SAMPLE_ANIMAL_LIST);
    }

    @Test
    @DisplayName("Фильтрация всего списка")
    void filterAllElementsTest() {
        final List<Animal> filterWholeList = new ArrayList<>(List.of(
            new Animal(Animal.Type.CAT, "Cat", Animal.Sex.M, 4, 10, 10, false),
            new Animal(Animal.Type.DOG, "Dog", Animal.Sex.M, 4, 10, 10, false),
            new Animal(Animal.Type.BIRD, "Bird", Animal.Sex.M, 2, 10, 10, false),
            new Animal(Animal.Type.FISH, "Fish", Animal.Sex.M, 0, 10, 10, false),
            new Animal(Animal.Type.SPIDER, "Spider", Animal.Sex.M, 8, 10, 10, false)
        ));

        final List<Animal> result = Main.animalsWithAgeIsNotEqualPaws(filterWholeList);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = new ArrayList<>();
        final List<Animal> result = Main.animalsWithAgeIsNotEqualPaws(emptyList);
        assertThat(result).isEmpty();
    }
}

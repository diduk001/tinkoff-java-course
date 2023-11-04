package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task16Test {
    @Test
    @DisplayName("Тест задания 16 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST);
        final List<Animal> result = Main.sortByTypeThanBySexThanByName(sampleList);
        // Поскольку в SAMPLE_ANIMAL_LIST животные уже отсортированы нужным образом, можем просто сравнить с sampleList
        assertThat(result).hasSize(10).isEqualTo(sampleList);
    }

    @Test
    @DisplayName("Тест задания 16 на перевёрнутом примере")
    void reversedSampleListTest() {
        final List<Animal> sampleList = new ArrayList<>(SAMPLE_ANIMAL_LIST).reversed();
        final List<Animal> result = Main.sortByTypeThanBySexThanByName(sampleList);
        assertThat(result).hasSize(10).isEqualTo(sampleList);
    }

    @Test
    @DisplayName("Сортировка по типу")
    void animalTypeSortTest() {
        final Animal cat = new Animal(Animal.Type.CAT, "Cat", Animal.Sex.M, 10, 10, 10, false);
        final Animal dog = new Animal(Animal.Type.DOG, "Dog", Animal.Sex.M, 10, 10, 10, false);
        final Animal bird = new Animal(Animal.Type.BIRD, "Bird", Animal.Sex.M, 10, 10, 10, false);
        final Animal fish = new Animal(Animal.Type.FISH, "Fish", Animal.Sex.M, 10, 10, 10, false);
        final Animal spider = new Animal(Animal.Type.SPIDER, "Spider", Animal.Sex.M, 10, 10, 10, false);

        final List<Animal> types = new ArrayList<>(List.of(dog, spider, bird, cat, fish));
        final List<Animal> result = Main.sortByTypeThanBySexThanByName(types);
        final List<Animal> expected = new ArrayList<>(List.of(cat, dog, bird, fish, spider));
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по полу")
    void sexSortTest() {
        final Animal male = new Animal(Animal.Type.CAT, "Male", Animal.Sex.M, 10, 10, 10, false);
        final Animal female = new Animal(Animal.Type.CAT, "Female", Animal.Sex.F, 10, 10, 10, false);

        final List<Animal> sexes = new ArrayList<>(List.of(female, male));
        final List<Animal> result = Main.sortByTypeThanBySexThanByName(sexes);
        final List<Animal> expected = new ArrayList<>(List.of(male, female));
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по имени")
    void nameSortTest() {
        final Animal barsik = new Animal(Animal.Type.CAT, "Barsik", Animal.Sex.M, 10, 10, 10, false);
        final Animal vaska = new Animal(Animal.Type.CAT, "Vaska", Animal.Sex.M, 10, 10, 10, false);

        final List<Animal> names = new ArrayList<>(List.of(vaska, barsik));
        final List<Animal> result = Main.sortByTypeThanBySexThanByName(names);
        final List<Animal> expected = new ArrayList<>(List.of(barsik, vaska));
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Сортировка по всем параметрам")
    void allParametersSortTest() {
        final Animal cat_m_barsik = new Animal(Animal.Type.CAT, "Barsik", Animal.Sex.M, 10, 10, 10, false);
        final Animal cat_m_vaska = new Animal(Animal.Type.CAT, "Vaska", Animal.Sex.M, 10, 10, 10, false);
        final Animal cat_f = new Animal(Animal.Type.CAT, "Murka", Animal.Sex.F, 10, 10, 10, false);
        final Animal dog = new Animal(Animal.Type.DOG, "Rex", Animal.Sex.M, 10, 10, 10, false);

        final List<Animal> animals = new ArrayList<>(List.of(cat_f, cat_m_vaska, dog, cat_m_barsik));
        final List<Animal> result = Main.sortByTypeThanBySexThanByName(animals);
        final List<Animal> expected = new ArrayList<>(List.of(cat_m_barsik, cat_m_vaska, cat_f, dog));
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = new ArrayList<>(List.of());
        final List<Animal> result = Main.sortByTypeThanBySexThanByName(emptyList);
        assertThat(result).isEmpty();
    }
}

package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task12Test {
    @Test
    @DisplayName("Тест задания 12 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Integer result = Main.countAnimalsWithWeightMoreThanHeight(sampleList);
        assertThat(result).isZero();
    }

    @Test
    @DisplayName("Ненулевой ответ")
    void nonZeroAnswer() {
        final List<Animal> animalsList = List.of(
            new Animal(Animal.Type.CAT, "Cat 1", Animal.Sex.M, 10, 10, 20, false),
            new Animal(Animal.Type.CAT, "Cat 2", Animal.Sex.M, 10, 20, 20, false),
            new Animal(Animal.Type.CAT, "Cat 3", Animal.Sex.M, 10, 20, 10, false)
        );

        final Integer result = Main.countAnimalsWithWeightMoreThanHeight(animalsList);
        assertThat(result).isOne();
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = List.of();
        final Integer result = Main.countAnimalsWithWeightMoreThanHeight(emptyList);
        assertThat(result).isZero();
    }
}

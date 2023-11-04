package edu.hw4;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task13Test {
    @Test
    @DisplayName("Тест задания 13 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final List<Animal> result = Main.animalsWithNameOfMoreThanTwoWords(sampleList);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Непустой ответ")
    void filterElementTest() {
        final Animal oneWordName = new Animal(Animal.Type.CAT, "Name",
            Animal.Sex.M, 10, 10, 10, false
        );
        final Animal twoWordsName = new Animal(Animal.Type.CAT, "Long Name",
            Animal.Sex.M, 10, 10, 10, false
        );
        final Animal threeWordsName = new Animal(Animal.Type.CAT, "Very Long Name",
            Animal.Sex.M, 10, 10, 10, false
        );
        final Animal fourWordsName = new Animal(Animal.Type.CAT, "Very Very Long Name",
            Animal.Sex.M, 10, 10, 10, false
        );

        final List<Animal> animalList =
            List.of(oneWordName, twoWordsName, threeWordsName, fourWordsName);

        final List<Animal> result = Main.animalsWithNameOfMoreThanTwoWords(animalList);
        final List<Animal> expected = List.of(threeWordsName, fourWordsName);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пустой список")
    void emptyListTest() {
        final List<Animal> emptyList = List.of();
        final List<Animal> result = Main.animalsWithNameOfMoreThanTwoWords(emptyList);
        assertThat(result).isEmpty();
    }
}

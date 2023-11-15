package edu.hw4;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class Task20Test {
    @Test
    @DisplayName("Тест задания 20 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Map<String, String> result = Main.prettyValidateAnimals(sampleList);
        final Map<String, String> expected = Map.of(
            "Murzik", "Validated correctly",
            "Murka", "Validated correctly",
            "Barbos", "Validated correctly",
            "Belka", "Validated correctly",
            "Tweetie", "Validated correctly",
            "Kleo", "Validated correctly",
            "Nemo", "Validated correctly",
            "Dory", "Validated correctly",
            "Spidey", "Validated correctly",
            "Mary Jane", "Validated correctly"
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тип животного является Null")
    void typeIsNullTest() {
        final Animal nullTypeAnimal = new Animal(null, "NullType", Animal.Sex.M, 1, 1, 1, false);
        final List<Animal> nullTypeList = List.of(nullTypeAnimal);
        final Map<String, String> result = Main.prettyValidateAnimals(nullTypeList);
        final Map<String, String> expected = Map.of("NullType", "Animal's type is null");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Имя животного является пустым")
    void emptyNameTest() {
        final Animal emptyNameAnimal = new Animal(Animal.Type.CAT, "", Animal.Sex.M, 1, 1, 1, false);
        final List<Animal> emptyNameList = List.of(emptyNameAnimal);
        final Map<String, String> result = Main.prettyValidateAnimals(emptyNameList);
        final Map<String, String> expected = Map.of("", "Animal's name is empty");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Имя животного состоит из пробельных символов")
    void spacesNameTest() {
        final Animal spacesNameAnimal = new Animal(Animal.Type.CAT, "  \t\n\r ", Animal.Sex.M, 1, 1, 1, false);
        final List<Animal> spacesNameList = List.of(spacesNameAnimal);
        final Map<String, String> result = Main.prettyValidateAnimals(spacesNameList);
        final Map<String, String> expected = Map.of("  \t\n\r ", "Animal's name is empty");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пол животного является Null")
    void sexIsNullTest() {
        final Animal nullSexAnimal = new Animal(Animal.Type.CAT, "NullSex", null, 1, 1, 1, false);
        final List<Animal> nullSexList = List.of(nullSexAnimal);
        final Map<String, String> result = Main.prettyValidateAnimals(nullSexList);
        final Map<String, String> expected = Map.of("NullSex", "Animal's sex is null");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Возраст животного отрицателен")
    void ageIsNegativeTest() {
        final Animal negativeAgeAnimal = new Animal(Animal.Type.CAT, "NegativeAge", Animal.Sex.M, -10, 1, 1, false);
        final List<Animal> negativeAgeList = List.of(negativeAgeAnimal);
        final Map<String, String> result = Main.prettyValidateAnimals(negativeAgeList);
        final Map<String, String> expected = Map.of("NegativeAge", "Animal's age is negative");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Рост животного отрицателен")
    void heightIsNegativeTest() {
        final Animal negativeHeightAnimal =
            new Animal(Animal.Type.CAT, "NegativeHeight", Animal.Sex.M, 1, -10, 1, false);
        final List<Animal> negativeHeightList = List.of(negativeHeightAnimal);
        final Map<String, String> result = Main.prettyValidateAnimals(negativeHeightList);
        final Map<String, String> expected = Map.of("NegativeHeight", "Animal's height is negative");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Вес животного отрицателен")
    void weightIsNegativeTest() {
        final Animal negativeWeightAnimal =
            new Animal(Animal.Type.CAT, "NegativeWeight", Animal.Sex.M, 1, 1, -10, false);
        final List<Animal> negativeWeightList = List.of(negativeWeightAnimal);
        final Map<String, String> result = Main.prettyValidateAnimals(negativeWeightList);
        final Map<String, String> expected = Map.of("NegativeWeight", "Animal's weight is negative");
        assertThat(result).isEqualTo(expected);
    }
}

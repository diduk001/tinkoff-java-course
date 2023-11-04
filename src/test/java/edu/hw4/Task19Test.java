package edu.hw4;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw4.SampleAnimals.SAMPLE_ANIMAL_LIST;
import static edu.hw4.ValidationError.ErrorType.AGE_IS_NEGATIVE;
import static edu.hw4.ValidationError.ErrorType.HEIGHT_IS_NEGATIVE;
import static edu.hw4.ValidationError.ErrorType.NAME_IS_EMPTY;
import static edu.hw4.ValidationError.ErrorType.NAME_IS_NULL;
import static edu.hw4.ValidationError.ErrorType.SEX_IS_NULL;
import static edu.hw4.ValidationError.ErrorType.TYPE_IS_NULL;
import static edu.hw4.ValidationError.ErrorType.VALIDATED_CORRECTLY;
import static edu.hw4.ValidationError.ErrorType.WEIGHT_IS_NEGATIVE;
import static org.assertj.core.api.Assertions.assertThat;

public class Task19Test {
    @Test
    @DisplayName("Тест задания 19 на примере")
    void sampleListTest() {
        final List<Animal> sampleList = List.copyOf(SAMPLE_ANIMAL_LIST);
        final Set<ValidationError> expected = Set.of(
            new ValidationError(VALIDATED_CORRECTLY)
        );

        for (Animal animal : sampleList) {
            assertThat(animal.validate()).isEqualTo(expected);
        }
    }

    @Test
    @DisplayName("Тип животного является Null")
    void typeIsNullTest() {
        final Animal nullTypeAnimal = new Animal(null, "Null", Animal.Sex.M, 1, 1, 1, false);
        final Set<ValidationError> result = nullTypeAnimal.validate();
        final Set<ValidationError> expected = Set.of(
            new ValidationError(TYPE_IS_NULL)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Имя животного является Null")
    void nameIsNullTest() {
        final Animal nullNameAnimal = new Animal(Animal.Type.CAT, null, Animal.Sex.M, 1, 1, 1, false);
        final Set<ValidationError> result = nullNameAnimal.validate();
        final Set<ValidationError> expected = Set.of(
            new ValidationError(NAME_IS_NULL)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Имя животного является пустым")
    void emptyNameTest() {
        final Animal emptyNameAnimal = new Animal(Animal.Type.CAT, "", Animal.Sex.M, 1, 1, 1, false);
        final Set<ValidationError> result = emptyNameAnimal.validate();
        final Set<ValidationError> expected = Set.of(
            new ValidationError(NAME_IS_EMPTY)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Имя животного состоит из пробельных символов")
    void spacesNameTest() {
        final Animal spacesNameAnimal = new Animal(Animal.Type.CAT, "  \t\n\r ", Animal.Sex.M, 1, 1, 1, false);
        final Set<ValidationError> result = spacesNameAnimal.validate();
        final Set<ValidationError> expected = Set.of(
            new ValidationError(NAME_IS_EMPTY)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Пол животного является Null")
    void sexIsNullTest() {
        final Animal nullSexAnimal = new Animal(Animal.Type.CAT, "Cat", null, 1, 1, 1, false);
        final Set<ValidationError> result = nullSexAnimal.validate();
        final Set<ValidationError> expected = Set.of(
            new ValidationError(SEX_IS_NULL)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Возраст животного отрицателен")
    void ageIsNegativeTest() {
        final Animal negativeAgeAnimal = new Animal(Animal.Type.CAT, "Cat", Animal.Sex.M, -10, 1, 1, false);
        final Set<ValidationError> result = negativeAgeAnimal.validate();
        final Set<ValidationError> expected = Set.of(
            new ValidationError(AGE_IS_NEGATIVE)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Рост животного отрицателен")
    void heightIsNegativeTest() {
        final Animal negativeHeightAnimal = new Animal(Animal.Type.CAT, "Cat", Animal.Sex.M, 1, -10, 1, false);
        final Set<ValidationError> result = negativeHeightAnimal.validate();
        final Set<ValidationError> expected = Set.of(
            new ValidationError(HEIGHT_IS_NEGATIVE)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Вес животного отрицателен")
    void weightIsNegativeTest() {
        final Animal negativeWeightAnimal = new Animal(Animal.Type.CAT, "Cat", Animal.Sex.M, 1, 1, -10, false);
        final Set<ValidationError> result = negativeWeightAnimal.validate();
        final Set<ValidationError> expected = Set.of(
            new ValidationError(WEIGHT_IS_NEGATIVE)
        );
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Тест всех ошибок сразу")
    void allErrorsTest() {
        final Animal allErrorsAnimal = new Animal(null, null, null, -1, -1, -1, false);
        final Set<ValidationError> result = allErrorsAnimal.validate();
        final Set<ValidationError> expected = Set.of(
            new ValidationError(TYPE_IS_NULL),
            new ValidationError(NAME_IS_NULL),
            new ValidationError(SEX_IS_NULL),
            new ValidationError(AGE_IS_NEGATIVE),
            new ValidationError(HEIGHT_IS_NEGATIVE),
            new ValidationError(WEIGHT_IS_NEGATIVE)
        );

        assertThat(result).isEqualTo(expected);
    }
}
